<%@ page language="java" contentType="text/plain; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<%
    String destinationAccount = request.getParameter("destinationAccount");

    // Database connection parameters
    String jdbcUrl = "jdbc:mysql://localhost:3306/customer_details";
    String dbUsername = "root";
    String dbPassword = "root";

    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    String fullName = "";

    try {
        Class.forName("com.mysql.jdbc.Driver");
        con = DriverManager.getConnection(jdbcUrl, dbUsername, dbPassword);
        ps = con.prepareStatement("SELECT full_name FROM customers WHERE account_no = ?");
        ps.setString(1, destinationAccount);
        rs = ps.executeQuery();

        if (rs.next()) {
            fullName = rs.getString("full_name");
        } else {
            fullName = "Account not found";
        }
    } catch (Exception e) {
        e.printStackTrace();
        fullName = "Error fetching account name";
    } finally {
        try {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
            if (con != null) con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    out.println(fullName);
%>
