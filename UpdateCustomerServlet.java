import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

@WebServlet("/UpdateCustomerServlet")
public class UpdateCustomerServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String id = request.getParameter("id");
        String fullName = request.getParameter("fullName");
        String address = request.getParameter("address");
        String mobile = request.getParameter("mobile");
        String email = request.getParameter("email");
        String username = request.getParameter("account_no");
        String accountType = request.getParameter("accountType");
        String dob = request.getParameter("dob");
        String accountStatus = request.getParameter("accountStatus");

        Customer customer = new Customer();
        customer.setId(Integer.parseInt(id));
        customer.setFullName(fullName);
        customer.setAddress(address);
        customer.setMobileNo(mobile);
        customer.setEmail(email);
        customer.setAccountNo(username);
        customer.setAccountType(accountType);
        customer.setDob(dob);
        customer.setAccountStatus(accountStatus);

        // Hash the password (DOB in this case)
        String hashedPassword = hashPassword(dob);

        CustomerDAO customerDAO = new CustomerDAO();
        

        try {
            customerDAO.updateCustomer(customer);

            if (!customerDAO.userExists(username)) {
                customerDAO.insertUser(username, hashedPassword);
            }else {
            	customerDAO.delete(customer);
            }

           response.sendRedirect("customer_details.jsp?success=true");

        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("customer_details.jsp?success=false");
        }
    }

    // Method to hash the password using SHA-256 algorithm
    private String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashInBytes = md.digest(password.getBytes());

            // Convert byte array to a string of hexadecimal format
            StringBuilder sb = new StringBuilder();
            for (byte b : hashInBytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }
}
