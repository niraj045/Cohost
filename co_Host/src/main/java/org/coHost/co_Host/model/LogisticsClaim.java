package org.coHost.co_Host.model;

import jakarta.persistence.*;

@Entity
@Table(name = "logistics_claims")
public class LogisticsClaim {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "logistics_item_id", nullable = false)
    private LogisticsItem logisticsItem;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public LogisticsClaim() {}

    public LogisticsClaim(Long id, LogisticsItem logisticsItem, User user) {
        this.id = id;
        this.logisticsItem = logisticsItem;
        this.user = user;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public LogisticsItem getLogisticsItem() { return logisticsItem; }
    public void setLogisticsItem(LogisticsItem logisticsItem) { this.logisticsItem = logisticsItem; }
    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
}
