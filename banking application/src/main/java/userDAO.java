import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class userDAO {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/banking_apps";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "root";

    public Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
    }


    public void deleteUserByAccountNo(String accountNo) throws SQLException {
        String deleteUserSQL = "DELETE FROM users WHERE username = ?";
        try (Connection con = getConnection();
             PreparedStatement pst = con.prepareStatement(deleteUserSQL)) {
            pst.setString(1, accountNo);
            pst.executeUpdate();
        }
    }
}
