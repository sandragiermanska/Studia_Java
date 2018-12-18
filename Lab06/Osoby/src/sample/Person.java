package sample;

public class Person {

    private String fullName;
    private String phone;
    private String pesel;

    public Person(String fullName, String phone, String pesel) {
        this.fullName = fullName;
        this.phone = phone;
        this.pesel = pesel;
    }

    public String getFullName() {
        return fullName;
    }

    public String getPhone() {
        return phone;
    }

    public String getPesel() {
        return pesel;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setPesel(String pesel) {
        this.pesel = pesel;
    }

}
