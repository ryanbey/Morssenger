package com.goodfellows.morssenger;

public class MessageBubble {

    private String content;
    private String email;
    private boolean isMyMessage;

    public MessageBubble(String content, String email, boolean myMessage) {
        this.content = content;
        this.isMyMessage = myMessage;
        this.email = email;
    }

    public String getContent() {
        return content;
    }

    public boolean myMessage() {
        return isMyMessage;
    }

    public String getEmail() {return email;}
}
