package com.example.oopd_project;

class Userdetails {
    String Username,email,address,phoneno;
    //m

    public Userdetails() {
        //to read the values back
    }

    public Userdetails(String username, String email) {
        Username = username;
        this.email = email;
    }

    public Userdetails(String username, String email, String address, String phoneno) {
        Username = username;
        this.email = email;
        this.address = address;
        this.phoneno = phoneno;
    }
}
