package org.coHost.co_Host.repository;

import org.coHost.co_Host.model.LogisticsClaim;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LogisticsClaimRepository extends JpaRepository<LogisticsClaim, Long> {
    // Add custom query methods if needed
}
