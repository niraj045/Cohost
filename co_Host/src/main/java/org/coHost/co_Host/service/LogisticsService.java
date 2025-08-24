package org.coHost.co_Host.service;

import org.coHost.co_Host.model.LogisticsItem;
import org.coHost.co_Host.repository.LogisticsItemRepository;
import org.coHost.co_Host.model.LogisticsClaim;
import org.coHost.co_Host.repository.LogisticsClaimRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LogisticsService {
    private final LogisticsItemRepository logisticsItemRepository;
    private final LogisticsClaimRepository logisticsClaimRepository;

    public LogisticsService(LogisticsItemRepository logisticsItemRepository,
                           LogisticsClaimRepository logisticsClaimRepository) {
        this.logisticsItemRepository = logisticsItemRepository;
        this.logisticsClaimRepository = logisticsClaimRepository;
    }

    public List<LogisticsItem> getAllItems() {
        return logisticsItemRepository.findAll();
    }

    public LogisticsItem createItem(LogisticsItem item) {
        return logisticsItemRepository.save(item);
    }

    public List<LogisticsClaim> getAllClaims() {
        return logisticsClaimRepository.findAll();
    }

    public LogisticsClaim createClaim(LogisticsClaim claim) {
        return logisticsClaimRepository.save(claim);
    }
}
