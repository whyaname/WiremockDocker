package com.anava.spring;

public class User {
    public String name;
    public String dob;
    public String gender;
    public String first_name;
    public String last_name;
    public String picture;

    public User() {
    }

    public User(String name, String dob, String gender, String picture) {
        this.name = name;
        this.dob = dob;
        this.gender = gender;
        this.picture = picture;
    }

}
