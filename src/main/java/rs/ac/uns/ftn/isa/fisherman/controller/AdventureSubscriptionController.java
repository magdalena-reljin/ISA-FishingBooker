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
import rs.ac.uns.ftn.isa.fisherman.service.AdventureSubscriptionService;

@RestController
@RequestMapping(value = "/adventureSubscription", produces = MediaType.APPLICATION_JSON_VALUE)
public class AdventureSubscriptionController {

    @Autowired
    AdventureSubscriptionService adventureSubscriptionService;

    @PostMapping("/addSubscription")
    @PreAuthorize("hasRole('CLIENT')")
    public ResponseEntity<String> addSubscription (@RequestBody AdventureSubscriptionDto adventureSubscriptionDto) {
        adventureSubscriptionService.addSubscription(adventureSubscriptionDto.getClientUsername(), adventureSubscriptionDto.getAdventureDto().getId());
        return new ResponseEntity<>("Successful subscription!", HttpStatus.OK);
    }

    @PostMapping("/removeSubscription")
    @PreAuthorize("hasRole('CLIENT')")
    public ResponseEntity<String> removeSubscription (@RequestBody AdventureSubscriptionDto adventureSubscriptionDto) {
        adventureSubscriptionService.removeSubscription(adventureSubscriptionDto.getClientUsername(), adventureSubscriptionDto.getAdventureDto().getId());
        return new ResponseEntity<>("Subscription successfully removed!", HttpStatus.OK);
    }

}
