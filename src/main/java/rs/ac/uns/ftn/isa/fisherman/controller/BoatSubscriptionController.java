package rs.ac.uns.ftn.isa.fisherman.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rs.ac.uns.ftn.isa.fisherman.dto.AdventureSubscriptionDto;
import rs.ac.uns.ftn.isa.fisherman.dto.BoatSubscriptionDto;
import rs.ac.uns.ftn.isa.fisherman.service.BoatSubscriptionService;

@RestController
@RequestMapping(value = "/boatSubscription", produces = MediaType.APPLICATION_JSON_VALUE)
public class BoatSubscriptionController {

    @Autowired
    BoatSubscriptionService boatSubscriptionService;

    @PostMapping("/addSubscription")
    @PreAuthorize("hasRole('CLIENT')")
    public ResponseEntity<String> addSubscription (@RequestBody BoatSubscriptionDto boatSubscriptionDto) {
        boatSubscriptionService.addSubscription(boatSubscriptionDto.getClientUsername(), boatSubscriptionDto.getBoatDto().getId());
        return new ResponseEntity<>("Successful subscription!", HttpStatus.OK);
    }

    @PostMapping("/removeSubscription")
    @PreAuthorize("hasRole('CLIENT')")
    public ResponseEntity<String> removeSubscription (@RequestBody BoatSubscriptionDto boatSubscriptionDto) {
        boatSubscriptionService.removeSubscription(boatSubscriptionDto.getClientUsername(), boatSubscriptionDto.getBoatDto().getId());
        return new ResponseEntity<>("Subscription successfully removed!", HttpStatus.OK);
    }

}
