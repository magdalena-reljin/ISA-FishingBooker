package rs.ac.uns.ftn.isa.fisherman.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.ftn.isa.fisherman.dto.AdventureDto;
import rs.ac.uns.ftn.isa.fisherman.dto.FishingInstructorDto;
import rs.ac.uns.ftn.isa.fisherman.mapper.AdditionalServiceMapper;
import rs.ac.uns.ftn.isa.fisherman.mapper.AdventureMapper;
import rs.ac.uns.ftn.isa.fisherman.model.Adventure;
import rs.ac.uns.ftn.isa.fisherman.model.FishingInstructor;
import rs.ac.uns.ftn.isa.fisherman.service.AdventureService;
import rs.ac.uns.ftn.isa.fisherman.service.FishingInstructorService;
import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping(value = "/adventures", produces = MediaType.APPLICATION_JSON_VALUE)
public class AdventureController {

    private static final String success= "Success.";
    @Autowired
    private FishingInstructorService fishingInstructorService;
    @Autowired
    private AdventureService adventureService;

    private final AdditionalServiceMapper additionalServiceMapper=new AdditionalServiceMapper();
    private final AdventureMapper adventureMapper=new AdventureMapper();

    @PreAuthorize("hasRole('FISHING_INSTRUCTOR')")
    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestBody AdventureDto adventureDto){
        boolean services=false;
        Adventure adventure=adventureMapper.AdventureDtoToAdventure(adventureDto);
        adventure.setFishingInstructor(fishingInstructorService.findByUsername(adventureDto.getFishingInstructorUsername()));
        adventureService.save(adventure);

       if(adventureDto.getAdditionalServices()!=null) {
            adventure.setAdditionalServices(additionalServiceMapper.AdditionalServicesDtoToAdditionalServices(adventureDto.getAdditionalServices()));
            services=true;
        }
        if(services)
            adventureService.save(adventure);
        return new ResponseEntity<>(success, HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('FISHING_INSTRUCTOR')")
    @PostMapping("/findAdventuresByInstructorUsername")
    public ResponseEntity<Set<AdventureDto>> findAdventuresByInstructorUsername(@RequestBody FishingInstructorDto instructor){
        Set<AdventureDto> adventures=new HashSet<>();
        for(Adventure adventure: adventureService.findAdventuresByInstructorId(fishingInstructorService.findByUsername(instructor.getUsername()).getId()))
            adventures.add(adventureMapper.AdventureToAdventureDto(adventure));
        return new ResponseEntity<>(adventures, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('CLIENT')")
    @GetMapping("/getAll")
    public ResponseEntity<Set<AdventureDto>> getAll(){
        Set<AdventureDto> adventures=new HashSet<>();
        for(Adventure adventure: adventureService.findAll())
            adventures.add(adventureMapper.AdventureToAdventureDto(adventure));
        return new ResponseEntity<>(adventures, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('FISHING_INSTRUCTOR')")
    @PostMapping("/findByName")
    public ResponseEntity<AdventureDto> findByName(@RequestBody AdventureDto adventureDto){
        Long fishingInstructorId= fishingInstructorService.findByUsername(adventureDto.getFishingInstructorUsername()).getId();
        String adventureName= adventureDto.getName();
        Adventure adventure= adventureService.findAdventureByName(adventureName,fishingInstructorId);
        return new ResponseEntity<>(adventureMapper.AdventureToAdventureDto(adventure), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('FISHING_INSTRUCTOR')")
    @PostMapping("/deleteAdventure")
    public ResponseEntity<String> deleteAdventure(@RequestBody AdventureDto adventureDto){
        adventureService.delete(adventureDto.getId());
        return new ResponseEntity<>(success, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('FISHING_INSTRUCTOR')")
    @PostMapping("/edit")
    public ResponseEntity<String> editAdventure(@RequestBody AdventureDto adventureDto){
        FishingInstructor fishingInstructor= fishingInstructorService.findByUsername(adventureDto.getFishingInstructorUsername());
        Adventure adventure = adventureMapper.AdventureDtoToEditAdventure(adventureDto);
        adventureService.edit(adventure,fishingInstructor.getId());
        return new ResponseEntity<>(success, HttpStatus.OK);
    }
}
