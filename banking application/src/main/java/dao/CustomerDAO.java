package dao;

import model.Customer;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;



public class CustomerDAO {
    private String jdbcUrl = "jdbc:mysql://localhost:3306/customer_details";
    private String jdbcUsername = "root";
    private String jdbcPassword = "root";
    private String jdbcDriver = "com.mysql.cj.jdbc.Driver";
    
    private static final String INSERT_CUSTOMER_SQL = "INSERT INTO customers" +
            " (full_name, address, mobile_no, email, account_type, initial_balance, dob, id_proof_type, account_no,id_proof_number,gender) VALUES " +
            " (?, ?, ?, ?, ?, ?, ?, ?, ?,?,?);";

    private static final String INSERT_USER_SQL = "INSERT INTO banking_apps.users (username, password, role) VALUES (?, ?, ?);";

    private static final String CHECK_ACCOUNT_NUMBER_SQL = "SELECT count(*) FROM customers WHERE account_no = ?;";
    private static final String Trans_NUMBER_SQL = "INSERT INTO banking_apps.transactions (account_no, transaction_date, transaction_type, amount, balance) VALUES (?, NOW(), ?, ?, ?);";

    public CustomerDAO() {}

    protected Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName(jdbcDriver);
            connection = DriverManager.getConnection(jdbcUrl, jdbcUsername, jdbcPassword);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }

    private String hashPassword(String dob) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashInBytes = md.digest(dob.getBytes());

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

    private String generateUniqueAccountNumber() throws SQLException {
        Random random = new Random();
        String accountNumber;
        boolean isUnique;
        try (Connection connection = getConnection();
             PreparedStatement checkStatement = connection.prepareStatement(CHECK_ACCOUNT_NUMBER_SQL)) {
            do {
                accountNumber = String.valueOf(random.nextInt(900000000) + 100000000);
                checkStatement.setString(1, accountNumber);
                try (ResultSet resultSet = checkStatement.executeQuery()) {
                    resultSet.next();
                    isUnique = resultSet.getInt(1) == 0;
                }
            } while (!isUnique);
        }
        return accountNumber;
    }

    public void registerCustomer(Customer customer) throws SQLException {
        String accountNumber = generateUniqueAccountNumber();

        // Hash password using SHA-256
        String dobAsString = customer.getDob().toString();
        String hashedPassword = hashPassword(dobAsString);

        try (Connection connection = getConnection();
             PreparedStatement customerStatement = connection.prepareStatement(INSERT_CUSTOMER_SQL);
             PreparedStatement userStatement = connection.prepareStatement(INSERT_USER_SQL);
        		PreparedStatement transactionStatement = connection.prepareStatement(Trans_NUMBER_SQL))
        {
        	String subject = "Welcome to Our Bank - Your Account Details";
            String body = "Dear " + customer.getFullName() + ",\n\n" +
                          "Thank you for registering with our bank. Here are your account details:\n\n" +
                          "Account Number: " + accountNumber + "\n" +
                          "Password: " + customer.getDob() + "\n\n" +
                          "Please keep this information secure.\n\n" +
                          "Best regards,\n" +
                          "Your Bank";
            EmailUtil.sendEmail(customer.getEmail(), subject, body);

            // Insert into customers table
            customerStatement.setString(1, customer.getFullName());
            customerStatement.setString(2, customer.getAddress());
            customerStatement.setString(3, customer.getMobileNo());
            customerStatement.setString(4, customer.getEmail());
            customerStatement.setString(5, customer.getAccountType());
            customerStatement.setDouble(6, customer.getInitialBalance());
            customerStatement.setDate(7, java.sql.Date.valueOf(customer.getDob()));
            customerStatement.setString(8, customer.getIdProofType());
            customerStatement.setString(9, accountNumber);
            customerStatement.setString(10, customer.getIdProofNumber());
            customerStatement.setString(11, customer.getGender());
            customerStatement.executeUpdate();

            // Insert into users table
            userStatement.setString(1, accountNumber);
            userStatement.setString(2, hashedPassword);
            userStatement.setString(3, "user");
            userStatement.executeUpdate();
            
            
            transactionStatement.setString(1, accountNumber); 
            transactionStatement.setString(2, "Deposit");
            transactionStatement.setDouble(3, customer.getInitialBalance());
            transactionStatement.setDouble(4, customer.getInitialBalance());
            transactionStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        }
    }
    public double getCurrentBalance(String accountNo) throws SQLException {
        String query = "SELECT initial_balance FROM customers WHERE account_no=?";
        try (Connection connection = getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, accountNo);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getDouble("initial_balance");
                }
            }
        }
        return -1; // Indicate that balance retrieval failed
    }

    public boolean updateBalance(String accountNo, double newBalance) throws SQLException {
        String query = "UPDATE customers SET initial_balance=? WHERE account_no=?";
        try (Connection connection = getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setDouble(1, newBalance);
            stmt.setString(2, accountNo);
            int rowsUpdated = stmt.executeUpdate();
            return rowsUpdated > 0;
        }
    }

    public void insertTransaction(String accountNo, String transactionType, double amount, double balance) throws SQLException {
        String query = "INSERT INTO banking_apps.transactions (account_no, transaction_date, transaction_type, amount, balance) VALUES (?, NOW(), ?, ?, ?)";
        try (Connection connection = getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, accountNo);
            stmt.setString(2, transactionType);
            stmt.setDouble(3, amount);
            stmt.setDouble(4, balance);
            stmt.executeUpdate();
        }
    }
    

    private void printSQLException(SQLException ex) {
        for (Throwable e : ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }
}
