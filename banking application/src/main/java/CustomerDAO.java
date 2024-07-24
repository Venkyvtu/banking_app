import java.sql.Connection; 
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerDAO {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/customer_details";
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

    public Customer getCustomerByAccountNo(String accountNo) {
        Customer customer = null;
        String query = "SELECT id, full_name, address, mobile_no, email, account_type, dob, initial_balance, id_proof_type,id_proof_number FROM customers WHERE account_no = ?";
        
        try (Connection con = getConnection();
             PreparedStatement pst = con.prepareStatement(query)) {
            pst.setString(1, accountNo);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    customer = new Customer();
                    customer.setId(rs.getInt("id"));
                    customer.setFullName(rs.getString("full_name"));
                    customer.setAddress(rs.getString("address"));
                    customer.setMobileNo(rs.getString("mobile_no"));
                    customer.setEmail(rs.getString("email"));
                    customer.setAccountType(rs.getString("account_type"));
                    customer.setDob(rs.getString("dob"));
                    customer.setInitialBalance(rs.getDouble("initial_balance"));
                    customer.setIdProofType(rs.getString("id_proof_type"));
                    customer.setIdProofNumber(rs.getString("id_proof_number"));
                    
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customer;
    }
    public void updateCustomer(Customer customer) throws SQLException {
        String updateCustomerSQL = "UPDATE customers SET full_name = ?, address = ?, mobile_no = ?, email = ?, account_type = ?, dob = ?, account_status = ? WHERE id = ?";
        try (Connection con = getConnection();
             PreparedStatement updateCustomerStmt = con.prepareStatement(updateCustomerSQL)) {
            updateCustomerStmt.setString(1, customer.getFullName());
            updateCustomerStmt.setString(2, customer.getAddress());
            updateCustomerStmt.setString(3, customer.getMobileNo());
            updateCustomerStmt.setString(4, customer.getEmail());
            updateCustomerStmt.setString(5, customer.getAccountType());
            updateCustomerStmt.setString(6, customer.getDob());
            updateCustomerStmt.setString(7, customer.getAccountStatus());
            updateCustomerStmt.setInt(8, customer.getId());
            updateCustomerStmt.executeUpdate();
        }
    }
    public Customer getCustomerById(String id) {
        Customer customer = null;
        String query = "SELECT id, full_name, address, mobile_no, email, account_no, account_type, dob, initial_balance, account_status FROM customers WHERE id = ?";
        
        try (Connection con = getConnection();
             PreparedStatement pst = con.prepareStatement(query)) {
            pst.setString(1, id);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    customer = new Customer();
                    customer.setId(rs.getInt("id"));
                    customer.setFullName(rs.getString("full_name"));
                    customer.setAddress(rs.getString("address"));
                    customer.setMobileNo(rs.getString("mobile_no"));
                    customer.setEmail(rs.getString("email"));
                    customer.setAccountNo(rs.getString("account_no"));
                    customer.setAccountType(rs.getString("account_type"));
                    customer.setDob(rs.getString("dob"));
                    customer.setInitialBalance(rs.getDouble("initial_balance"));
                    customer.setAccountStatus(rs.getString("account_status"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customer;
    }
    public boolean userExists(String username) throws SQLException {
        String checkUserSQL = "SELECT * FROM banking_apps.users WHERE username = ?";
        try (Connection con = getConnection();
             PreparedStatement checkUserStmt = con.prepareStatement(checkUserSQL)) {
            checkUserStmt.setString(1, username);
            try (ResultSet rs = checkUserStmt.executeQuery()) {
                return rs.next();
            }
        }
    }

    public void insertUser(String username, String hashedPassword) throws SQLException {
        String insertUserSQL = "INSERT INTO banking_apps.users (username, password, role) VALUES (?, ?, ?)";
        try (Connection con = getConnection();
             PreparedStatement insertUserStmt = con.prepareStatement(insertUserSQL)) {
            insertUserStmt.setString(1, username);
            insertUserStmt.setString(2, hashedPassword);
            insertUserStmt.setString(3, "user");
            insertUserStmt.executeUpdate();
        }
    }
    
    public void updateAccountStatus(String accountNo, String status) throws SQLException {
        String updateStatusSQL = "UPDATE customers SET account_status = ? WHERE account_no = ?";
        try (Connection con = getConnection();
             PreparedStatement pst = con.prepareStatement(updateStatusSQL)) {
            pst.setString(1, status);
            pst.setString(2, accountNo);
            pst.executeUpdate();
        }
    }
}
