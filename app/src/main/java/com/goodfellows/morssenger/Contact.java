package com.goodfellows.morssenger;

import java.util.List;

public class Contact {
    private String name;
    private List<String> messages;

    public String getName() { return name; }
    public List<String> getMessages() { return messages; }
    public void setName(String name) { this.name = name; }
    public void setList(List<String> messages) { this.messages = messages; }

    // adds a message to the list
    public void addMessage(String message){
        messages.add(message);
    }
}

