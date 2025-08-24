package org.coHost.co_Host.dto;

import org.coHost.co_Host.model.Event;

public class EventMapper {
    public static EventDto toDto(Event event) {
        if (event == null) return null;

        Long communityId = null;
        CommunityDto communityDto = null;
        if (event.getCommunity() != null) {
            communityId = event.getCommunity().getId();
            communityDto = new CommunityDto(
                event.getCommunity().getId(),
                event.getCommunity().getName(),
                event.getCommunity().getDescription()
            );
        }

        EventDto eventDto = new EventDto(
            event.getId(),
            event.getTitle(),
            event.getDescription(),
            event.getEventDate(),
            event.getLocation(),
            communityId
        );

        eventDto.setCommunity(communityDto);

        return eventDto;
    }
}
