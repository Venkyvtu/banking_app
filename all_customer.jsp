<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>View Customer Details</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 20px;
        }
        .container {
            background-color: #fff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            max-width: 100%;
            overflow-x: auto;
            position: relative;
        }
        h1 {
            margin-bottom: 20px;
            color: #333;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 10px;
        }
        th, td {
            padding: 12px 15px;
            text-align: left;
            border: 1px solid #ddd;
        }
        th {
            background-color: #007bff;
            color: white;
        }
        tr:nth-child(even) {
            background-color: #f2f2f2;
        }
        @media (max-width: 768px) {
            table {
                font-size: 14px;
            }
        }
        @media (max-width: 500px) {
            table {
                font-size: 12px;
            }
        }
        .download-btn {
            position: absolute;
            top: 20px;
            right: 20px;
            padding: 10px 20px;
            background-color: #28a745;
            color: white;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }
        .download-btn:hover {
            background-color: #218838;
        }
    </style>
</head>
<body>
<% response.setHeader("Cache-Control", "no-cache , no-store, must-revalidate"); 
	response.setHeader("Expires","0"); %>
    <div class="container">
        
        <h1><i class="fas fa-user"></i> View Customer Details</h1>
        <table>
            <thead>
                <tr>
                    <th>Account Number</th>
                    <th>Full Name</th>
                    <th>Address</th>
                    <th>Mobile No</th>
                    <th>Email</th>
                    <th>Date of Birth</th>
                    <th>Gender</th>
                </tr>
            </thead>
            <tbody>
                <%
                    String accountNumber = (String) session.getAttribute("accountNumber");
                    try {
                        Class.forName("com.mysql.jdbc.Driver");
                        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/customer_details", "root", "root");
                        PreparedStatement ps = con.prepareStatement("SELECT * FROM customers");
                        
                        ResultSet rs = ps.executeQuery();
                        while (rs.next()) {
                %>
                <tr>
                    <td><%= rs.getString("account_no") %></td>
                    <td><%= rs.getString("full_name") %></td>
                    <td><%= rs.getString("address") %></td>
                    <td><%= rs.getString("mobile_no") %></td>
                    <td><%= rs.getString("email") %></td>
                    <td><%= rs.getDate("dob") %></td>
                    <td><%= rs.getString("gender") %></td>
                </tr>
                <%
                        }
                        con.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                %>
            </tbody>
        </table>
    </div>
</body>
</html>
