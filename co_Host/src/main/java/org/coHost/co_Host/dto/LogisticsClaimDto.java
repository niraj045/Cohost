package org.coHost.co_Host.dto;

public class LogisticsClaimDto {
    private Long id;
    private Long logisticsItemId;
    private Long userId;

    public LogisticsClaimDto() {}
    public LogisticsClaimDto(Long id, Long logisticsItemId, Long userId) {
        this.id = id;
        this.logisticsItemId = logisticsItemId;
        this.userId = userId;
    }
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getLogisticsItemId() { return logisticsItemId; }
    public void setLogisticsItemId(Long logisticsItemId) { this.logisticsItemId = logisticsItemId; }
    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }
}
