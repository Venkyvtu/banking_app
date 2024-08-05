<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Registration Successful</title>
</head>
<body>
<% response.setHeader("Cache-Control", "no-cache , no-store, must-revalidate"); 
	response.setHeader("Expires","0"); %>
    <h2>Registration Successful!</h2>
    <p>Your account has been successfully created.</p>
    <%
        String accountNumber = (String) request.getAttribute("accountNumber");
    %>
    <p>Your Account Number: <%= accountNumber %></p>
    <a href="customer_details.jsp">View Your Details</a>
</body>
</html>
