package org.coHost.co_Host.service;

import org.coHost.co_Host.dto.EventDto;
import org.coHost.co_Host.dto.EventMapper;
import org.coHost.co_Host.model.Community;
import org.coHost.co_Host.model.Event;
import org.coHost.co_Host.model.User;
import org.coHost.co_Host.repository.CommunityRepository;
import org.coHost.co_Host.repository.EventRepository;
import org.coHost.co_Host.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EventService {
    private final EventRepository eventRepository;
    private final CommunityRepository communityRepository;
    private final UserRepository userRepository; // 1. Inject UserRepository

    // 2. Update the constructor
    public EventService(EventRepository eventRepository, CommunityRepository communityRepository, UserRepository userRepository) {
        this.eventRepository = eventRepository;
        this.communityRepository = communityRepository;
        this.userRepository = userRepository;
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

    // 3. Update the create method to associate the event with an organizer
    public EventDto createEventFromDto(EventDto eventDto, String organizerUsername) {
        // Find the user who is creating the event
        User organizer = userRepository.findByUsername(organizerUsername);
        if (organizer == null) {
            throw new RuntimeException("User not found with username: " + organizerUsername);
        }

        // Find the community for the event
        Community community = communityRepository.findById(eventDto.getCommunityId())
                .orElseThrow(() -> new RuntimeException("Community not found with id " + eventDto.getCommunityId()));

        // Create and configure the new Event entity
        Event event = new Event();
        event.setTitle(eventDto.getTitle());
        event.setDescription(eventDto.getDescription());
        event.setEventDate(eventDto.getEventDate());
        event.setLocation(eventDto.getLocation());
        event.setCommunity(community);
        event.setOrganizer(organizer); // Set the logged-in user as the organizer

        // Save the new event and return its DTO
        Event savedEvent = eventRepository.save(event);
        return EventMapper.toDto(savedEvent);
    }

    // These methods can remain if you need direct entity access elsewhere
    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    public Event getEventById(Long id) {
        return eventRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Event not found with id " + id));
    }
}