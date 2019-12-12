package com.goodfellows.morssenger;

import java.util.Calendar;
import java.util.Date;

public class    Message {
    private String text;
    private String textSender;
    private String textTime;

    public Message(){}

    public Message(String text, String textSender, String textTime){
        this.text = text;
        this.textSender = textSender;
        this.textTime = textTime;
    }

    public String getText() {return text;}

    public String getTextSender() {return textSender;}

    public String getTextTime() {return textTime;}

    public void setText(String text) {this.text = text;}

    public void setTextSender(String textSender) {this.textSender = textSender;}

    public String setTextTime(String textTime) {return textTime;}
}

