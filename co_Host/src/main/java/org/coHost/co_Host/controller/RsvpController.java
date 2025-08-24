package org.coHost.co_Host.controller;

import org.coHost.co_Host.model.Rsvp;
import org.coHost.co_Host.service.RsvpService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rsvps")
public class RsvpController {
    private final RsvpService rsvpService;

    public RsvpController(RsvpService rsvpService) {
        this.rsvpService = rsvpService;
    }

    @GetMapping
    public List<Rsvp> getAllRsvps() {
        return rsvpService.getAllRsvps();
    }

    @PostMapping
    public ResponseEntity<Rsvp> createRsvp(@RequestBody Rsvp rsvp) {
        return ResponseEntity.ok(rsvpService.createRsvp(rsvp));
    }
}
