<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Search Customer</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/boxicons/2.0.7/css/boxicons.min.css">
    <link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css' rel='stylesheet'>
    <style>
        body {
            font-family: Arial, sans-serif;
             /* Background blur effect */
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }

        .container {
            max-width: 400px;
            background-color: rgba(255, 255, 255, 0.8); /* Semi-transparent background */
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.3);
            text-align: center;
        }

        h2 {
            margin-bottom: 20px;
            color: #007bff;
        }

        form {
            max-width: 100%;
            margin: 0 auto;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            background-color: rgba(255, 255, 255, 0.9); /* Semi-transparent background */
        }

        label, input {
            display: block;
            margin: 10px auto;
        }

        input[type="text"] {
            width: calc(100% - 22px);
            padding: 10px;
            border: 1px solid #ccc;
            border-radius: 4px;
            font-size: 16px;
        }

        button {
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

        button:hover {
            background-color: #0056b3;
        }


        .link-container {
            text-align: center;
            margin-top: 20px;
        }

        .link-container a {
            display: inline-block;
            background-color: #007bff;
            color: white;
            padding: 10px 20px;
            text-decoration: none;
            border-radius: 4px;
            transition: background-color 0.3s ease;
        }

        .link-container a:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>
<% response.setHeader("Cache-Control", "no-cache , no-store, must-revalidate"); 
	response.setHeader("Expires","0"); %>
    <div class="container">
        <h2>Search Customer</h2>
        <form id="searchForm" action="SearchCustomerServlet" method="post" onsubmit="return fetchCustomerDetails()">
           <label for="accountNo"> <i class='bx bxs-user-account'></i>  Account Number:</label>
            <input type="text" id="accountNo" name="accountNo" required>
            <button type="submit"><i class='bx bx-search'></i> Search</button>
        </form>
        <div id="customerDetails" class="customer-details"></div>

        <div class="link-container">
            <a href="customer_registration.jsp"><i class='bx bx-user-plus'></i> New Customer Registration</a>
        </div>
    </div>
</body>
</html>
