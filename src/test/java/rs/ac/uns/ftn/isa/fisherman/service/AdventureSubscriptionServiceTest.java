package rs.ac.uns.ftn.isa.fisherman.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import rs.ac.uns.ftn.isa.fisherman.model.Adventure;
import rs.ac.uns.ftn.isa.fisherman.repository.AdventureSubscriptionRepository;
import rs.ac.uns.ftn.isa.fisherman.service.impl.AdventureSubscriptionServiceImpl;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AdventureSubscriptionServiceTest {

    @Mock
    private AdventureSubscriptionRepository adventureSubscriptionRepositoryMock;
    @InjectMocks
    private AdventureSubscriptionServiceImpl adventureSubscriptionService;

    @Test
    public void testFindAdventureSubscribers(){
        Adventure adventure = new Adventure();
        adventure.setId(1L);

        when(adventureSubscriptionRepositoryMock.findAdventureSubscribers(1L)).thenReturn(new HashSet<>(Arrays.asList("testUser1","testUser2")));

        Set<String> subscribers = adventureSubscriptionService.findAdventureSubscribers(1L);

        assertThat(subscribers).hasSize(2);
    }
}
