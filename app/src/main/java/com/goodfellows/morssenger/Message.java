package com.goodfellows.morssenger;

import java.util.Date;

public class Message {
    private String text;
    private String textSender;
    private long textTime;
    private boolean belongsToCurrentUser;

    public Message(){}

    public Message(String text, String textSender, boolean belongsToCurrentUser){
        this.text = text;
        this.textSender = textSender;
        this.textTime = new Date().getTime();
    }

    public String getText(){return text;}

    public String getTextSender(){return textSender;}

    public long getTextTime(){return textTime;}

    public void setText(String text){this.text = text;}

    public void setTextSender(String textSender){this.text = textSender;}

    public void setTextTime(long textTime){this.textTime = textTime;}
}

