import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

@WebServlet("/DeleteCustomerServlet")
public class DeleteCustomerServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        String accountNo = request.getParameter("accountNo");

        CustomerDAO customerDAO = new CustomerDAO();
        
        userDAO userDAO = new userDAO();

        try {
            customerDAO.updateAccountStatus(accountNo, "closed");
            
            userDAO.deleteUserByAccountNo(accountNo);

            out.println("<html><body><script>");
            out.println("alert('Customer deleted successfully!');");
            out.println("window.location.href = 'customer_details.jsp';");
            out.println("</script></body></html>");
        } catch (SQLException e) {
            e.printStackTrace();
            out.println("<html><body><script>");
            out.println("alert('Error deleting customer. Please try again.');");
            out.println("window.location.href = 'customer_details.jsp';");
            out.println("</script></body></html>");
        } finally {
            out.close();
        }
    }
}
