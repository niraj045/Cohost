package org.coHost.co_Host.controller;

import org.coHost.co_Host.dto.EventDto;
import org.coHost.co_Host.service.EventService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/events")
public class EventController {
    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping
    public ResponseEntity<List<EventDto>> getAllEvents() {
        List<EventDto> eventDtos = eventService.getAllEventsDto();
        return ResponseEntity.ok(eventDtos);
    }

    // UPDATE THIS METHOD
    @PostMapping
    public ResponseEntity<EventDto> createEvent(@RequestBody EventDto eventDto, Principal principal) {
        // 'principal.getName()' provides the username (email) of the currently authenticated user
        EventDto createdEvent = eventService.createEventFromDto(eventDto, principal.getName());
        return ResponseEntity.ok(createdEvent);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EventDto> getEvent(@PathVariable Long id) {
        EventDto eventDto = eventService.getEventDtoById(id);
        return ResponseEntity.ok(eventDto);
    }
}