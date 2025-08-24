package org.coHost.co_Host.controller;

import java.util.List;

import org.coHost.co_Host.model.LogisticsClaim;
import org.coHost.co_Host.model.LogisticsItem;
import org.coHost.co_Host.service.LogisticsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/logistics")
public class LogisticsController {
    private final LogisticsService logisticsService;

    public LogisticsController(LogisticsService logisticsService) {
        this.logisticsService = logisticsService;
    }

    @GetMapping("/items")
    public List<LogisticsItem> getAllItems() {
        return logisticsService.getAllItems();
    }

    @PostMapping("/items")
    public ResponseEntity<LogisticsItem> createItem(@RequestBody LogisticsItem item) {
        return ResponseEntity.ok(logisticsService.createItem(item));
    }

    @GetMapping("/claims")
    public List<LogisticsClaim> getAllClaims() {
        return logisticsService.getAllClaims();
    }

    @PostMapping("/claims")
    public ResponseEntity<LogisticsClaim> createClaim(@RequestBody LogisticsClaim claim) {
        return ResponseEntity.ok(logisticsService.createClaim(claim));
    }
}
