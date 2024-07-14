<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Logout</title>
    <style>
        body { font-family: Arial, sans-serif; background-color: #f9f9f9; margin: 40px; text-align: center; }
        .logout-container { max-width: 300px; margin: auto; padding: 20px; background-color: #fff; border-radius: 8px; box-shadow: 0 0 10px rgba(0,0,0,0.1); }
        button { padding: 10px 20px; background-color: #007bff; color: white; border: none; border-radius: 4px; font-size: 16px; cursor: pointer; transition: background-color 0.3s ease; }
        button:hover { background-color: #0056b3; }
        .message { margin-top: 20px; color: #ff0000; font-size: 16px; }
    </style>
    <script>
        function confirmLogout() {
            if (confirm("Are you sure you want to log out?")) {
                var xhr = new XMLHttpRequest();
                xhr.open("GET", "Logoutservlet", true);
                xhr.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
                xhr.onreadystatechange = function () {
                    if (xhr.readyState === 4 && xhr.status === 200) {
                        var jsonResponse = JSON.parse(xhr.responseText);
                        if (jsonResponse.status === "success") {
                            document.getElementById("message").innerText = "You have successfully logged out.";
                        }
                    }
                };
                xhr.send();
            }
        }
    </script>
</head>
<body>
    <div class="logout-container">
        <h2>Logout</h2>
        <button onclick="confirmLogout()">Logout</button>
        <div id="message" class="message"></div>
    </div>
</body>
</html>
