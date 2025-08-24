package org.coHost.co_Host.repository;

import org.coHost.co_Host.model.LogisticsItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LogisticsItemRepository extends JpaRepository<LogisticsItem, Long> {
    // Add custom query methods if needed
}
