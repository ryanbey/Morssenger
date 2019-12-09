package com.goodfellows.morssenger;

public class User {
    private String firstName;
    private String lastName;
    private String email;
    private String password;

    User(){}

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
