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
            background-color: #f0f0f0;
            margin: 0;
            padding: 20px;
        }
        h2 {
            text-align: center;
            color: #007bff;
        }
        form {
            max-width: 600px;
            margin: 0 auto;
            background-color: #fff;
            padding: 30px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        label {
            display: block;
            margin-top: 15px;
            color: #333;
        }
        input, select {
            width: calc(100% - 12px);
            padding: 10px;
            margin-top: 5px;
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
            margin-top: 20px;
        }
        input[type="submit"]:hover {
            background-color: #0056b3;
        }
        input[type="file"] {
            margin-top: 5px;
        }
        .form-group {
            margin-bottom: 20px;
        }
        .note {
            font-size: 14px;
            color: #777;
            margin-top: 5px;
        }
    </style>
</head>
<body>
    <h2>Customer Registration</h2>
    <form action="registerCustomerServlet" method="post" enctype="multipart/form-data">
        <div class="form-group">
            <label for="fullName">Full Name:</label>
            <input type="text" id="fullName" name="fullName" required>
        </div>

        <div class="form-group">
            <label for="address">Address:</label>
            <input type="text" id="address" name="address" required>
        </div>

        <div class="form-group">
            <label for="mobileNo">Mobile No:</label>
            <input type="text" id="mobileNo" name="mobileNo" pattern="[0-9]{10}" required>
            <span class="note">Please enter a 10-digit mobile number.</span>
        </div>

        <div class="form-group">
            <label for="email">Email ID:</label>
            <input type="email" id="email" name="email" required>
            <span class="note">We'll send your account details to this email.</span>
        </div>

        <div class="form-group">
            <label for="accountType">Type of Account:</label>
            <select id="accountType" name="accountType" required>
                <option value="">Select Account Type</option>
                <option value="Savings">Savings Account</option>
                <option value="Current">Current Account</option>
            </select>
        </div>

        <div class="form-group">
            <label for="initialBalance">Initial Balance (min 1000):</label>
            <input type="number" id="initialBalance" name="initialBalance" min="1000" required>
            <span class="note">Minimum initial deposit is ₹1000.</span>
        </div>

        <div class="form-group">
            <label for="dob">Date of Birth:</label>
            <input type="date" id="dob" name="dob" required>
        </div>

        <div class="form-group">
            <label for="idProof">Upload ID Proof:</label>
            <input type="file" id="idProof" name="idProof" accept=".jpg, .jpeg, .png, .pdf" required>
            <span class="note">Accepted formats: JPG, JPEG, PNG, PDF (max size: 2MB).</span>
        </div>

        <input type="submit" value="Register">
    </form>
</body>
</html>
