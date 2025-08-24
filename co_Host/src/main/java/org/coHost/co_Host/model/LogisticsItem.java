package org.coHost.co_Host.model;

import jakarta.persistence.*;

@Entity
@Table(name = "logistics_items")
public class LogisticsItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "event_id", nullable = false)
    private Event event;

    @Column(nullable = false, length = 150)
    private String itemName;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column
    private Integer quantity = 1;

    public LogisticsItem() {}

    public LogisticsItem(Long id, Event event, String itemName, String description, Integer quantity) {
        this.id = id;
        this.event = event;
        this.itemName = itemName;
        this.description = description;
        this.quantity = quantity;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Event getEvent() { return event; }
    public void setEvent(Event event) { this.event = event; }
    public String getItemName() { return itemName; }
    public void setItemName(String itemName) { this.itemName = itemName; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public Integer getQuantity() { return quantity; }
    public void setQuantity(Integer quantity) { this.quantity = quantity; }
}
