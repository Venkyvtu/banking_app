<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Transfer Amount</title>
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
        input[type="text"], input[type="number"], button {
            width: calc(100% - 22px);
            padding: 12px;
            margin: 10px 0;
            border: 1px solid #ccc;
            border-radius: 4px;
            font-size: 16px;
            box-sizing: border-box;
        }
        input[type="text"][disabled] {
            background-color: #f0f0f0;
        }
        button {
            background-color: #007bff;
            color: white;
            cursor: pointer;
            transition: background-color 0.3s;
        }
        button:hover {
            background-color: #0056b3;
        }
        .info {
            margin-top: 20px;
            font-size: 14px;
            color: #666;
            text-align: left;
        }
        .info p {
            margin: 5px 0;
            line-height: 1.4;
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
    <script>
    
        function fetchAccountName() {
            var destinationAccount = document.getElementById("destinationAccount").value;
            var xmlhttp = new XMLHttpRequest();
            xmlhttp.onreadystatechange = function() {
                if (this.readyState == 4 && this.status == 200) {
                    document.getElementById("accountName").innerHTML = this.responseText;
                }
            };
            xmlhttp.open("GET", "getAccountName.jsp?destinationAccount=" + destinationAccount, true);
            xmlhttp.send();
        }
    </script>
</head>
<body>
<% response.setHeader("Cache-Control", "no-cache , no-store, must-revalidate"); 
	response.setHeader("Expires","0"); %>
    <div class="container">
        <h1><i class="fas fa-exchange-alt"></i> Transfer Amount</h1>
        <form action="transferAmountServlet" method="post">
            <input type="text" name="sourceAccount" placeholder="Source Account Number" value="${sessionScope.accountNumber}" disabled required>
            <input type="text" id="destinationAccount" name="destinationAccount" placeholder="Destination Account Number" oninput="fetchAccountName()" required>
            <label id="accountName" class="info"></label>
            <input type="number" name="amount" placeholder="Amount" required>
            <button type="submit">Transfer</button>
        </form>
        <div class="info">
            <p><i class="fas fa-info-circle"></i> Please enter the destination account number and amount to transfer.</p>
            <p><i class="fas fa-info-circle"></i> The source account number is pre-filled based on your session.</p>
        </div>
    </div>
</body>
</html>
