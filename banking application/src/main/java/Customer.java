public class Customer {
	private int id;
    private String fullName;
    private String address;
    private String mobileNo;
    private String email;
    private String accountType;
    private double initialBalance;
    private String dob;
    private String idProofType;
    private String idProofNumber;
    private String accountStatus;
    private String accountNo;
    

    public Customer() {
    	
    }
    public Customer(int id,String fullName, String address, String mobileNo, String email, String accountType, double initialBalance, String dob, String idProofType, String idProofNumber,String accountStatus,String accountNo) {
        this.fullName = fullName;
        this.id=id;
        this.address = address;
        this.mobileNo = mobileNo;
        this.email = email;
        this.accountType = accountType;
        this.initialBalance = initialBalance;
        this.dob = dob;
        this.idProofType = idProofType;
        this.idProofNumber = idProofNumber;
        this.accountStatus=accountStatus;
        this.accountNo=accountNo;
    }

    // Getters and setters
    // ...
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public double getInitialBalance() {
        return initialBalance;
    }

    public void setInitialBalance(double initialBalance) {
        this.initialBalance = initialBalance;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }
    public String getAccountNO() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

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


    public String getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(String accountStatus) {
        this.accountStatus = accountStatus;
    }
}
