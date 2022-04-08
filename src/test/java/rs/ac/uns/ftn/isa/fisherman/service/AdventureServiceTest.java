package rs.ac.uns.ftn.isa.fisherman.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.assertj.core.api.Assertions.assertThat;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import rs.ac.uns.ftn.isa.fisherman.model.Address;
import rs.ac.uns.ftn.isa.fisherman.model.Adventure;
import rs.ac.uns.ftn.isa.fisherman.model.FishingInstructor;
import rs.ac.uns.ftn.isa.fisherman.repository.AdventureRepository;
import rs.ac.uns.ftn.isa.fisherman.service.impl.AdventureServiceImpl;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.mockito.Mockito.when;



@RunWith(SpringRunner.class)
@SpringBootTest
public class AdventureServiceTest {

    @Mock
    private AdventureRepository adventureRepositoryMock;
    @InjectMocks
    private AdventureServiceImpl adventureService;

    @Test
    public void testFindAll() {
        Address address= new Address(53,43,"Serbia","Novi Sad","Dunavska");
        Adventure adventure = new Adventure(5L,"Fishing adventure",address,"The best.","Licenced instructor.",3,43,"No kids.","Hooks and rails","FREE");

        when(adventureRepositoryMock.findAll()).thenReturn(Arrays.asList(adventure));

        List<Adventure> adventureSet = adventureService.findAll();

        assertThat(adventureSet).hasSize(1);

    }

    @Test
    public void testFindAdventureByNameAndOwnersId() {

        Address address= new Address(53,43,"Serbia","Novi Sad","Dunavska");
        FishingInstructor fishingInstructor= new FishingInstructor(1L,"Miroslav","Mirkovic","mirko@gmail.com","123","061435234",address,new HashSet<>(),new HashSet<>(),"bla bla");
        Adventure adventure = new Adventure(5L,"Fishing adventure",address,"The best.","Licenced instructor.",3,43,"No kids.","Hooks and rails","FREE");
        adventure.setFishingInstructor(fishingInstructor);


        when(adventureRepositoryMock.findAll()).thenReturn(Arrays.asList(adventure));
        when(adventureRepositoryMock.findAdventureByName("Fishing adventure",1L)).thenReturn(adventure);


        Adventure adventureResult = adventureService.findAdventureByName("Fishing adventure",1L);

        assertEquals(adventureResult,adventure);

    }

    @Test
    public void testNotFoundAdventureByNameAndOwnersId() {

        Address address= new Address(53,43,"Serbia","Novi Sad","Dunavska");
        FishingInstructor fishingInstructor= new FishingInstructor(1L,"Miroslav","Mirkovic","mirko@gmail.com","123","061435234",address,new HashSet<>(),new HashSet<>(),"bla bla");
        Adventure adventure = new Adventure(5L,"Fishing adventure",address,"The best.","Licenced instructor.",3,43,"No kids.","Hooks and rails","FREE");
        adventure.setFishingInstructor(fishingInstructor);


        when(adventureRepositoryMock.findAll()).thenReturn(Arrays.asList(adventure));
        when(adventureRepositoryMock.findAdventureByName("Fishing adventure",1L)).thenReturn(adventure);


        Adventure adventureResult = adventureService.findAdventureByName("Adventure",1L);

        assertNotEquals(adventureResult.getName(),"Adventure");

    }






}
