package org.coHost.co_Host.repository;

import org.coHost.co_Host.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
    // Add custom query methods if needed
}
