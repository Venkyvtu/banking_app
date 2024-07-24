package model;

import java.math.BigDecimal;
import java.sql.Date; 

public class UserLoginResult {
    private String role;
    private boolean firstLogin;
    private String fullName;
    private String accountNumber;
    private BigDecimal balance;
    private String address;
    private String mobileNo;
    private String email;
    private Date dob; 

    public UserLoginResult(String role, boolean firstLogin, String fullName, String accountNumber, BigDecimal balance,
                           String address, String mobileNo, String email, Date dob) { 
        this.role = role;
        this.firstLogin = firstLogin;
        this.fullName = fullName;
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.address = address;
        this.mobileNo = mobileNo;
        this.email = email;
        this.dob = dob;
    }

    public String getRole() {
        return role;
    }

    public boolean isFirstLogin() {
        return firstLogin;
    }

    public String getFullName() {
        return fullName;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public String getAddress() {
        return address;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public String getEmail() {
        return email;
    }

    public Date getDob() {
        return dob;
    }

    // Setter for dob
    public void setDob(Date dob) {
        this.dob = dob;
    }
}
