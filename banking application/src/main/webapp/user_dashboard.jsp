<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
    response.setHeader("Pragma", "no-cache");
    response.setDateHeader("Expires", 0);
    if (session.getAttribute("username") == null) {
        response.sendRedirect("login.jsp");
        return;
    }
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Customer Dashboard</title>
    <link href='https://unpkg.com/boxicons@2.1.0/css/boxicons.min.css' rel='stylesheet'>
    <style>
        body {
            font-family: Arial, sans-serif;
            background: url('https://www.intelligentcio.com/me/wp-content/uploads/sites/12/2019/06/Digital-banking-web-pic.jpg') no-repeat center center fixed;
            background-size: cover;
            margin: 0;
            padding: 0;
            display: flex;
            flex-direction: column;
            height: 100vh;
            overflow: hidden;
        }
        header {
            width: 100%;
            background-color: rgba(0, 123, 255, 0.8);
            color: #fff;
            padding: 1px;
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
            margin-right: auto;
            font-weight: bold;
        }
        header a {
            display: inline-block;
            text-decoration: none;
            color: #fff;
            background-color: rgba(0, 123, 255, 0.8);
            border-radius: 4px;
            padding: 8px 12px;
            margin-left: 20px;
            transition: background-color 0.3s ease;
        }
        header a:hover {
            background-color: #0056b3;
        }
        .sidebar {
            width: 200px;
            background-color: rgba(0, 123, 255, 0.8);
            color: #fff;
            padding-top: 60px; /* Adjust padding to move content below header */
            box-sizing: border-box;
            height: calc(100vh - 60px); /* Take full height minus header height */
            position: fixed;
            top: 60px;
            left: 0;
            overflow-y: auto; /* Enable scrolling if content exceeds sidebar height */
            transition: width 0.3s ease; /* Added transition for smooth width change */
        }
        .sidebar.collapsed {
            width: 60px; /* Collapsed width */
        }
        .sidebar a {
            display: flex;
            align-items: center;
            color: #fff;
            padding: 12px 20px; /* Increase padding for better touch targets */
            text-decoration: none;
            margin: 5px 0;
            transition: background-color 0.3s ease;
        }
        .sidebar a i {
            margin-right: 10px; /* Add space between icon and text */
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
        .menu-btn {
            display: none; /* Initially hide the three-dot menu */
        }
        .popup {
            display: none;
            position: fixed;
            top: 60px; /* Adjust based on header height */
            left: 0;
            background-color: rgba(0, 123, 255, 0.8);
            box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);
            z-index: 1001;
            width: 100%;
            padding: 20px;
            box-sizing: border-box;
            overflow-y: auto; /* Enable scrolling */
        }
        .popup a {
            display: block;
            padding: 12px 20px;
            color: #fff;
            text-decoration: none;
            transition: background-color 0.3s ease;
        }
        .popup a:hover {
            background-color: #0056b3;
        }
        @media (max-width: 768px) {
            .sidebar {
                width: 60px; /* Default width for collapsed state on small screens */
                overflow: hidden;
            }
            .sidebar.expanded {
                width: 200px; /* Expanded width when visible */
            }
            .menu-btn {
                display: block; /* Display the three-dot menu button */
                position: fixed;
                top: 10px;
                left: 10px;
                z-index: 1000;
                color: #fff;
                background-color: rgba(0, 123, 255, 0.8);
                border: none;
                border-radius: 50%;
                padding: 8px;
                cursor: pointer;
                transition: background-color 0.3s ease;
            }
            .menu-btn:hover {
                background-color: #0056b3;
            }
            .content {
                margin-left: 0; /* No margin for content on small screens */
            }
            .popup {
                width: 180px; /* Adjust width as needed */
                height: calc(100vh - 60px); /* Take full height minus header height */
                overflow-y: auto; /* Enable scrolling */
            }
        }
    </style>
</head>
<body>
<header>
    <label>Welcome, ${sessionScope.fullName}</label>
     <p id="datetime"></p>
    <a href="Logoutservlet">Logout</a>
</header>
<button class="menu-btn" id="menu-btn" onclick="toggleMenu()">
    <i class='bx bx-dots-horizontal-rounded'></i>
</button>
<div class="sidebar" id="sidebar">
    <a href="dashboard.jsp" target="contentFrame"><i class='bx bx-home'></i> Dashboard</a>
    <a href="view_transactions.jsp" target="contentFrame"><i class='bx bx-list-ul'></i> View Transactions</a>
    <a href="deposit.jsp" target="contentFrame"><i class='bx bx-dollar'></i> Deposit</a>
    <a href="withdraw.jsp" target="contentFrame"><i class='bx bx-money'></i> Withdraw</a>
    <a href="transfer_amount.jsp" target="contentFrame"><i class='bx bx-transfer'></i> Transfer Amount</a>
    <a href="close_account.jsp"><i class='bx bx-lock-alt'></i> Close Account</a>
</div>
<div class="content">
    <iframe name="contentFrame" src="dashboard.jsp"></iframe>
</div>
<div class="popup" id="popup">
    <a href="dashboard.jsp" target="contentFrame"><i class='bx bx-home'></i> Dashboard</a>
    <a href="view_transactions.jsp" target="contentFrame"><i class='bx bx-list-ul'></i> View Transactions</a>
    <a href="deposit.jsp" target="contentFrame"><i class='bx bx-dollar'></i> Deposit</a>
    <a href="withdraw.jsp" target="contentFrame"><i class='bx bx-money'></i> Withdraw</a>
    <a href="transfer_amount.jsp" target="contentFrame"><i class='bx bx-transfer'></i> Transfer Amount</a>
    <a href="close_account.jsp"><i class='bx bx-lock-alt'></i> Close Account</a>
</div>
<script src="https://cdnjs.cloudflare.com/ajax/libs/boxicons/2.1.0/boxicons.min.js"></script>
<script>
    function toggleMenu() {
        var sidebar = document.getElementById('sidebar');
        sidebar.classList.toggle('expanded');
    }
    
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
