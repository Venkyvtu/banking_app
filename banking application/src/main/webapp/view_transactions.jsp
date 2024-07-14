<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>View Transactions</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f0f0f0;
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
    <div class="container">
        <button class="download-btn" onclick="window.location.href='DownloadTransactionServlet'">Download Transactions</button>
        <h1><i class="fas fa-history"></i> View Transactions</h1>
        <table>
            <thead>
                <tr>
                    <th>Transaction ID</th>
                    <th>Transaction Date</th>
                    <th>Transaction Type</th>
                    <th>Amount</th>
                    <th>Balance</th>
                </tr>
            </thead>
            <tbody>
                <%
                    String accountNumber = (String) session.getAttribute("accountNumber");
                    try {
                        Class.forName("com.mysql.jdbc.Driver");
                        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/banking_apps", "root", "root");
                        PreparedStatement ps = con.prepareStatement("SELECT * FROM transactions WHERE account_no = ? ORDER BY transaction_date DESC LIMIT 10");
                        ps.setString(1, accountNumber);
                        ResultSet rs = ps.executeQuery();
                        while (rs.next()) {
                %>
                <tr>
                    <td><%= rs.getString("transaction_id") %></td>
                    <td><%= rs.getTimestamp("transaction_date") %></td>
                    <td><%= rs.getString("transaction_type") %></td>
                    <td><%= rs.getFloat("amount") %></td>
                    <td><%= rs.getFloat("balance") %></td>
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
