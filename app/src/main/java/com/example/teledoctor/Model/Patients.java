package com.example.teledoctor.Model;

public class Patients {
    private String firstname;
    private String lastname;
    private String address;
    private String age;
    private String phone;
    private String gender;
    private String email;
    private String password;
    private Boolean checked;

    public Patients(String firstname, String lastname, String address, String age, String gender, String email, String password) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.address = address;
        this.age = age;

        this.gender = gender;
        this.email = email;
        this.password = password;

    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

//    public Boolean getCheck() {
//        return checked;
//    }

    public void setCheck(Boolean check) {
        this.checked = check;
    }
}
