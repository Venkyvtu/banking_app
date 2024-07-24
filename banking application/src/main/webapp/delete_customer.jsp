<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Search Customer</title>
    <style>
        body {
    font-family: Arial, sans-serif;
    margin: 0;
    padding: 20px;
    text-align: center;
 /* Light background for contrast */
}

.container {
    max-width: 500px;
    margin: 0 auto;
    background-color: #fff;
    padding: 20px;
    border-radius: 8px;
    box-shadow: 0 4px 10px rgba(0, 0, 0, 0.2); /* Slightly stronger shadow */
    transform: translateY(-10px); /* Moves the container upwards */
    transition: all 0.3s ease; /* Smooth transition for hover effect */
}

.container:hover {
    transform: translateY(-15px); /* More lift on hover */
    box-shadow: 0 8px 20px rgba(0, 0, 0, 0.3); /* More pronounced shadow on hover */
}

form {
    margin-bottom: 20px;
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

table {
    width: 100%;
    border-collapse: collapse;
    margin-top: 20px;
}

table, th, td {
    border: 1px solid #ddd;
    padding: 8px;
    text-align: left;
}

th {
    background-color: #f2f2f2;
}

.error-message {
    color: red;
    margin-top: 10px;
    text-align: center;
}

    </style>
</head>
<body>
<% response.setHeader("Cache-Control", "no-cache , no-store, must-revalidate"); 
	response.setHeader("Expires","0"); %>
	    <div class="container">
        <h2>Search Customer by Account Number</h2>
        <form action="deleteaccount" method="post">
            <label for="accountNo">Account Number:</label>
            <input type="text" id="accountNo" name="accountNo" required>
            <button type="submit">Search</button>
        </form>
        <div id="customerDetails">
            <!-- Customer details will be displayed dynamically here -->
        </div>
    </div>

    <script>
        // Ensure history is maintained properly
        history.pushState(null, null, document.URL);
        window.addEventListener('popstate', function () {
            history.pushState(null, null, document.URL);
        });
    </script>
</body>
</html>
