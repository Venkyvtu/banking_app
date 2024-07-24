package controller;

import model.UserLoginResult;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.math.BigDecimal;
import java.sql.*;

public class LoginDAO {

    // Method to validate user credentials
    public UserLoginResult validate(String username, String password) {
        String jdbcUrl = "jdbc:mysql://localhost:3306/banking_apps";
        String dbUsername = "root";
        String dbPassword = "root";
        UserLoginResult result = null;

        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcUrl, dbUsername, dbPassword);

            // Fetch hashed password and role from database
            String sql = "SELECT password, role, is_first_login FROM users WHERE username=?";
            ps = connection.prepareStatement(sql);
            ps.setString(1, username);

            rs = ps.executeQuery();
            if (rs.next()) {
                String hashedPasswordFromDB = rs.getString("password");
                String role = rs.getString("role");
                boolean firstLogin = rs.getBoolean("is_first_login");

                // Verify password using your hash method
                if (isAdmin(role)) {
                    // Admin password is not hashed, compare directly
                    if (password.equals(hashedPasswordFromDB)) {
                        result = new UserLoginResult(role, firstLogin, null, null, null,null,null,null,null);
                    }
                } else {
                    // User password needs to be hashed for comparison
                    if (verifyPassword(password, hashedPasswordFromDB)) {
                        // If password matches, fetch user details
                        sql = "SELECT full_name, account_no, initial_balance,address,mobile_no,email,dob FROM customer_details.customers WHERE account_no=?";
                        ps = connection.prepareStatement(sql);
                        ps.setString(1, username);

                        rs = ps.executeQuery();
                        if (rs.next()) {
                            String fullName = rs.getString("full_name");
                            String accountNumber = rs.getString("account_no");
                            BigDecimal balance = rs.getBigDecimal("initial_balance");
                            String address = rs.getString("address");

                            String mobile_no = rs.getString("mobile_no");

                            String email = rs.getString("email");
                            Date dob = rs.getDate("dob");


                            result = new UserLoginResult(role, firstLogin, fullName, accountNumber, balance,address,mobile_no,email,dob);
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    // Method to verify password using hashing
    private boolean verifyPassword(String inputPassword, String hashedPasswordFromDB) {
        String hashedInputPassword = hashPassword(inputPassword); // Hash input password
        return hashedInputPassword.equals(hashedPasswordFromDB); // Compare with stored hash
    }

    // Method to hash password using SHA-256 (similar to your previous implementation)
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

    // Method to check if the user role is admin
    private boolean isAdmin(String role) {
        return role != null && role.equalsIgnoreCase("admin");
    }
}
