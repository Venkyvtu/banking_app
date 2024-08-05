import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/CloseAccountServlet")
public class CloseAccountServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String accountId = request.getParameter("accountId");

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/customer_details", "root", "root");

            // Check the current balance of the account
            PreparedStatement ps = con.prepareStatement("SELECT initial_balance, account_status FROM customers WHERE account_no = ?");
            ps.setString(1, accountId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                float balance = rs.getFloat("initial_balance");
                String accountStatus = rs.getString("account_status");

                if ("closed".equalsIgnoreCase(accountStatus)) {
                    // If the account is already closed, alert the user
                    out.println("<script type=\"text/javascript\">");
                    out.println("alert('This account is already closed.');");
                    out.println("location='close_account.jsp';");  // Replace 'close_account.jsp' with the appropriate page
                    out.println("</script>");
                } else if (balance > 0) {
                    // If balance is not zero, alert the user to withdraw all funds
                    out.println("<script type=\"text/javascript\">");
                    out.println("alert('Please withdraw all funds before closing your account.');");
                    out.println("location='close_account.jsp';");  // Replace 'close_account.jsp' with the appropriate page
                    out.println("</script>");
                } else {
                    // If balance is zero, update the account status to 'closed'
                    PreparedStatement updatePs = con.prepareStatement("UPDATE customers SET account_status = 'closed' WHERE account_no = ?");
                    updatePs.setString(1, accountId);
                    updatePs.executeUpdate();

                    // Remove the user's credentials from the users table
                    PreparedStatement deletePs = con.prepareStatement("DELETE FROM banking_apps.users WHERE username = ?");
                    deletePs.setString(1, accountId);
                    deletePs.executeUpdate();

                    // Optionally, you can also delete the transactions associated with this account
                    PreparedStatement deleteTransPs = con.prepareStatement("DELETE FROM banking_apps.transactions WHERE account_no = ?");
                    deleteTransPs.setString(1, accountId);
                    deleteTransPs.executeUpdate();

                    out.println("<script type=\"text/javascript\">");
                    out.println("alert('Your account has been successfully closed.');");
                    out.println("location='login.jsp';");  // Replace 'login.jsp' with the appropriate page
                    out.println("</script>");
                }
            } else {
                out.println("<script type=\"text/javascript\">");
                out.println("alert('Account not found.');");
                out.println("location='close_account.jsp';");  // Replace 'close_account.jsp' with the appropriate page
                out.println("</script>");
            }

            con.close();
        } catch (Exception e) {
            e.printStackTrace();
            out.println("<script type=\"text/javascript\">");
            out.println("alert('An error occurred while closing your account. Please try again later.');");
            out.println("location='close_account.jsp';");  // Replace 'close_account.jsp' with the appropriate page
            out.println("</script>");
        } finally {
            out.close();
        }
    }
}
