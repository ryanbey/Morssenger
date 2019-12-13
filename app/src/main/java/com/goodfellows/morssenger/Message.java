package com.goodfellows.morssenger;

import java.util.Calendar;
import java.util.Date;

public class    Message {
    //private member variables
    private String text;
    private String textSender;
    private String textTime;


    //Default Constructor
    public Message(){}


    //Non Default constructor takes in and sets parameter values
    public Message(String text, String textSender, String textTime){
        this.text = text;
        this.textSender = textSender;
        this.textTime = textTime;
    }


    //Getters
    public String getText() {return text;}

    public String getTextSender() {return textSender;}

    public String getTextTime() {return textTime;}


    //Setters
    public void setText(String text) {this.text = text;}

    public void setTextSender(String textSender) {this.textSender = textSender;}

    public String setTextTime(String textTime) {return textTime;}
}

