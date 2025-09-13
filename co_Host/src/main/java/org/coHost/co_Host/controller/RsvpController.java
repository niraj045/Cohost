package org.coHost.co_Host.controller;

import org.coHost.co_Host.model.Rsvp;
import org.coHost.co_Host.service.RsvpService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class RsvpController {
    private final RsvpService rsvpService;

    public RsvpController(RsvpService rsvpService) {
        this.rsvpService = rsvpService;
    }

    @GetMapping("/rsvps")
    public List<Rsvp> getAllRsvps() {
        return rsvpService.getAllRsvps();
    }

    @PostMapping("/events/{eventId}/rsvp")
    public ResponseEntity<Rsvp> createRsvp(@PathVariable Long eventId, @RequestBody Map<String, String> request, Principal principal) {
        try {
            String status = request.get("status");
            if (status == null || status.trim().isEmpty()) {
                status = "ATTENDING";
            }
            Rsvp rsvp = rsvpService.createRsvp(eventId, principal.getName(), status);
            return ResponseEntity.ok(rsvp);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    @PutMapping("/rsvp/{rsvpId}")
    public ResponseEntity<Rsvp> updateRsvp(@PathVariable Long rsvpId, @RequestBody Map<String, String> request) {
        String status = request.get("status");
        Optional<Rsvp> updatedRsvp = rsvpService.updateRsvp(rsvpId, status);
        return updatedRsvp.map(ResponseEntity::ok)
                          .orElse(ResponseEntity.notFound().build());
    }
    
    @DeleteMapping("/rsvp/{rsvpId}")
    public ResponseEntity<Void> deleteRsvp(@PathVariable Long rsvpId) {
        try {
            rsvpService.deleteRsvp(rsvpId);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @GetMapping("/events/{eventId}/rsvp")
    public ResponseEntity<List<Rsvp>> getEventRsvps(@PathVariable Long eventId) {
        try {
            List<Rsvp> rsvps = rsvpService.getEventRsvps(eventId);
            return ResponseEntity.ok(rsvps);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @GetMapping("/users/{userId}/rsvp")
    public ResponseEntity<List<Rsvp>> getUserRsvps(@PathVariable String userId, Principal principal) {
        try {
            // For security, users can only view their own RSVPs unless admin
            List<Rsvp> rsvps = rsvpService.getUserRsvps(principal.getName());
            return ResponseEntity.ok(rsvps);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @GetMapping("/events/{eventId}/rsvp/my")
    public ResponseEntity<Rsvp> getMyEventRsvp(@PathVariable Long eventId, Principal principal) {
        try {
            Optional<Rsvp> rsvp = rsvpService.getUserEventRsvp(eventId, principal.getName());
            return rsvp.map(ResponseEntity::ok)
                      .orElse(ResponseEntity.notFound().build());
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
