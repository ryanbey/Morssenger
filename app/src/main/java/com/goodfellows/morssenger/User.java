package com.goodfellows.morssenger;

public class User {
    //Private member variables
    private String firstName;
    private String lastName;
    private String email;
    private String password;


    //Default Constructor
    User(){}


    //Getters
    public String getPassword()
    {
        return password;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public String getEmail()
    {
        return email;
    }


    //setters
    public void setPassword(String password) { this.password = password; }

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }
}
