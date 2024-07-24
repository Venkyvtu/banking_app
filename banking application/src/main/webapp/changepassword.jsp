<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Change Password</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/boxicons/2.1.0/css/boxicons.min.css">
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f2f2f2;
            margin: 20px;
            padding: 0;
            text-align: center;
        }

        form {
            margin: 20px auto;
            max-width: 400px;
            background-color: #fff;
            padding: 30px;
            border-radius: 10px;
            box-shadow: 0 0 20px rgba(0, 0, 0, 0.1);
        }

        label, input {
            display: block;
            margin: 10px auto;
            width: calc(100% - 20px);
        }

        input[type="password"] {
            padding: 12px;
            border: 1px solid #ccc;
            border-radius: 4px;
            font-size: 16px;
            transition: border-color 0.3s ease;
        }

        input[type="password"]:focus {
            outline: none;
            border-color: #007bff;
        }

        button[type="submit"] {
            width: 100%;
            padding: 12px 20px;
            background-color: #007bff;
            color: white;
            border: none;
            border-radius: 4px;
            font-size: 16px;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }

        button[type="submit"]:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>
<% response.setHeader("Cache-Control", "no-cache , no-store, must-revalidate"); 
	response.setHeader("Expires","0"); %>
    <form action="ChangePasswordServlet" method="post">
        <label for="newPassword"><i class='bx bxs-lock'></i> New Password:</label>
        <input type="password" id="newPassword" name="newPassword" required>
        <button type="submit"><i class='bx bx-key'></i> Change Password</button>
    </form>

    <script src="https://cdnjs.cloudflare.com/ajax/libs/boxicons/2.1.0/boxicons.min.js"></script>
</body>
</html>
