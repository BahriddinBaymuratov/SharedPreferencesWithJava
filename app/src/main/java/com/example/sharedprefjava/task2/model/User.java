package com.example.sharedprefjava.task2.model;

public class User {
    private String fullName;
    private String email;
    private String phoneNum;
    private String userName;
    private String password;

    public User(String fullName, String email, String phoneNum, String userName, String password) {
        this.fullName = fullName;
        this.email = email;
        this.phoneNum = phoneNum;
        this.userName = userName;
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
