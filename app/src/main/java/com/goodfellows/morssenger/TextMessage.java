package com.goodfellows.morssenger;

import java.util.Date;

public class TextMessage {
    private String text;
    private String textSender;
    private long textTime;

    public TextMessage(){}

    public TextMessage(String text, String textSender){
        this.text = text;
        this.textSender = textSender;
        this.textTime = new Date().getTime();
    }

    public String getText(){
        return text;
    }

    public String getTextSender(){
        return textSender;
    }

    public long getTextTime(){
        return textTime;
    }

    public void setText(String text){
        this.text = text;
    }

    public void setTextSender(String textSender){
        this.text = textSender;
    }

    public void setTextTime(long textTime){
        this.textTime = textTime;
    }
}

