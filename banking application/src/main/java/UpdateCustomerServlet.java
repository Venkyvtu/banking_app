import java.io.IOException;
import java.io.PrintWriter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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

        // Hash the password (DOB in this case)
        String hashedPassword = hashPassword(dob);

        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/customer_details", "root", "root")) {
            // Load the JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Update customers table
            String updateCustomerSQL = "UPDATE customers SET full_name = ?, address = ?, mobile_no = ?, email = ?, account_type = ?, dob = ?, account_status = ? WHERE id = ?";
            try (PreparedStatement updateCustomerStmt = con.prepareStatement(updateCustomerSQL)) {
                updateCustomerStmt.setString(1, fullName);
                updateCustomerStmt.setString(2, address);
                updateCustomerStmt.setString(3, mobile);
                updateCustomerStmt.setString(4, email);
                updateCustomerStmt.setString(5, accountType);
                updateCustomerStmt.setString(6, dob);
                updateCustomerStmt.setString(7, accountStatus);
                updateCustomerStmt.setInt(8, Integer.parseInt(id));
                updateCustomerStmt.executeUpdate();
            }

            // Check if user exists in users table
            String checkUserSQL = "SELECT * FROM banking_apps.users WHERE username = ?";
            boolean userExists = false;
            try (PreparedStatement checkUserStmt = con.prepareStatement(checkUserSQL)) {
                checkUserStmt.setString(1, username);
                try (ResultSet rs = checkUserStmt.executeQuery()) {
                    if (rs.next()) {
                        userExists = true;
                    }
                }
            }

            if (!userExists) {
                // Insert new user
                String insertUserSQL = "INSERT INTO banking_apps.users (username, password, role) VALUES (?, ?, ?)";
                try (PreparedStatement insertUserStmt = con.prepareStatement(insertUserSQL)) {
                    insertUserStmt.setString(1, username);
                    insertUserStmt.setString(2, hashedPassword);
                    insertUserStmt.setString(3, "user");
                    insertUserStmt.executeUpdate();
                }
            }

            response.sendRedirect("customer_details.jsp?success=true");

        } catch (ClassNotFoundException | SQLException e) {
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
