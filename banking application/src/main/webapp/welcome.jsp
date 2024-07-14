<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Banking Application - Welcome</title>
    <style>
        /* General Styles */
        body {
            font-family: Arial, sans-serif;
            background-color: #f0f0f0;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            min-height: 100vh;
        }
        .container {
            text-align: center;
            background-color: #fff;
            padding: 50px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            max-width: 600px;
            width: 90%;
        }
        h1 {
            margin-bottom: 20px;
            color: #007bff;
        }
        a {
            display: inline-block;
            margin: 10px;
            padding: 10px 20px;
            text-decoration: none;
            color: #fff;
            background-color: #007bff;
            border-radius: 4px;
            transition: background-color 0.3s ease;
        }
        a:hover {
            background-color: #0056b3;
        }

        /* Header Styles */
        header {
            width: 100%;
            background-color: #007bff;
            color: #fff;
            padding: 10px;
            position: fixed;
            top: 0;
            left: 0;
            display: flex;
            justify-content: space-between;
            align-items: center;
            text-align: center;
            box-sizing: border-box;
            z-index: 1000; /* Ensure header stays on top */
        }
        header label {
            margin-right: auto; /* Pushes the label to the left */
            font-weight: bold;
        }
        header a {
            color: #fff;
            margin-left: 20px;
            padding: 8px 16px;
            border-radius: 4px;
            transition: background-color 0.3s ease;
        }
        header a:hover {
            background-color: #0056b3;
        }

        /* Responsive Adjustments */
        @media (max-width: 768px) {
            .container {
                padding: 30px;
            }
            header {
                flex-wrap: wrap;
                justify-content: center;
                padding: 10px;
            }
            header label {
                margin-bottom: 10px;
            }
        }
    </style>
</head>
<body>
    <header>
        <label>Welcome, ${sessionScope.username}</label>
        <a href="Logoutservlet">Logout</a>
    </header>

    <div class="container">
        <h1>Welcome to Banking Application</h1>
        <p>This application allows you to manage customer details, transactions, and more.</p>
        <div>
            <a href="customeCheck.jsp">Customer Registration</a>
            <a href="customer_details.jsp">View Customer Details</a>
            <a href="delete_customer.jsp">Delete Customer Details</a>
        </div>
    </div>
</body>
</html>
