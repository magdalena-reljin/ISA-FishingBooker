package rs.ac.uns.ftn.isa.fisherman.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import rs.ac.uns.ftn.isa.fisherman.model.*;
import rs.ac.uns.ftn.isa.fisherman.repository.PenaltyRepository;
import rs.ac.uns.ftn.isa.fisherman.service.impl.PenaltyServiceImpl;

import java.time.LocalDateTime;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PenaltyServiceTest {

    @Mock
    private ClientService clientService;
    @Mock
    private PenaltyRepository penaltyRepository;
    @InjectMocks
    private PenaltyServiceImpl penaltyService;

    @Test
    public void testGetUserPenalties() {
        Client client = new Client(1L, "testUser");
        Penalty penalty = new Penalty(1L, LocalDateTime.now(), client);
        when(penaltyRepository.getUserPenalties(1L)).thenReturn(Arrays.asList(penalty));
        when(clientService.findByUsername("testUser")).thenReturn(client);
        List<Penalty> penaltySet = penaltyService.getUserPenalties("testUser");

        assertThat(penaltySet).hasSize(1);
    }

    @Test
    public void testAddNewPenalty() {
        Client client = new Client(1L, "testUser");
        Penalty penalty = new Penalty(1L, LocalDateTime.now(), client);

        when(clientService.findByUsername("testUser")).thenReturn(client);
        when(penaltyRepository.getUserPenalties(1L)).thenReturn(Arrays.asList(penalty));

        penaltyService.addPenalty("testUser");

        List<Penalty> penaltySet = penaltyService.getUserPenalties("testUser");

        assertThat(penaltySet).isNotNull();
        assertThat(penaltySet).hasSize(1);
    }

    @Test
    public void testIsUserBlocked(){
        Client client = new Client(1L, "testUser");

        when(clientService.findByUsername("testUser")).thenReturn(client);
        when(penaltyRepository.isUserBlockedFromReservation(1L)).thenReturn(true);

        assertThat(penaltyService.isUserBlockedFromReservation("testUser")).isTrue();
    }
}
