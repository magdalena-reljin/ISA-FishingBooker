package rs.ac.uns.ftn.isa.fisherman.service;

public interface CabinSubscriptionService {

    void addSubscription(String clientUsername, Long cabinId);
    void removeSubscription(Long id);

}
