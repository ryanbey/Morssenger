package com.goodfellows.morssenger;

public class MessageBubble {

    private String content;
    private boolean isMyMessage;

    public MessageBubble(String content, boolean myMessage) {
        this.content = content;
        this.isMyMessage = myMessage;
    }

    public String getContent() {
        return content;
    }

    public boolean myMessage() {
        return isMyMessage;
    }
}
