<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="model.UserLoginResult" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>User Profile</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
    <link href="https://cdn.jsdelivr.net/npm/boxicons@2.1.0/css/boxicons.min.css" rel="stylesheet">
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
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            width: 90%;
            max-width: 500px;
            box-sizing: border-box;
            display: flex;
            flex-direction: column;
            align-items: center;
        }
        h1 {
            margin-bottom: 20px;
            font-size: 24px;
            color: #333;
            text-align: center;
        }
        .info {
            margin-bottom: 20px;
            font-size: 18px;
            color: #666;
            text-align: center;
        }
        .info p {
            margin: 15px 0;
            display: flex;
            align-items: center;
        }
        .info p .icon {
            margin-right: 15px;
            color: #555;
        }
        @media (max-width: 500px) {
            .container {
                padding: 15px;
            }
            h1 {
                font-size: 20px;
            }
            .info {
                font-size: 16px;
            }
        }
    </style>
</head>
<body>
<% response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
   response.setHeader("Expires", "0"); %>
<div class="container">
    <h1><i class="fas fa-chart-line icon"></i> User Profile</h1>
    <div class="info">
        <p><i class='bx bxs-user icon'></i> <strong>Full Name:</strong> ${sessionScope.fullName}</p>
        <p><i class='bx bxs-phone icon'></i> <strong>Mobile Number:</strong> ${sessionScope.mobile_no}</p>
        <p><i class='bx bxs-calendar icon'></i> <strong>DOB:</strong> ${sessionScope.dob}</p>
        <p><i class='bx bxs-envelope icon'></i> <strong>Email:</strong> ${sessionScope.email}</p>
        <p><i class='bx bxs-dollar-circle icon'></i> <strong>Balance:</strong> ${sessionScope.balance}</p>
        <p><i class='bx bxs-id-card icon'></i> <strong>Account Number:</strong> ${sessionScope.accountNumber}</p>
        <p><i class='bx bxs-map icon'></i> <strong>Address:</strong> ${sessionScope.address}</p>
    </div>
</div>
</body>
</html>
