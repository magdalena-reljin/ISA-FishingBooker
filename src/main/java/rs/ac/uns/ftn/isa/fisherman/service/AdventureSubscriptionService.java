package rs.ac.uns.ftn.isa.fisherman.service;

public interface AdventureSubscriptionService {
    void addSubscription(String clientUsername, Long adventureId);
    void removeSubscription(String username, Long adventureId);
    boolean checkIfUserIsSubscribed(String username, Long adventureId);
}
