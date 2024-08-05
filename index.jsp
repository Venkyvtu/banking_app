<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="refresh" content="5;url=login.jsp"> <!-- Redirect to login.jsp after 5 seconds -->
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Welcome to the Bank</title>
    <style>
        body {
            background-image: url('https://static.vecteezy.com/system/resources/previews/010/518/833/non_2x/digital-finance-and-banking-investment-service-on-microchip-with-cloud-computing-in-futuristic-background-bank-building-with-online-payment-secure-money-and-financial-innovation-technology-vector.jpg');
            background-size: cover;
            background-position: center;
            height: 100vh;
            margin: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            font-family: Arial, sans-serif;
            color: #fff;
        }
        .message {
            text-align: center;
            font-size: 24px;
        }
        .bank-info {
            background: rgba(0, 0, 0, 0.5);
            padding: 20px;
            border-radius: 10px;
            max-width: 600px;
        }
        .bank-info h1 {
            margin-top: 0;
            font-size: 36px;
        }
        .bank-info p {
            font-size: 18px;
            line-height: 1.6;
        }
    </style>
</head>
<body>
<% response.setHeader("Cache-Control", "no-cache , no-store, must-revalidate"); 
	response.setHeader("Expires","0"); %>
    <div class="message">
        <div class="bank-info">
            <h1>Welcome to Overseas VTU Bank</h1>
            <p>We provide secure and innovative banking solutions tailored to meet your financial needs.</p>
            <p>Explore our services, manage your accounts, and enjoy seamless banking experiences online.</p>
            <p>Redirecting you to the login page...</p>
        </div>
    </div>
</body>
</html>
