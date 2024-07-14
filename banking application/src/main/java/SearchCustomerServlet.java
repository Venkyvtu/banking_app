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

@WebServlet("/SearchCustomerServlet")
public class SearchCustomerServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String accountNo = request.getParameter("accountNo");
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/customer_details", "root", "root");

            PreparedStatement pst = con.prepareStatement("SELECT id, full_name, address, mobile_no, email, account_type, dob, initial_balance,id_proof_path FROM customers WHERE account_no = ?");
            pst.setString(1, accountNo);
            ResultSet rs = pst.executeQuery();

            out.println("<!DOCTYPE html>");
            out.println("<html lang='en'>");
            out.println("<head>");
            out.println("<meta charset='UTF-8'>");
            out.println("<meta name='viewport' content='width=device-width, initial-scale=1.0'>");
            out.println("<title>Customer Details</title>");
            out.println("<style>");
            out.println("body { font-family: Arial, sans-serif; background-color: #f9f9f9; margin: 40px; text-align: center; }");
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
            out.println("<input type='text' id='accountNo' name='accountNo' value="+ accountNo +"  required>");
            out.println("<button type='submit'>Search</button>");
            out.println("</form>");

            out.println("<div class='customer-details'>");

            if (rs.next()) {
                int id = rs.getInt("id");
                String fullName = rs.getString("full_name");
                String address = rs.getString("address");
                String mobile = rs.getString("mobile_no");
                String email = rs.getString("email");
                String accountType = rs.getString("account_type");
                String dob = rs.getString("dob");
                String initial_balance = rs.getString("initial_balance");
                String photo = rs.getString("id_proof_path");

                out.println("<table>");
                out.println("<tr><th>ID</th><td>" + id + "</td></tr>");
                out.println("<tr><th>Full Name</th><td>" + fullName + "</td></tr>");
                out.println("<tr><th>Address</th><td>" + address + "</td></tr>");
                out.println("<tr><th>Mobile</th><td>" + mobile + "</td></tr>");
                out.println("<tr><th>Email</th><td>" + email + "</td></tr>");
                out.println("<tr><th>Account Number</th><td>" + accountNo + "</td></tr>");
                out.println("<tr><th>Account Type</th><td>" + accountType + "</td></tr>");
                out.println("<tr><th>Date of Birth</th><td>" + dob + "</td></tr>");
                out.println("<tr><th>Balance Amount</th><td>" + initial_balance + "</td></tr>");
                out.println("</table>");

                out.println("<div>");
                out.println("<form action='EditCustomerServlet' method='post'>");
                out.println("<input type='hidden' name='id' value='" + id + "'>");
                out.println("<button type='submit'>Edit</button>");
                out.println("</form>");
                out.println("</div>");
            } else {
                out.println("<p>No customer found with account number: " + accountNo + "</p>");
            }

            out.println("</div>");
            out.println("</body>");
            out.println("</html>");

            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            out.close();
        }
    }
}
