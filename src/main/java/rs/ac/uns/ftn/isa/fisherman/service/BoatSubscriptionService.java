package rs.ac.uns.ftn.isa.fisherman.service;

public interface BoatSubscriptionService {
    void addSubscription(String clientUsername, Long boatId);
    void removeSubscription(String username, Long boatId);
    boolean checkIfUserIsSubscribed(String username, Long boatId);
}
