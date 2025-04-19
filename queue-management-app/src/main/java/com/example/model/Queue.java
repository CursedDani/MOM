package com.example.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Queue {

    @Id
    private String name; // Use name as the primarys key

    @OneToMany(mappedBy = "queueName", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Message> messages = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private User owner;

    public Queue() {
    }

    public Queue(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void addMessage(Message message) {
        this.messages.add(message);
    }

    public Message pullMessage() {
        if (!messages.isEmpty()) {
            return messages.remove(0);
        }
        return null;
    }

    // Getter and Setter for owner
    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }
}