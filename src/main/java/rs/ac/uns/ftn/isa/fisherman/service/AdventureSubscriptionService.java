package rs.ac.uns.ftn.isa.fisherman.service;

import rs.ac.uns.ftn.isa.fisherman.model.AdventureSubscription;

import java.util.Set;

public interface AdventureSubscriptionService {
    void addSubscription(String clientUsername, Long adventureId);
    void removeSubscription(String username, Long adventureId);
    boolean checkIfUserIsSubscribed(String username, Long adventureId);
    Set<AdventureSubscription> findSubscriptionsByClientUsername(String username);
}
