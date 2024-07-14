import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/DeleteCustomerServlet")
public class DeleteCustomerServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        String accountNo = request.getParameter("accountNo");

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/customer_details", "root", "root");

            PreparedStatement pst = con.prepareStatement("DELETE FROM banking_apps.users WHERE account_no = ?");
            PreparedStatement pst1=con.prepareStatement("UPDATE customers SET account_status = 'closed' WHERE account_no = ?");
            pst.setInt(1, Integer.parseInt(accountNo));
            pst1.setInt(1,Integer.parseInt(accountNo) );
            pst1.executeUpdate();

            int result = pst.executeUpdate();

            out.println("<html><body><script>");
            if (result > 0) {
                // Success alert
                out.println("alert('Customer deleted successfully!');");
            } else {
                // Error alert
                out.println("alert('Error deleting customer. Please try again.');");
            }
            // Redirect back to the customer details page or any other appropriate page
            out.println("window.location.href = 'customer_details.jsp';");
            out.println("</script></body></html>");

            con.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            // Handle exceptions appropriately
        } finally {
            out.close();
        }
    }
}
