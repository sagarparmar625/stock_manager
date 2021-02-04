package com.example.stockmanagementsystem;

public class UserHelperClass {

    String compyname,username,phoneno,password;

    public UserHelperClass() {

    }

    public UserHelperClass(String name, String email, String phoneno, String password) {
        this.compyname = compyname;
        this.username = username;
        this.phoneno = phoneno;
        this.password = password;
    }

    public String getName() {
        return compyname;
    }

    public void setName(String name) {
        this.compyname = compyname;
    }

    public String getEmail() {
        return username;
    }

    public void setEmail(String email) {
        this.username = username;
    }

    public String getPhoneno() {
        return phoneno;
    }

    public void setPhoneno(String phoneno) {
        this.phoneno = phoneno;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
