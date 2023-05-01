package com.example.studentverification;

public class Users {

    String name2, regNumber2, email2, phone2;

    public Users() {
    }

    public Users(String name, String regNumber, String email, String phone) {
        this.name2 = name;
        this.regNumber2 = regNumber;
        this.email2 = email;
        this.phone2 = phone;
    }

    public String getName() {
        return name2;
    }

    public void setName(String name) {
        this.name2 = name;
    }

    public String getRegNumber() {
        return regNumber2;
    }

    public void setRegNumber(String regNumber) {
        this.regNumber2 = regNumber;
    }

    public String getEmail() {
        return email2;
    }

    public void setEmail(String email) {
        this.email2 = email;
    }

    public String getPhone() {
        return phone2;
    }

    public void setPhone(String phone) {
        this.phone2 = phone;
    }
}
