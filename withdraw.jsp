<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*, java.io.*, javax.servlet.*" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Withdraw</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
    <style>
        body {
             font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }
        .container {
            background-color: #fff;
            padding: 30px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            text-align: center;
            max-width: 400px;
            margin: auto;
        }
        h1 {
            margin-bottom: 20px;
            color: #007bff;
        }
        input[type="number"], input[type="submit"] {
            padding: 12px;
            margin: 10px 0;
            border: 1px solid #ccc;
            border-radius: 4px;
            width: 100%;
            font-size: 16px;
            box-sizing: border-box;
        }
        input[type="submit"] {
            background-color: #007bff;
            color: white;
            cursor: pointer;
            transition: background-color 0.3s;
        }
        input[type="submit"]:hover {
            background-color: #0056b3;
        }
        .error-message {
            color: red;
            margin-top: 10px;
        }
        .info {
            margin-top: 20px;
            font-size: 14px;
            color: #666;
        }
        .info p {
            margin: 5px 0;
        }
        .info i {
            margin-right: 5px;
            color: #555;
        }
        @media (max-width: 500px) {
            .container {
                padding: 20px;
            }
        }
    </style>
</head>
<body>
<% response.setHeader("Cache-Control", "no-cache , no-store, must-revalidate"); 
	response.setHeader("Expires","0"); %>
	    <div class="container">
        <h1><i class="fas fa-hand-holding-usd"></i> Withdraw</h1>
        <form action="WithdrawServlet" method="post">
            <label for="amount">Enter withdraw amount:</label>
            <br>
            <input type="number" id="amount" name="amount" min="0" step="0.01" required>
            <br>
            <input type="submit" value="Withdraw">
        </form>
        <%-- Error message handling --%>
        <% String errorMessage = (String) request.getAttribute("errorMessage");
           if (errorMessage != null) { %>
               <div class="error-message"><%= errorMessage %></div>
        <% } %>
        <div class="info">
            <p><i class="fas fa-info-circle"></i> Please enter the amount you wish to withdraw from your account.</p>
            <p><i class="fas fa-info-circle"></i> Ensure the amount entered is within your available balance.</p>
        </div>
    </div>
</body>
</html>
