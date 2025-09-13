package org.coHost.co_Host.repository;

import org.coHost.co_Host.model.Rsvp;
import org.coHost.co_Host.model.Event;
import org.coHost.co_Host.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RsvpRepository extends JpaRepository<Rsvp, Long> {
    
    // Find RSVP by user and event
    Optional<Rsvp> findByUserAndEvent(User user, Event event);
    
    // Find all RSVPs for a specific event
    List<Rsvp> findByEvent(Event event);
    
    // Find all RSVPs for a specific user
    List<Rsvp> findByUser(User user);
    
    // Count RSVPs by status for an event
    @Query("SELECT COUNT(r) FROM Rsvp r WHERE r.event.id = :eventId AND r.status = :status")
    Long countByEventIdAndStatus(@Param("eventId") Long eventId, @Param("status") String status);
    
    // Check if user already has RSVP for event
    boolean existsByUserAndEvent(User user, Event event);
}
