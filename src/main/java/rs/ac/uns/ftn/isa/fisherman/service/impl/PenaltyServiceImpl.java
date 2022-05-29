package rs.ac.uns.ftn.isa.fisherman.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.isa.fisherman.model.Penalty;
import rs.ac.uns.ftn.isa.fisherman.repository.PenaltyRepository;
import rs.ac.uns.ftn.isa.fisherman.service.ClientService;
import rs.ac.uns.ftn.isa.fisherman.service.PenaltyService;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PenaltyServiceImpl implements PenaltyService {

    @Autowired
    private PenaltyRepository penaltyRepository;
    @Autowired
    private ClientService clientService;

    public void addPenalty(String clientUsername){
        deleteOldPenalties();
        penaltyRepository.save(new Penalty(null, LocalDateTime.now(), clientService.findByUsername(clientUsername)));
    }

    public boolean isUserBlockedFromReservation(String username){
        deleteOldPenalties();
        return penaltyRepository.isUserBlockedFromReservation(clientService.findByUsername(username).getId());
    }

    public List<Penalty> getUserPenalties(String username){
        deleteOldPenalties();
        return penaltyRepository.getUserPenalties(clientService.findByUsername(username).getId());
    }

    @Override
    public void deleteOldPenalties() {
        penaltyRepository.deleteOldPenalties(LocalDateTime.now().withDayOfMonth(1));
    }
}
