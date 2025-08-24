package org.coHost.co_Host.service;

import java.util.List;
import java.util.stream.Collectors;

import org.coHost.co_Host.dto.EventDto;
import org.coHost.co_Host.dto.EventMapper;
import org.coHost.co_Host.model.Community;
import org.coHost.co_Host.model.Event;
import org.coHost.co_Host.repository.CommunityRepository;
import org.coHost.co_Host.repository.EventRepository;
import org.springframework.stereotype.Service;

@Service
public class EventService {
    private final EventRepository eventRepository;
    private final CommunityRepository communityRepository;

    public EventService(EventRepository eventRepository, CommunityRepository communityRepository) {
        this.eventRepository = eventRepository;
        this.communityRepository = communityRepository;
    }

    public List<EventDto> getAllEventsDto() {
        List<Event> events = eventRepository.findAll();
        return events.stream()
                     .map(EventMapper::toDto)
                     .collect(Collectors.toList());
    }

    public EventDto getEventDtoById(Long id) {
        Event event = eventRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Event not found with id " + id));
        return EventMapper.toDto(event);
    }

    public EventDto createEventFromDto(EventDto eventDto) {
        Event event = new Event();

        event.setTitle(eventDto.getTitle());
        event.setDescription(eventDto.getDescription());
        event.setEventDate(eventDto.getEventDate());
        event.setLocation(eventDto.getLocation());

        if (eventDto.getCommunity() != null && eventDto.getCommunity().getId() != null) {
            Long communityId = eventDto.getCommunity().getId();
            Community community = communityRepository.findById(communityId)
                .orElseThrow(() -> new RuntimeException("Community not found with id " + communityId));
            event.setCommunity(community);
        } else {
            throw new RuntimeException("Community ID is required");
        }

        Event savedEvent = eventRepository.save(event);
        return EventMapper.toDto(savedEvent);
    }


    // If you still want original entity methods

    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    public Event createEvent(Event event) {
        return eventRepository.save(event);
    }

    public Event getEventById(Long id) {
        return eventRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Event not found with id " + id));
    }
}
