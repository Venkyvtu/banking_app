import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/SearchCustomerServlet")
public class SearchCustomerServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String accountNo = request.getParameter("accountNo");

        CustomerDAO customerDAO = new CustomerDAO();
        Customer customer = customerDAO.getCustomerByAccountNo(accountNo);

        out.println("<!DOCTYPE html>");
        out.println("<html lang='en'>");
        out.println("<head>");
        out.println("<meta charset='UTF-8'>");
        out.println("<meta name='viewport' content='width=device-width, initial-scale=1.0'>");
        out.println("<title>Customer Details</title>");
        out.println("<style>");
        out.println("body { font-family: Arial, sans-serif; margin: 40px; text-align: center; }");
        out.println("form { margin: 20px auto; max-width: 400px; background-color: #fff; padding: 20px; border-radius: 8px; box-shadow: 0 0 10px rgba(0, 0, 0, 0.1); }");
        out.println("label, input { display: block; margin: 10px auto; }");
        out.println("button { padding: 12px 20px; background-color: #007bff; color: white; border: none; border-radius: 4px; font-size: 16px; cursor: pointer; transition: background-color 0.3s ease; }");
        out.println("button:hover { background-color: #0056b3; }");
        out.println("table { border-collapse: collapse; width: 100%; margin: 20px auto; max-width: 600px; }");
        out.println("table, th, td { border: 1px solid black; padding: 10px; text-align: left; }");
        out.println("th { background-color: #f2f2f2; }");
        out.println("h2, p { margin-bottom: 20px; }");
        out.println(".customer-details { margin: 20px auto; max-width: 600px; background-color: #fff; padding: 20px; border-radius: 8px; box-shadow: 0 0 10px rgba(0, 0, 0, 0.1); }");
        out.println(".error-message { color: red; margin-top: 10px; text-align: center; }");
        out.println("</style>");
        out.println("</head>");
        out.println("<body>");

        out.println("<h2>Customer Details</h2>");
        out.println("<form action='SearchCustomerServlet' method='post'>");
        out.println("<label for='accountNo'>Account Number:</label>");
        out.println("<input type='text' id='accountNo' name='accountNo' value='"+ accountNo +"' required>");
        out.println("<button type='submit'>Search</button>");
        out.println("</form>");

        out.println("<div class='customer-details'>");

        if (customer != null) {
            out.println("<table>");
            out.println("<tr><th>ID</th><td>" + customer.getId() + "</td></tr>");
            out.println("<tr><th>Full Name</th><td>" + customer.getFullName() + "</td></tr>");
            out.println("<tr><th>Address</th><td>" + customer.getAddress() + "</td></tr>");
            out.println("<tr><th>Mobile</th><td>" + customer.getMobileNo() + "</td></tr>");
            out.println("<tr><th>Email</th><td>" + customer.getEmail() + "</td></tr>");
            out.println("<tr><th>Account Number</th><td>" + accountNo + "</td></tr>");
            out.println("<tr><th>Account Type</th><td>" + customer.getAccountType() + "</td></tr>");
            out.println("<tr><th>Date of Birth</th><td>" + customer.getDob() + "</td></tr>");
            out.println("<tr><th>Balance Amount</th><td>" + customer.getInitialBalance() + "</td></tr>");
            out.println("</table>");

            out.println("<div>");
            out.println("<form action='EditCustomerServlet' method='post'>");
            out.println("<input type='hidden' name='id' value='" + customer.getId() + "'>");
            out.println("<button type='submit'>Edit</button>");
            out.println("</form>");
            out.println("</div>");
        } else {
            out.println("<p>No customer found with account number: " + accountNo + "</p>");
        }

        out.println("</div>");
        out.println("</body>");
        out.println("</html>");

        out.close();
    }
}
