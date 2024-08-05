
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");// https 1.1
    response.setHeader("Pragma", "no-cache");
    response.setDateHeader("Expires", 0);
    HttpSession session1 = request.getSession(false);
    if ( session1 == null||session1.getAttribute("username") == null) {
        response.sendRedirect("login.jsp");
        return;
    }
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Banking Application - Welcome</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/boxicons/2.1.0/css/boxicons.min.css">
    <style>
        /* General Styles */
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            display: flex;
            flex-direction: column; /* Stack header and main content */
            min-height: 100vh;
            background: url('https://www.intelligentcio.com/me/wp-content/uploads/sites/12/2019/06/Digital-banking-web-pic.jpg') no-repeat center center fixed;
            background-size: cover;
        }
        header {
            width: 100%;
            background-color: rgba(0, 123, 255, 0.8); /* Semi-transparent background */
            color: #fff;
            padding: 10px;
            display: flex;
            justify-content: space-between;
            align-items: center;
            box-sizing: border-box;
        }
        header label {
            margin-right: auto;
            font-weight: bold;
        }
        header a {
            color: #fff;
            margin-left: 20px;
            padding: 8px 16px;
            border-radius: 4px;
            transition: background-color 0.3s ease;
            text-decoration: none;
        }
        header a:hover {
            background-color: #0056b3;
        }

        .main-content {
            display: flex;
            flex: 1; /* Take the remaining space */
            width: 100%;
        }
        .container {
            background-color: rgba(255, 255, 255, 0.9); /* Semi-transparent background */
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            width: 250px;
            min-width: 250px;
            text-align: center;
            margin: 20px; /* Add margin for spacing */
        }
        .container h1 {
            margin-bottom: 20px;
            color: #007bff;
        }
        .container a {
            display: block;
            margin: 10px 0;
            padding: 10px;
            text-decoration: none;
            color: #fff;
            background-color: #007bff;
            border-radius: 4px;
            transition: background-color 0.3s ease;
        }
        .container a:hover {
            background-color: #0056b3;
        }

        .iframe-container {
            flex: 1;
            display: flex;
            justify-content: center;
            align-items: center;
            padding: 20px;
        }
        iframe {
            width: 100%;
            height: 100%;
            border: none;
            min-height: 600px;
            background: rgba(255, 255, 255, 0); /* Fully transparent background */
        }

        /* Responsive Adjustments */
        @media (max-width: 768px) {
            .main-content {
                flex-direction: column;
                align-items: center; /* Center items on small screens */
            }
        }
    </style>
</head>
<body>

    <header>
        <label>Welcome, ${sessionScope.username}</label>
         <p id="datetime"></p>
        <a href="Logoutservlet"><i class='bx bx-log-out'></i> Logout</a>
    </header>

    <div class="main-content">
        <div class="container">
            <h1>Welcome to Overseas VTU Bank</h1>
            <p>This application allows you to manage customer details, transactions, and more.</p>
            <a href="customeCheck.jsp" target="contentFrame"><i class='bx bx-user-plus'></i> Customer Registration</a>
            <a href="customer_details.jsp" target="contentFrame"><i class='bx bx-user-detail'></i> View Customer Details</a>
            <a href="delete_customer.jsp" target="contentFrame"><i class='bx bx-user-x'></i> Delete Customer Details</a>
            <a href="all_customer.jsp" target="contentFrame"><i class='bx bx-user-x'></i> All Customer Details</a>
        </div>

        <div class="iframe-container">
            <iframe name="contentFrame"></iframe>
        </div>
    </div>
    <script>
    window.onload = function() {
        setInterval(function(){
            var date = new Date();
            var displayDate = date.toLocaleDateString();
            var displayTime = date.toLocaleTimeString();

            document.getElementById('datetime').innerHTML = displayDate + " " + displayTime;
        }, 1000); // 1000 milliseconds = 1 second
    }
    </script>
</body>
</html>
