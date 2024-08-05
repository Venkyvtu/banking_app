import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/EditCustomerServlet")
public class EditCustomerServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String id = request.getParameter("id");

        CustomerDAO customerDAO = new CustomerDAO();
        Customer customer = customerDAO.getCustomerById(id);

        out.println("<html><head><style>");
        out.println("body { font-family: Arial, sans-serif; margin: 40px; padding: 20px; }");
        out.println("h2 { text-align: center; }");
        out.println("form { max-width: 600px; margin: auto; padding: 20px; background-color: #fff; border-radius: 8px; box-shadow: 0 0 10px rgba(0,0,0,0.1); }");
        out.println("label, input, select { display: block; width: 100%; margin: 10px 0; padding: 12px; box-sizing: border-box; }");
        out.println("button { width: 100%; padding: 12px; background-color: #007bff; color: white; border: none; border-radius: 4px; font-size: 16px; cursor: pointer; transition: background-color 0.3s ease; }");
        out.println("button:hover { background-color: #0056b3; }");
        out.println("</style></head><body><h2>Edit Customer Details</h2>");

        if (customer != null) {
            out.println("<form action='UpdateCustomerServlet' method='post'>");

            out.println("<input type='hidden' name='id' value='" + customer.getId() + "'>");
            out.println("<label for='fullName'>Full Name:</label><input type='text' id='fullName' name='fullName' value='" + customer.getFullName() + "' required>");
            out.println("<label for='address'>Address:</label><input type='text' id='address' name='address' value='" + customer.getAddress() + "' required>");
            out.println("<label for='mobile'>Mobile No:</label><input type='text' id='mobile' name='mobile' value='" + customer.getMobileNo() + "' required>");
            out.println("<label for='email'>Email:</label><input type='email' id='email' name='email' value='" + customer.getEmail() + "' required>");
            out.println("<input type='hidden' name='account_no' value='" + customer.getAccountNO()+ "'>");
            out.println("<label for='accountType'>Account Type:</label><select id='accountType' name='accountType' required>");
            out.println("<option value='Savings'" + ("Savings".equals(customer.getAccountType()) ? " selected" : "") + ">Savings</option>");
            out.println("<option value='Current'" + ("Current".equals(customer.getAccountType()) ? " selected" : "") + ">Current</option>");
            out.println("<option value='Fixed Deposit'" + ("Fixed Deposit".equals(customer.getAccountType()) ? " selected" : "") + ">Fixed Deposit</option>");
            out.println("</select>");
            out.println("<label for='dob'>Date of Birth:</label><input type='date' id='dob' name='dob' value='" + customer.getDob() + "' required>");
            out.println("<label for='initialBalance'>Balance Amount:</label><input type='text' id='initialBalance' name='initialBalance' value='" + customer.getInitialBalance() + "'disabled required>");
            out.println("<label for='accountStatus'>Account Status:</label><select id='accountStatus' name='accountStatus' required>");
            out.println("<option value='Open'" + ("Open".equals(customer.getAccountStatus()) ? " selected" : "") + ">Open</option>");
            out.println("<option value='Closed'" + ("Closed".equals(customer.getAccountStatus()) ? " selected" : "") + ">Closed</option>");
            out.println("</select>");
            out.println("<button type='submit'>Update</button>");
            out.println("</form>");
        } else {
            out.println("<p>Customer not found.</p>");
        }

        out.println("</body></html>");
        out.close();
    }
}
