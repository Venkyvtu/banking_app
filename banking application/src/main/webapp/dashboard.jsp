<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<%@ page import="java.nio.file.Paths" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Dashboard</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f0f0f0;
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
            margin: 10px 0;
        }
        .profile-image {
            width: 150px;
            height: 150px;
            object-fit: cover;
            border-radius: 50%;
            border: 4px solid #ddd;
        }
        .icon {
            color: #555;
            margin-right: 10px;
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
    <div class="container">
        <h1><i class="fas fa-chart-line icon"></i> Dashboard</h1>
        <div class="info">
			<p><i class="fas fa-user icon"></i> <strong>Full Name:</strong> ${sessionScope.fullName}</p>
            <p><i class="fas fa-id-card icon"></i> <strong>Account Number:</strong> ${sessionScope.accountNumber}</p>
            <p><i class="fas fa-coins icon"></i> <strong>Balance:</strong> ${sessionScope.balance}</p>
        </div>
    </div>
</body>
</html>
