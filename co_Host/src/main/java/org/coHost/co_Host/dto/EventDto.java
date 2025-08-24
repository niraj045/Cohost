package org.coHost.co_Host.dto;

import java.time.LocalDateTime;

public class EventDto {
    private Long id;
    private String title;
    private String description;
    private LocalDateTime eventDate;
    private String location;

    private Long communityId;
    private CommunityDto community;  // nested DTO

    public EventDto() {}

    public EventDto(Long id, String title, String description, LocalDateTime eventDate, String location, Long communityId) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.eventDate = eventDate;
        this.location = location;
        this.communityId = communityId;
    }



    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public LocalDateTime getEventDate() { return eventDate; }
    public void setEventDate(LocalDateTime eventDate) { this.eventDate = eventDate; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public Long getCommunityId() { return communityId; }
    public void setCommunityId(Long communityId) { this.communityId = communityId; }

    public CommunityDto getCommunity() { return community; }
    public void setCommunity(CommunityDto community) { this.community = community; }
}
