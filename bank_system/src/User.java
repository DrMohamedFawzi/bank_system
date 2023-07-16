class User {
    private String name;
    private String id;
    private String address;
    private double money;
    private int accountNumber;

    // Constructor to initialize the fields
    public User(String name, String id, String address, double money, int accountNumber) {
        this.name = name;
        this.id = id;
        this.address = address;
        this.money = money;
        this.accountNumber = accountNumber;
    }

    // Getters and setters for the fields
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    @Override
    public String toString() {
        return "مستخدم{" +
                "الإسم='" + name + '\'' +
                ", الهوية='" + id + '\'' +
                ", العنوان='" + address + '\'' +
                ", الرصيد المالي=" + money +
                ", رقم الحساب=" + accountNumber +
                '}';
    }
}
