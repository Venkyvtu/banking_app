<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Login</title>
<style>
    body {
        font-family: Arial, sans-serif;
        background-color: #f9f9f9;
        margin: 20px;
        text-align: center;
    }
    h1 {
        color: #333;
    }
    form {
        width: 90%;
        max-width: 400px;
        margin: 0 auto;
        background-color: #fff;
        padding: 20px;
        border-radius: 8px;
        box-shadow: 0 0 10px rgba(0,0,0,0.1);
    }
    label {
        display: block;
        text-align: left;
        margin-bottom: 8px;
        color: #555;
    }
    input[type="text"], input[type="password"] {
        width: calc(100% - 12px);
        padding: 10px;
        margin-bottom: 15px;
        border: 1px solid #ccc;
        border-radius: 4px;
        font-size: 16px;
    }
    input[type="submit"] {
        background-color: #007bff;
        color: white;
        border: none;
        padding: 12px 20px;
        text-align: center;
        text-decoration: none;
        display: inline-block;
        font-size: 16px;
        border-radius: 4px;
        cursor: pointer;
        transition: background-color 0.3s ease;
    }
    input[type="submit"]:hover {
        background-color: #0056b3;
    }
    .error-message {
        color: red;
        margin-top: 10px;
        text-align: center;
    }
</style>
</head>
<body>
    <h1>Login</h1>
    <form action="LoginServlet" method="post">
        <label for="user">User name:</label>
        <input type="text" id="user" name="user" placeholder="Enter user ID" required>
        <br><br>
        <label for="password">Password:</label>
        <input type="password" id="password" name="password" placeholder="Enter password" required>
        <br><br>
        <input type="submit" name="login" value="Login">
        <div class="error-message">
            <%-- Display error message here if needed --%>
        </div>
    </form>
</body>
</html>
