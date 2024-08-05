<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Search Customer</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/boxicons/2.1.0/css/boxicons.min.css">
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 20px;
            padding: 0;
            text-align: center;
            /* Light background color */
        }

        h2 {
            margin-bottom: 20px;
            font-size: 1.8rem;
        }

        form {
            margin: 20px auto;
            max-width: 400px;
            background-color: rgba(255, 255, 255, 0.9); /* Semi-transparent background */
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
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

        .error-message {
            color: red;
            margin-top: 10px;
            text-align: center;
        }

        /* Responsive Adjustments */
        @media (max-width: 768px) {
            form {
                max-width: 100%;
            }
            h2 {
                font-size: 1.5rem;
            }
        }
    </style>
</head>
<body>
<% response.setHeader("Cache-Control", "no-cache , no-store, must-revalidate"); 
	response.setHeader("Expires","0"); %>
    <div>
        
        <form id="searchForm" action="SearchCustomerServlet" method="post" onsubmit="return fetchCustomerDetails()">
        <h2><i class='bx bx-search-alt'></i> Search Customer by Account Number</h2>
            <label for="accountNo">Account Number:</label>
            <input type="text" id="accountNo" name="accountNo" required>
            <button type="submit"><i class='bx bx-search'></i> Search</button>
        </form>

        <div id="customerDetails" class="customer-details"></div>
    </div>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/boxicons/2.1.0/boxicons.min.js"></script>
</body>
</html>
