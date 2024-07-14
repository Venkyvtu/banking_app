<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Customer Registration</title>
    <style>
        body {
            font-family: Arial, sans-serif;
        }
        form {
            width: 300px;
            margin: 0 auto;
        }
        label {
            display: block;
            margin-top: 10px;
        }
        input, select {
            width: 100%;
            padding: 5px;
            margin-top: 5px;
        }
        input[type="submit"] {
            width: auto;
            margin-top: 20px;
        }
    </style>
</head>
<body>
    <h2>Customer Registration</h2>
    <form action="registerCustomerServlet" method="post" enctype="multipart/form-data">
        <label for="fullName">Full Name:</label>
        <input type="text" id="fullName" name="fullName" required>

        <label for="address">Address:</label>
        <input type="text" id="address" name="address" required>

        <label for="mobileNo">Mobile No:</label>
        <input type="text" id="mobileNo" name="mobileNo" pattern="[0-9]{10}" required>

        <label for="email">Email ID:</label>
        <input type="email" id="email" name="email" required>

        <label for="accountType">Type of Account:</label>
        <select id="accountType" name="accountType" required>
            <option value="Savings">Savings Account</option>
            <option value="Current">Current Account</option>
        </select>

        <label for="initialBalance">Initial Balance (min 1000):</label>
        <input type="number" id="initialBalance" name="initialBalance" min="1000" required>

        <label for="dob">Date of Birth:</label>
        <input type="date" id="dob" name="dob" required>

        <label for="idProof">ID Proof:</label>
        <input type="file" id="idProof" name="idProof" accept=".jpg, .jpeg, .png, .pdf" required>

        <input type="submit" value="Register">
    </form>
</body>
</html>
