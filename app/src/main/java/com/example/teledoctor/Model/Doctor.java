package com.example.teledoctor.Model;

public class Doctor {
private  String firstname;
private String lastname;
    private String address;
    private String phone;
    private String gender;
    private String clinic;
    private String speciality;
    private String experience;
    private String email;
    private String password;

    private String image;

    public Doctor(String image, String firstname, String lastname, String address,  String gender, String clinic, String speciality, String experience, String email, String password) {
        this.image = image;
        this.firstname = firstname;
        this.lastname = lastname;
        this.address = address;

        this.gender = gender;
        this.clinic = clinic;
        this.speciality = speciality;
        this.experience = experience;
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

    public String getClinic() {
        return clinic;
    }

    public void setClinic(String clinic) {
        this.clinic = clinic;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
