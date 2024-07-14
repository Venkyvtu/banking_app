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

@WebServlet("/EditCustomerServlet")
public class EditCustomerServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String id = request.getParameter("id");

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/customer_details", "root", "root");

            PreparedStatement pst = con.prepareStatement("SELECT id, full_name, address, mobile_no, email, account_no, account_type, dob, initial_balance, account_status FROM customers WHERE id = ?");
            pst.setString(1, id);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                String currentAccountType = rs.getString("account_type");
                String currentAccountStatus = rs.getString("account_status");

                out.println("<html><head><style>");
                out.println("body { font-family: Arial, sans-serif; background-color: #f9f9f9; margin: 40px; padding: 20px; }");
                out.println("h2 { text-align: center; }");
                out.println("form { max-width: 600px; margin: auto; padding: 20px; background-color: #fff; border-radius: 8px; box-shadow: 0 0 10px rgba(0,0,0,0.1); }");
                out.println("label, input, select { display: block; width: 100%; margin: 10px 0; padding: 12px; box-sizing: border-box; }");
                out.println("button { width: 100%; padding: 12px; background-color: #007bff; color: white; border: none; border-radius: 4px; font-size: 16px; cursor: pointer; transition: background-color 0.3s ease; }");
                out.println("button:hover { background-color: #0056b3; }");
                out.println("</style></head><body><h2>Edit Customer Details</h2>");
                out.println("<form action='UpdateCustomerServlet' method='post'>");

                out.println("<input type='hidden' name='id' value='" + rs.getInt("id") + "'>");
                out.println("<label for='fullName'>Full Name:</label><input type='text' id='fullName' name='fullName' value='" + rs.getString("full_name") + "' required>");
                out.println("<label for='address'>Address:</label><input type='text' id='address' name='address' value='" + rs.getString("address") + "' required>");
                out.println("<label for='mobile'>Mobile No:</label><input type='text' id='mobile' name='mobile' value='" + rs.getString("mobile_no") + "' required>");
                out.println("<label for='email'>Email:</label><input type='email' id='email' name='email' value='" + rs.getString("email") + "' required>");
                out.println("<input type='hidden' name='account_no' value='" + rs.getString("account_no") + "'>");
                out.println("<label for='accountType'>Account Type:</label><select id='accountType' name='accountType' required>");
                out.println("<option value='Savings'" + ("Savings".equals(currentAccountType) ? " selected" : "") + ">Savings</option>");
                out.println("<option value='Current'" + ("Current".equals(currentAccountType) ? " selected" : "") + ">Current</option>");
                out.println("<option value='Fixed Deposit'" + ("Fixed Deposit".equals(currentAccountType) ? " selected" : "") + ">Fixed Deposit</option>");
                out.println("</select>");
                out.println("<label for='dob'>Date of Birth:</label><input type='date' id='dob' name='dob' value='" + rs.getString("dob") + "' required>");
                out.println("<label for='initialBalance'>Balance Amount:</label><input type='text' id='initialBalance' name='initialBalance' value='" + rs.getString("initial_balance") + "' required>");
                out.println("<label for='accountStatus'>Account Status:</label><select id='accountStatus' name='accountStatus' value= '"+rs.getString("account_status")+"' required>");
                out.println("<option value='Open'" + ("Open".equals(currentAccountStatus) ? " selected" : "") + ">Open</option>");
                out.println("<option value='Closed'" + ("Closed".equals(currentAccountStatus) ? " selected" : "") + ">Closed</option>");
                out.println("</select>");
                out.println("<button type='submit'>Update</button>");
                out.println("</form></body></html>");
            } else {
                out.println("<html><head><style>");
                out.println("body { font-family: Arial, sans-serif; background-color: #f9f9f9; margin: 40px; padding: 20px; }");
                out.println("h2 { text-align: center; }");
                out.println(".message { text-align: center; color: red; }");
                out.println("</style></head><body>");
                out.println("<h2>Customer Details</h2>");
                out.println("<p class='message'>Customer not found.</p>");
                out.println("</body></html>");
            }

            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            out.close();
        }
    }
}
