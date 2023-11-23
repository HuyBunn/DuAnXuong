package com.example.duanxuong.Model;

public class User {
    private String userName;
    private String pass;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public User(String userName, String pass, String fullName) {
        this.userName = userName;
        this.pass = pass;
        this.fullName = fullName;
    }

    public User() {
    }

    private String fullName;

}
