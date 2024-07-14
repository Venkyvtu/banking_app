import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@WebServlet("/ChangePasswordServlet")
public class ChangePasswordServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ChangePasswordServlet() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String newPassword = request.getParameter("newPassword");
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");

        try {
            // Generate hashed password
            String hashedPassword = hashPassword(newPassword);

            // Database connection
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/banking_apps", "root", "root");

            // Update password
            PreparedStatement pst = con.prepareStatement("UPDATE users SET password=?, is_first_login=false WHERE username=?");
            pst.setString(1, hashedPassword);
            pst.setString(2, username);
            pst.executeUpdate();

            response.sendRedirect("login.jsp"); // Redirect to login page

            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Method to hash password using SHA-256
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
