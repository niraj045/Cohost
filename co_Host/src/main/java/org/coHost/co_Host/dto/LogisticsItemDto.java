package org.coHost.co_Host.dto;

public class LogisticsItemDto {
    private Long id;
    private Long eventId;
    private String itemName;
    private String description;
    private Integer quantity;

    public LogisticsItemDto() {}
    public LogisticsItemDto(Long id, Long eventId, String itemName, String description, Integer quantity) {
        this.id = id;
        this.eventId = eventId;
        this.itemName = itemName;
        this.description = description;
        this.quantity = quantity;
    }
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getEventId() { return eventId; }
    public void setEventId(Long eventId) { this.eventId = eventId; }
    public String getItemName() { return itemName; }
    public void setItemName(String itemName) { this.itemName = itemName; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public Integer getQuantity() { return quantity; }
    public void setQuantity(Integer quantity) { this.quantity = quantity; }
}
