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
            " (full_name, address, mobile_no, email, account_type, initial_balance, dob, id_proof_path, account_no) VALUES " +
            " (?, ?, ?, ?, ?, ?, ?, ?, ?);";

    private static final String INSERT_USER_SQL = "INSERT INTO banking_apps.users (username, password, role) VALUES (?, ?, ?);";

    private static final String CHECK_ACCOUNT_NUMBER_SQL = "SELECT count(*) FROM customers WHERE account_no = ?;";

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
             PreparedStatement userStatement = connection.prepareStatement(INSERT_USER_SQL)) {

            // Insert into customers table
            customerStatement.setString(1, customer.getFullName());
            customerStatement.setString(2, customer.getAddress());
            customerStatement.setString(3, customer.getMobileNo());
            customerStatement.setString(4, customer.getEmail());
            customerStatement.setString(5, customer.getAccountType());
            customerStatement.setDouble(6, customer.getInitialBalance());
            customerStatement.setDate(7, java.sql.Date.valueOf(customer.getDob()));
            customerStatement.setString(8, customer.getIdProof());
            customerStatement.setString(9, accountNumber);
            customerStatement.executeUpdate();

            // Insert into users table
            userStatement.setString(1, accountNumber);
            userStatement.setString(2, hashedPassword);
            userStatement.setString(3, "user");
            userStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
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
