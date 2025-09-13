package org.coHost.co_Host.service;

import org.coHost.co_Host.model.Rsvp;
import org.coHost.co_Host.model.Event;
import org.coHost.co_Host.model.User;
import org.coHost.co_Host.repository.RsvpRepository;
import org.coHost.co_Host.repository.EventRepository;
import org.coHost.co_Host.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RsvpService {
    private final RsvpRepository rsvpRepository;
    private final EventRepository eventRepository;
    private final UserRepository userRepository;

    public RsvpService(RsvpRepository rsvpRepository, EventRepository eventRepository, UserRepository userRepository) {
        this.rsvpRepository = rsvpRepository;
        this.eventRepository = eventRepository;
        this.userRepository = userRepository;
    }

    public List<Rsvp> getAllRsvps() {
        return rsvpRepository.findAll();
    }

    public Rsvp createRsvp(Long eventId, String userEmail, String status) {
        Event event = eventRepository.findById(eventId)
            .orElseThrow(() -> new RuntimeException("Event not found"));
        User user = userRepository.findByUsername(userEmail);
        if (user == null) {
            throw new RuntimeException("User not found");
        }
        
        // Check if RSVP already exists, if so update it
        Optional<Rsvp> existingRsvp = rsvpRepository.findByUserAndEvent(user, event);
        if (existingRsvp.isPresent()) {
            Rsvp rsvp = existingRsvp.get();
            rsvp.setStatus(status);
            return rsvpRepository.save(rsvp);
        } else {
            Rsvp rsvp = new Rsvp();
            rsvp.setUser(user);
            rsvp.setEvent(event);
            rsvp.setStatus(status);
            return rsvpRepository.save(rsvp);
        }
    }
    
    public Optional<Rsvp> updateRsvp(Long rsvpId, String status) {
        Optional<Rsvp> rsvpOpt = rsvpRepository.findById(rsvpId);
        if (rsvpOpt.isPresent()) {
            Rsvp rsvp = rsvpOpt.get();
            rsvp.setStatus(status);
            return Optional.of(rsvpRepository.save(rsvp));
        }
        return Optional.empty();
    }
    
    public void deleteRsvp(Long rsvpId) {
        rsvpRepository.deleteById(rsvpId);
    }
    
    public List<Rsvp> getEventRsvps(Long eventId) {
        Event event = eventRepository.findById(eventId)
            .orElseThrow(() -> new RuntimeException("Event not found"));
        return rsvpRepository.findByEvent(event);
    }
    
    public List<Rsvp> getUserRsvps(String userEmail) {
        User user = userRepository.findByUsername(userEmail);
        if (user == null) {
            throw new RuntimeException("User not found");
        }
        return rsvpRepository.findByUser(user);
    }
    
    public Optional<Rsvp> getUserEventRsvp(Long eventId, String userEmail) {
        Event event = eventRepository.findById(eventId)
            .orElseThrow(() -> new RuntimeException("Event not found"));
        User user = userRepository.findByUsername(userEmail);
        if (user == null) {
            throw new RuntimeException("User not found");
        }
        return rsvpRepository.findByUserAndEvent(user, event);
    }
}
