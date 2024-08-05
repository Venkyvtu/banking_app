import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/transferAmountServlet")
public class transferAmountServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public transferAmountServlet() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        String amountStr = request.getParameter("amount");
        double amount = Double.parseDouble(amountStr);
        String destinationAccount = request.getParameter("destinationAccount");

        HttpSession session = request.getSession();
        String sourceAccount = (String) session.getAttribute("accountNumber");

        // Check if source and destination accounts are the same
        if (sourceAccount.equals(destinationAccount)) {
            out.println("<html><body><script>");
            out.println("alert('Source and destination account numbers cannot be the same.');");
            out.println("window.location.href = 'transfer_amount.jsp';");
            out.println("</script></body></html>");
            return;
        }

        String jdbcUrl = "jdbc:mysql://localhost:3306/customer_details";
        String dbUsername = "root";
        String dbPassword = "root";

        Connection connection = null;
        PreparedStatement psRetrieveSource = null;
        PreparedStatement psRetrieveDestination = null;
        PreparedStatement psUpdateSource = null;
        PreparedStatement psUpdateDestination = null;
        PreparedStatement psInsertTransaction = null;
        ResultSet rsSource = null;
        ResultSet rsDestination = null;
        ResultSet rsAccountHolder = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcUrl, dbUsername, dbPassword);
            connection.setAutoCommit(false); // Start transaction

            // Retrieve source account balance
            String retrieveBalanceQuery = "SELECT initial_balance FROM customers WHERE account_no = ?";
            psRetrieveSource = connection.prepareStatement(retrieveBalanceQuery);
            psRetrieveSource.setString(1, sourceAccount);
            rsSource = psRetrieveSource.executeQuery();

            if (!rsSource.next()) {
                throw new SQLException("Source account not found");
            }

            double sourceBalance = rsSource.getDouble("initial_balance");

            if (sourceBalance < amount) {
                throw new SQLException("Insufficient funds in source account");
            }

            // Retrieve destination account balance
            psRetrieveDestination = connection.prepareStatement(retrieveBalanceQuery);
            psRetrieveDestination.setString(1, destinationAccount);
            rsDestination = psRetrieveDestination.executeQuery();

            if (!rsDestination.next()) {
                throw new SQLException("Destination account not found");
            }

            double destinationBalance = rsDestination.getDouble("initial_balance");

            // Retrieve destination account holder's full name
            String retrieveAccountHolderQuery = "SELECT full_name FROM customers WHERE account_no = ?";
            psRetrieveDestination = connection.prepareStatement(retrieveAccountHolderQuery);
            psRetrieveDestination.setString(1, destinationAccount);
            rsAccountHolder = psRetrieveDestination.executeQuery();

            String destinationFullName = "";
            if (rsAccountHolder.next()) {
                destinationFullName = rsAccountHolder.getString("full_name");
            }

            // Update source account balance
            String updateBalanceQuery = "UPDATE customers SET initial_balance = ? WHERE account_no = ?";
            psUpdateSource = connection.prepareStatement(updateBalanceQuery);
            psUpdateSource.setDouble(1, sourceBalance - amount);
            psUpdateSource.setString(2, sourceAccount);
            psUpdateSource.executeUpdate();

            // Update destination account balance
            psUpdateDestination = connection.prepareStatement(updateBalanceQuery);
            psUpdateDestination.setDouble(1, destinationBalance + amount);
            psUpdateDestination.setString(2, destinationAccount);
            psUpdateDestination.executeUpdate();

            // Insert transaction record for source account
            String insertTransactionQuery = "INSERT INTO banking_apps.transactions (account_no, transaction_date, transaction_type, amount, balance) VALUES (?, NOW(), ?, ?, ?)";
            psInsertTransaction = connection.prepareStatement(insertTransactionQuery);
            psInsertTransaction.setString(1, sourceAccount);
            psInsertTransaction.setString(2, "Withdraw");
            psInsertTransaction.setDouble(3, amount);
            psInsertTransaction.setDouble(4, sourceBalance - amount);
            psInsertTransaction.executeUpdate();

            // Insert transaction record for destination account
            psInsertTransaction.setString(1, destinationAccount);
            psInsertTransaction.setString(2, "Deposit");
            psInsertTransaction.setDouble(3, amount);
            psInsertTransaction.setDouble(4, destinationBalance + amount);
            psInsertTransaction.executeUpdate();

            connection.commit(); // Commit transaction

            session.setAttribute("balance", sourceBalance - amount);
            session.setAttribute("destinationFullName", destinationFullName); // Set destination full name

            out.println("<html><body><script>");
            out.println("alert('Transfer successful');");
            out.println("window.location.href = 'transfer_amount.jsp';");
            out.println("</script></body></html>");

        } catch (Exception e) {
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            e.printStackTrace();
            out.println("<html><body><script>");
            out.println("alert('An unexpected error occurred. Please try again later.');");
            out.println("window.location.href = 'transfer_amount.jsp';");
            out.println("</script></body></html>");
        } finally {
            try {
                if (rsSource != null) rsSource.close();
                if (rsDestination != null) rsDestination.close();
                if (rsAccountHolder != null) rsAccountHolder.close();
                if (psRetrieveSource != null) psRetrieveSource.close();
                if (psRetrieveDestination != null) psRetrieveDestination.close();
                if (psUpdateSource != null) psUpdateSource.close();
                if (psUpdateDestination != null) psUpdateDestination.close();
                if (psInsertTransaction != null) psInsertTransaction.close();
                if (connection != null) connection.close();
                out.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
