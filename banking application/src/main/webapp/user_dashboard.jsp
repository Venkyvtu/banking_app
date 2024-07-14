<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Customer Dashboard</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f0f0f0;
            margin: 0;
            padding: 0;
            display: flex;
            flex-direction: column;
            height: 100vh;
            overflow: hidden;
        }
        header {
            width: 100%;
            background-color: #007bff;
            color: #fff;
            padding: 10px;
            box-sizing: border-box;
            display: flex;
            justify-content: space-between;
            align-items: center;
            position: fixed;
            top: 0;
            left: 0;
            z-index: 1000;
        }
        header label {
            margin-right: 20px;
        }
        header a {
            display: inline-block;
            text-decoration: none;
            color: #fff;
            background-color: #007bff;
            border-radius: 4px;
            padding: 8px 12px;
            margin-left: 20px;
        }
        header a:hover {
            background-color: #0056b3;
        }
        .sidebar {
            width: 200px;
            background-color: #007bff;
            color: #fff;
            padding-top: 60px; /* Adjust padding to move content below header */
            box-sizing: border-box;
            height: calc(100vh - 60px); /* Take full height minus header height */
            position: fixed;
            top: 60px;
            left: 0;
            overflow-y: auto; /* Enable scrolling if content exceeds sidebar height */
        }
        .sidebar a {
            display: block;
            color: #fff;
            padding: 12px 20px; /* Increase padding for better touch targets */
            text-decoration: none;
            margin: 5px 0;
        }
        .sidebar a:hover {
            background-color: #0056b3;
        }
        .content {
            margin-left: 200px; /* Move content right to accommodate sidebar */
            padding: 20px;
            box-sizing: border-box;
            flex: 1; /* Take remaining vertical space */
            overflow-y: auto; /* Enable scrolling if content exceeds viewport height */
        }
        iframe {
            width: 100%;
            height: 100%;
            border: none;
        }
        @media (max-width: 768px) {
            .sidebar {
                width: 100%;
                height: auto;
                position: relative;
                top: 0;
            }
            .content {
                margin-left: 0;
            }
        }
    </style>
</head>
<body>
<header>
    <label>Welcome, ${sessionScope.fullName}</label>
    <a href="login.jsp">Logout</a>
</header>
<div class="sidebar">
    <a href="dashboard.jsp" target="contentFrame">Dashboard</a>
    <a href="view_transactions.jsp" target="contentFrame">View Transactions</a>
    <a href="deposit.jsp" target="contentFrame">Deposit</a>
    <a href="withdraw.jsp" target="contentFrame">Withdraw</a>
    <a href="transfer_amount.jsp" target="contentFrame">Transfer Amount</a>
    <a href="close_account.jsp" >Close Account</a>
</div>
<div class="content">
    <iframe name="contentFrame" src="dashboard.jsp"></iframe>
</div>
</body>
</html>
