<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Customer Registration</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/flatpickr/dist/flatpickr.min.css">
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 20px;
        }
        h2 {
            text-align: center;
            color: #007bff;
        }
        form {
            max-width: 800px;
            margin: 0 auto;
            background-color: #fff;
            padding: 30px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        .form-group {
            display: flex;
            flex-wrap: wrap;
            justify-content: space-between;
        }
        .form-group > div {
            flex: 1 1 calc(33% - 10px);
            margin-bottom: 20px;
        }
        label {
            display: block;
            color: #333;
        }
        input, select {
            width: calc(100% - 10px);
            padding: 10px;
            margin-top: 5px;
            border: 1px solid #ccc;
            border-radius: 4px;
            font-size: 16px;
            box-sizing: border-box;
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
        .note {
            font-size: 14px;
            color: #777;
            margin-top: 5px;
        }
        .hidden {
            display: none;
        }

        /* Responsive Styles */
        @media (max-width: 768px) {
            .form-group > div {
                flex: 1 1 calc(50% - 10px);
            }
        }
        @media (max-width: 480px) {
            body {
                padding: 10px;
            }
            form {
                padding: 15px;
            }
            .form-group > div {
                flex: 1 1 100%;
            }
            input, select {
                width: calc(100% - 10px);
                font-size: 14px;
            }
            input[type="submit"] {
                font-size: 14px;
                padding: 10px 15px;
            }
            h2 {
                font-size: 24px;
            }
        }
    </style>
    <script src="https://cdn.jsdelivr.net/npm/flatpickr"></script>
    <script>
        function toggleIdInput() {
            var panInput = document.getElementById("panInput");
            var aadharInput = document.getElementById("aadharInput");
            var idProofType = document.querySelector('input[name="idProofType"]:checked').value;
            
            if (idProofType === "PAN") {
                panInput.classList.remove("hidden");
                aadharInput.classList.add("hidden");
            } else if (idProofType === "Aadhar") {
                panInput.classList.add("hidden");
                aadharInput.classList.remove("hidden");
            }
        }

        document.addEventListener("DOMContentLoaded", function() {
            flatpickr("#dob", {
                altInput: true,
                altFormat: "F j, Y",
                dateFormat: "Y-m-d",
                maxDate: new Date(),
                yearRange: [1900, new Date().getFullYear()],
                onReady: function(selectedDates, dateStr, instance) {
                    const yearSelect = instance.yearElement;
                    const currentYear = new Date().getFullYear();
                    for (let year = 1900; year <= currentYear; year++) {
                        const option = document.createElement("option");
                        option.value = year;
                        option.textContent = year;
                        yearSelect.appendChild(option);
                    }
                    instance.currentYearElement = yearSelect;
                }
            });
        });
    </script>
</head>
<body>
<% response.setHeader("Cache-Control", "no-cache , no-store, must-revalidate"); 
    response.setHeader("Expires","0"); %>
    
    <form action="registerCustomerServlet" method="post" enctype="multipart/form-data">
        <h2>Customer Registration</h2>
        <div class="form-group">
            <div>
                <label for="fullName">Full Name:</label>
                <input type="text" id="fullName" name="fullName" required>
            </div>
            <div>
                <label for="address">Address:</label>
                <input type="text" id="address" name="address" required>
            </div>
            <div>
                <label for="mobileNo">Mobile No:</label>
                <input type="text" id="mobileNo" name="mobileNo" pattern="[0-9]{10}" required>
                <span class="note">Please enter a 10-digit mobile number.</span>
            </div>
        </div>

        <div class="form-group">
            <div>
                <label for="email">Email ID:</label>
                <input type="email" id="email" name="email" required>
                <span class="note">We'll send your account details to this email.</span>
            </div>
            <div>
                <label for="accountType">Type of Account:</label>
                <select id="accountType" name="accountType" required>
                    <option value="">Select Account Type</option>
                    <option value="Savings">Savings Account</option>
                    <option value="Current">Current Account</option>
                </select>
            </div>
            <div>
                <label for="initialBalance">Initial Balance (min 1000):</label>
                <input type="number" id="initialBalance" name="initialBalance" min="1000" required>
                <span class="note">Minimum initial deposit is ₹1000.</span>
            </div>
        </div>

        <div class="form-group">
            <div>
                <label for="dob">Date of Birth:</label>
                <input type="text" id="dob" name="dob" required>
            </div>
            <div>
                <label for="gender">Gender:</label>
                <select id="gender" name="gender" required>
                    <option value="">Select Gender</option>
                    <option value="Male">Male</option>
                    <option value="Female">Female</option>
                    <option value="Other">Other</option>
                </select>
            </div>
            <div>
                <label for="idProofType">ID Proof:</label>
                <input type="radio" id="idProofPAN" name="idProofType" value="PAN" onclick="toggleIdInput()" required>
                <label for="idProofPAN">PAN</label>
                <input type="radio" id="idProofAadhar" name="idProofType" value="Aadhar" onclick="toggleIdInput()" required>
                <label for="idProofAadhar">Aadhar</label>
            </div>
        </div>

        <div class="form-group">
            <div id="panInput" class="hidden">
                <label for="panNumber">PAN Number:</label>
                <input type="text" id="panNumber" name="panNumber" pattern="[A-Z]{5}[0-9]{4}[A-Z]{1}">
                <span class="note">Please enter a valid PAN number (e.g., ABCDE1234F).</span>
            </div>
            <div id="aadharInput" class="hidden">
                <label for="aadharNumber">Aadhar Number:</label>
                <input type="text" id="aadharNumber" name="aadharNumber" pattern="[0-9]{12}">
                <span class="note">Please enter a 12-digit Aadhar number.</span>
            </div>
        </div>

        <input type="submit" value="Register">
    </form>
</body>
</html>
