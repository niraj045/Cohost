package org.coHost.co_Host.service;

import org.coHost.co_Host.model.Rsvp;
import org.coHost.co_Host.repository.RsvpRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RsvpService {
    private final RsvpRepository rsvpRepository;

    public RsvpService(RsvpRepository rsvpRepository) {
        this.rsvpRepository = rsvpRepository;
    }

    public List<Rsvp> getAllRsvps() {
        return rsvpRepository.findAll();
    }

    public Rsvp createRsvp(Rsvp rsvp) {
        return rsvpRepository.save(rsvp);
    }
}
