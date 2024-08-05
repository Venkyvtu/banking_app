<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Close Account</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
    <style>
        body {
            font-family: Arial, sans-serif;
            background: url('https://www.intelligentcio.com/me/wp-content/uploads/sites/12/2019/06/Digital-banking-web-pic.jpg') no-repeat center center fixed;
			            background-size: cover;
            	
			margin: 0;
            padding: 20px;
        }
        .container {
            background-color: #fff;
            padding: 30px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            max-width: 600px;
            margin: auto;
        }
        h1 {
            margin-bottom: 20px;
            color: #007bff;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin-bottom: 20px;
        }
        table, th, td {
            border: 1px solid #ddd;
        }
        th, td {
            padding: 10px;
            text-align: left;
        }
        th {
            background-color: #007bff;
            color: white;
        }
        .action-button {
            background-color: #007bff;
            color: white;
            border: none;
            padding: 10px 20px;
            text-align: center;
            text-decoration: none;
            display: inline-block;
            font-size: 16px;
            margin: 4px 2px;
            cursor: pointer;
            border-radius: 4px;
        }
        .action-button:hover {
            background-color: #0056b3;
        }
        .info {
            margin-top: 10px;
            font-size: 14px;
            color: #666;
            line-height: 1.4;
        }
        .info i {
            margin-right: 5px;
            color: #555;
        }
    </style>
</head>
<body>
<% response.setHeader("Cache-Control", "no-cache , no-store, must-revalidate"); 
	response.setHeader("Expires","0"); %>
    <div class="container">
        <h1><i class="fas fa-user-slash"></i> Account Details</h1>
        <table>
            <tbody>
                <%
                    String accountNumber = (String) session.getAttribute("accountNumber");
                    try {
                        Class.forName("com.mysql.jdbc.Driver");
                        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/customer_details", "root", "root");
                        PreparedStatement ps = con.prepareStatement("SELECT * FROM customers WHERE account_no = ?");
                        ps.setString(1, accountNumber);
                        ResultSet rs = ps.executeQuery();
                        if (rs.next()) {
                %>
                <tr>
                    <th>Full Name</th>
                    <td colspan="2"><%= rs.getString("full_name") %></td>
                </tr>
                <tr>
                    <th>Address</th>
                    <td colspan="2"><%= rs.getString("address") %></td>
                </tr>
                <tr>
                    <th>Mobile No</th>
                    <td colspan="2"><%= rs.getString("mobile_no") %></td>
                </tr>
                <tr>
                    <th>Email</th>
                    <td colspan="2"><%= rs.getString("email") %></td>
                </tr>
                <tr>
                    <th>Account No</th>
                    <td colspan="2"><%= rs.getString("account_no") %></td>
                </tr>
                <tr>
                    <th>Account Type</th>
                    <td colspan="2"><%= rs.getString("account_type") %></td>
                </tr>
                <tr>
                    <th>Initial Balance</th>
                    <td><%= rs.getFloat("initial_balance") %></td>
                    <td>
                        
                           <a href="withdraw.jsp"> <button type="submit" class="action-button">Withdraw</button></a>
                        
                    </td>
                </tr>
                <tr>
                    <th>Date of Birth</th>
                    <td colspan="2"><%= rs.getString("dob") %></td>
                </tr>
                <tr>
                    <td colspan="3" style="text-align: center;">
                        <form action="CloseAccountServlet" method="post" onsubmit="return confirm('Are you sure you want to close your account? This action cannot be undone.');">
                            <input type="hidden" name="accountId" value="<%= rs.getString("account_no") %>">
                            <button type="submit" class="action-button" style="background-color: #dc3545;">Close Account</button>
                        </form>
                    </td>
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
        <div class="info">
            <p><i class="fas fa-info-circle"></i> Please review your account details before closing your account.</p>
            <p><i class="fas fa-info-circle"></i> Once closed, your account cannot be re-opened.</p>
        </div>
    </div>
    <script>
        history.pushState(null, null, document.URL);
        window.addEventListener('popstate', function () {
            history.pushState(null, null, document.URL);
        });
    </script>
</body>
</html>
