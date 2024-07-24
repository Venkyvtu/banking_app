<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/boxicons/2.1.0/css/boxicons.min.css">
    <style>
        body {
            font-family: Arial, sans-serif;
            background-image:url('https://wallpaperbat.com/img/312976-free-download-log-on-to-your-bank-account-on-a-laptop-logging.png');
            background-size:cover;
            margin: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }
        .container {
            display: flex;
            justify-content: space-between;
            align-items: center;
            width: 90%;
            max-width: 1200px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            border-radius: 8px;
            overflow: hidden;
        }
        .image-side {
            flex: 1;
            background-size: cover;
            background-position: center;
            height: 100vh;
            width:600px;
        }
        .form-side {
            flex: 1;
            padding: 40px;
            box-sizing: border-box;
            text-align: center;
        }
        h1 {
            color: #333;
            margin-bottom: 20px;
        }
        form {
            max-width: 400px;
            margin: 0 auto;
        }
        label {
            display: block;
            text-align: left;
            margin-bottom: 8px;
            color: #555;
        }
        .password-container {
            position: relative;
            margin-bottom: 15px;
        }
        .password-toggle {
            position: absolute;
            top: 50%;
            right: 10px;
            transform: translateY(-50%);
            cursor: pointer;
            background: none;
            border: none;
            outline: none;
        }
        .password-toggle i {
            font-size: 1.2rem;
        }
        input[type="text"], input[type="password"] {
            width: calc(100% - 32px);
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
        /* Responsive Adjustments */
        @media (max-width: 1024px) {
            .container {
                flex-direction: column;
            }
            .image-side {
                height: 50vh;
                order: 2;
            }
            .form-side {
                height: 50vh;
                order: 1;
                padding: 20px;
            }
        }
    </style>
</head>
<body>
<% response.setHeader("Cache-Control", "no-cache , no-store, must-revalidate"); 
   response.setHeader("Expires","0"); %>
    <div class="container">
        <div class="image-side"></div>
        <div class="form-side">
            <h1><i class='bx bxs-user-circle'></i> Login</h1>
            <form action="LoginServlet" method="post">
                <label for="user"><i class='bx bx-user'></i> User name:</label>
                <input type="text" id="user" name="user" placeholder="Enter user ID" required>
                <div class="password-container">
                    <label for="password"><i class='bx bx-lock-alt'></i> Password:</label>
                    <input type="password" id="password" name="password" placeholder="Enter password" required>
                    <button type="button" class="password-toggle" onclick="togglePasswordVisibility()">
                        <i id="passwordToggleIcon" class='bx bx-hide'></i>
                    </button>
                </div>
                <input type="submit" name="login" value="Login">
                <div class="error-message">
                    <!-- Display any error messages here -->
                </div>
            </form>
        </div>
    </div>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/boxicons/2.1.0/boxicons.min.js"></script>
    <script>
        function togglePasswordVisibility() {
            var passwordInput = document.getElementById("password");
            var passwordToggleIcon = document.getElementById("passwordToggleIcon");

            if (passwordInput.type === "password") {
                passwordInput.type = "text";
                passwordToggleIcon.classList.remove('bx-hide');
                passwordToggleIcon.classList.add('bx-show');
            } else {
                passwordInput.type = "password";
                passwordToggleIcon.classList.remove('bx-show');
                passwordToggleIcon.classList.add('bx-hide');
            }
        }

        // Prevent navigating back to cached page after logout
        history.pushState(null, null, document.URL);
        window.addEventListener('popstate', function () {
            history.pushState(null, null, document.URL);
        });
    </script>
</body>
</html>
