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

@WebServlet("/deleteaccount")
public class deleteaccount extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String accountNo = request.getParameter("accountNo");

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/customer_details", "root", "root");

            PreparedStatement pst = con.prepareStatement("SELECT id, full_name, address, mobile_no, email, account_type, dob, initial_balance FROM customers WHERE account_no = ?");
            pst.setString(1, accountNo);
            ResultSet rs = pst.executeQuery();

            out.println("<html><head><style>");
            out.println("body { font-family: Arial, sans-serif; margin: 40px; text-align: center; padding: 20px; }");
            out.println("form { max-width: 400px; margin: 20px auto; background-color: #fff; padding: 20px; border-radius: 8px; box-shadow: 0 0 10px rgba(0, 0, 0, 0.1); }");
            out.println("label, input { display: block; margin: 10px auto; }");
            out.println("button { padding: 12px 20px; background-color: #007bff; color: white; border: none; border-radius: 4px; font-size: 16px; cursor: pointer; transition: background-color 0.3s ease; }");
            out.println("button:hover { background-color: #0056b3; }");
            out.println("table { border-collapse: collapse; width: 80%; margin: 20px auto; }");
            out.println("table, th, td { border: 1px solid black; padding: 8px; text-align: left; }");
            out.println("th { background-color: #f2f2f2; }");
            out.println("h2, p { margin-bottom: 20px; }");
            out.println("div { margin: 20px auto; max-width: 600px; background-color: #fff; padding: 20px; border-radius: 8px; box-shadow: 0 0 10px rgba(0, 0, 0, 0.1); }");
            out.println(".error-message { color: red; margin-top: 10px; text-align: center; }");
            out.println("</style></head><body><h2>Customer Details</h2>");
            out.println("<form action='deleteaccount' method='post'>");
            out.println("<label for='accountNo'>Account Number:</label>");
            out.println("<input type='text' id='accountNo' name='accountNo' required>");
            out.println("<button type='submit'>Search</button>");
            out.println("</form>");
            out.println("<div id='customerDetails'>");

            if (rs.next()) {
                int id = rs.getInt("id");
                String fullName = rs.getString("full_name");
                String address = rs.getString("address");
                String mobile = rs.getString("mobile_no");
                String email = rs.getString("email");
                String accountType = rs.getString("account_type");
                String dob = rs.getString("dob");
                String initialBalance = rs.getString("initial_balance");

                out.println("<table>");
                out.println("<tr><th>ID</th><td>" + id + "</td></tr>");
                out.println("<tr><th>Full Name</th><td>" + fullName + "</td></tr>");
                out.println("<tr><th>Address</th><td>" + address + "</td></tr>");
                out.println("<tr><th>Mobile</th><td>" + mobile + "</td></tr>");
                out.println("<tr><th>Email</th><td>" + email + "</td></tr>");
                out.println("<tr><th>Account Number</th><td>" + accountNo + "</td></tr>");
                out.println("<tr><th>Account Type</th><td>" + accountType + "</td></tr>");
                out.println("<tr><th>Date of Birth</th><td>" + dob + "</td></tr>");
                out.println("<tr><th>Balance Amount</th><td>" + initialBalance + "</td></tr>");
                out.println("</table>");
                out.println("<div>");
                out.println("<form action='DeleteCustomerServlet' method='post'>");
                out.println("<input type='hidden' name='accountNo' value='" + accountNo + "'>");
                out.println("<button type='submit'>Delete</button>");
                out.println("</form>");
                out.println("</div>");
            } else {
                out.println("<p class='error-message'>No customer found with account number: " + accountNo + "</p>");
            }

            out.println("</div>");
            out.println("</body></html>");

            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            out.close();
        }
    }
}
