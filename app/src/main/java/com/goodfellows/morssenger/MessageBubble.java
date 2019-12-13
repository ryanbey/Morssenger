package com.goodfellows.morssenger;

public class MessageBubble {

    private String content;
    private String email;
    private boolean myMessage;

    public MessageBubble(String content, String email, boolean myMessage) {
        this.content = content;
        this.myMessage = myMessage;
        this.email = email;
    }

    public String getContent() {
        return content;
    }

    public boolean myMessage() {
        return myMessage;
    }

    public String getEmail() {
        return email;
    }
}
