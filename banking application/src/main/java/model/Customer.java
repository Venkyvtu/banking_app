package model;

import java.time.LocalDate;

public class Customer {
    private String fullName;
    private String address;
    private String mobileNo;
    private String email;
    private String accountType;
    private double initialBalance;
    private LocalDate dob;
    private String idProof;
    public Customer(String fullName, String address, String mobileNo, String email, String accountType,
                    double initialBalance, LocalDate dob, String idProof) {
        this.fullName = fullName;
        this.address = address;
        this.mobileNo = mobileNo;
        this.email = email;
        this.accountType = accountType;
        this.initialBalance = initialBalance;
        this.dob = dob;
        this.idProof = idProof;
    }
    

    // Getters and Setters
    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public String getMobileNo() { return mobileNo; }
    public void setMobileNo(String mobileNo) { this.mobileNo = mobileNo; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getAccountType() { return accountType; }
    public void setAccountType(String accountType) { this.accountType = accountType; }

    public double getInitialBalance() { return initialBalance; }
    public void setInitialBalance(double initialBalance) { this.initialBalance = initialBalance; }

    public LocalDate getDob() { return dob; }
    public void setDob(LocalDate dob) { this.dob = dob; }

    public String getIdProof() { return idProof; }
    public void setIdProof(String idProof) { this.idProof = idProof; }

}
