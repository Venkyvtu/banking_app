

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/WithdrawServlet")
public class WithdrawServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public WithdrawServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
 
        protected void doPost(HttpServletRequest request, HttpServletResponse response)
                throws ServletException, IOException {
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();

            // Get parameters from the form
            String amountStr = request.getParameter("amount");
            double amount = Double.parseDouble(amountStr); // Convert amount to double

            // Retrieve session attributes
            HttpSession session = request.getSession();
            String username = (String) session.getAttribute("username");

            // Database connection parameters
            String jdbcUrl = "jdbc:mysql://localhost:3306/customer_details";
            String dbUsername = "root";
            String dbPassword = "root";

            Connection connection = null;
            PreparedStatement stmt = null;
            ResultSet rs = null;

            try {
                // Load MySQL JDBC Driver
                Class.forName("com.mysql.jdbc.Driver");

                // Establish connection to MySQL
                connection = DriverManager.getConnection(jdbcUrl, dbUsername, dbPassword);

                // Retrieve current balance from the database
                String retrieveBalanceQuery = "SELECT initial_balance FROM customers WHERE account_no=?";
                stmt = connection.prepareStatement(retrieveBalanceQuery);
                stmt.setString(1, username);
                rs = stmt.executeQuery();

                if (rs.next()) {
                    double currentBalance = rs.getDouble("initial_balance");

                    // Calculate new balance after deposit
                    double newBalance = currentBalance - amount;

                    // Update balance in the database
                    String updateBalanceQuery = "UPDATE customers SET initial_balance=? WHERE account_no=?";
                    stmt = connection.prepareStatement(updateBalanceQuery);
                    stmt.setDouble(1, newBalance);
                    stmt.setString(2, username);
                    int rowsUpdated = stmt.executeUpdate();

                    if (rowsUpdated > 0) {
                    	session.setAttribute("balance", newBalance);
                        // Insert transaction record into transactions table
                        String insertTransactionQuery = "INSERT INTO banking_apps.transactions (account_no, transaction_date, transaction_type, amount, balance) VALUES (?, NOW(), ?, ?, ?)";
                        stmt = connection.prepareStatement(insertTransactionQuery);
                        stmt.setString(1, username); 
                        stmt.setString(2, "Withdraw");
                        stmt.setDouble(3, amount);
                        stmt.setDouble(4, newBalance);
                        stmt.executeUpdate();
                        out.println("<html><body><script>");
                        out.println("alert('successfully withdraw amount');");
                        out.println("window.location.href = 'withdraw.jsp ';");
                        out.println("</script></body></html>");
                 
                        	
                    } else {
                        out.println("<html><body><script>");
                        out.println("alert('Failed to update balance. Please try again.');");
                        
                        out.println("</script></body></html>");
                    }
                } else {
                    out.println("<html><body><script>");
                    out.println("alert('Failed to retrieve current balance. Please try again.');");
                    
                    out.println("</script></body></html>");
                }
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
                out.println("<html><body><script>");
                out.println("alert('An unexpected error occurred. Please try again later.');");
               
                out.println("</script></body></html>");
            } finally {
                // Close connections and resources
                try {
                    if (rs != null) {
                        rs.close();
                    }
                    if (stmt != null) {
                        stmt.close();
                    }
                    if (connection != null) {
                        connection.close();
                    }
                    out.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
