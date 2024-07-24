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
    private String idProofNumber;
    private String idProofType;
    private String gender;
    public Customer(String fullName, String address, String mobileNo, String email, String accountType,
                    double initialBalance, LocalDate dob, String gender,String idProofType,String idProofNumber) {
        this.fullName = fullName;
        this.address = address;
        this.mobileNo = mobileNo;
        this.email = email;
        this.accountType = accountType;
        this.initialBalance = initialBalance;
        this.dob = dob;
        this.gender=gender;
        this.idProofType = idProofType;
        this.idProofNumber = idProofNumber;
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
    
    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }

    public String getIdProofType() {
        return idProofType;
    }

    public void setIdProofType(String idProofType) {
        this.idProofType = idProofType;
    }

    public String getIdProofNumber() {
        return idProofNumber;
    }

    public void setIdProofNumber(String idProofNumber) {
        this.idProofNumber = idProofNumber;
    }

}
