package org.coHost.co_Host.dto;

public class ChatMessageDto {
    private Long id;
    private Long eventId;
    private Long userId;
    private String message;
    private String sender;

    public ChatMessageDto() {}
    public ChatMessageDto(Long id, Long eventId, Long userId, String message, String sender) {
        this.id = id;
        this.eventId = eventId;
        this.userId = userId;
        this.message = message;
        this.sender = sender;
    }
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getEventId() { return eventId; }
    public void setEventId(Long eventId) { this.eventId = eventId; }
    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }
    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
    public String getSender() { return sender; }
    public void setSender(String sender) { this.sender = sender; }
}
