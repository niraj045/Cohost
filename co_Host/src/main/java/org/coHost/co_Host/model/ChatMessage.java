package org.coHost.co_Host.model;

import jakarta.persistence.*;

@Entity
@Table(name = "chat_messages")
public class ChatMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "event_id", nullable = false)
    private Event event;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String message;

    @Transient  // transient because sender is usually a derived or client-side field, not stored in DB
    private String sender;

    public ChatMessage() {}

    // Other getters and setters...

    // getter for sender
    public String getSender() {
        return sender;
    }

    // setter for sender
    public void setSender(String sender) {
        this.sender = sender;
    }
}
