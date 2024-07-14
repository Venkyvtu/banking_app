package model;

import java.math.BigDecimal;

public class UserLoginResult {
    private String role;
    private boolean firstLogin;
    private String fullName;
    private String accountNumber;
    private BigDecimal balance;

    public UserLoginResult(String role, boolean firstLogin, String fullName, String accountNumber, BigDecimal balance) {
        this.role = role;
        this.firstLogin = firstLogin;
        this.fullName = fullName;
        this.accountNumber = accountNumber;
        this.balance = balance;
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
}
