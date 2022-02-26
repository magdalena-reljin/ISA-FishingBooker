package rs.ac.uns.ftn.isa.fisherman.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.ftn.isa.fisherman.dto.AdventureDto;
import rs.ac.uns.ftn.isa.fisherman.mapper.AdditionalServiceMapper;
import rs.ac.uns.ftn.isa.fisherman.mapper.AdventureMapper;
import rs.ac.uns.ftn.isa.fisherman.model.Adventure;
import rs.ac.uns.ftn.isa.fisherman.service.AdventureService;
import rs.ac.uns.ftn.isa.fisherman.service.FishingInstructorService;

@RestController
@RequestMapping(value = "/adventures", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin
public class AdventureController {

    @Autowired
    private FishingInstructorService fishingInstructorService;
    @Autowired
    private AdventureService adventureService;

    private String success= "success";
    AdditionalServiceMapper additionalServiceMapper=new AdditionalServiceMapper();
    AdventureMapper adventureMapper=new AdventureMapper();
    @PreAuthorize("hasRole('FISHING_INSTRUCTOR')")
    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestBody AdventureDto adventureDto){

        System.out.println("JEBEM TI MATER"+adventureDto.getId());
        System.out.println("JEBEM TI MATER"+adventureDto.getCancelingCondition());
        System.out.println("JEBEM TI MATER"+adventureDto.getDescription());
        System.out.println("JEBEM TI MATER"+adventureDto.getEquipment());
        System.out.println("JEBEM TI MATER"+adventureDto.getFishingInstructorUsername());
        System.out.println("JEBEM TI MATER"+adventureDto.getName());
        System.out.println("JEBEM TI MATER"+adventureDto.getInstruktorsBiography());
        System.out.println("JEBEM TI MATER"+adventureDto.getRules());
        System.out.println("JEBEM TI MATER"+adventureDto.getMaxPeople());
        System.out.println("JEBEM TI MATER"+adventureDto.getPrice());


        Boolean services=false;
        Adventure adventure=adventureMapper.AdventureDtoToAdventure(adventureDto);
        adventure.setFishingInstructor(fishingInstructorService.findByUsername(adventureDto.getFishingInstructorUsername()));
        System.out.println("Instruktor"+adventure.getFishingInstructor().getUsername());
        adventureService.save(adventure);

       if(adventureDto.getAdditionalServices()!=null) {
            adventure.setAdditionalServices(additionalServiceMapper.AdditionalServicesDtoToAdditionalServices(adventureDto.getAdditionalServices()));
            services=true;
        }
        if(services)
            adventureService.save(adventure);
        return new ResponseEntity<>(success, HttpStatus.CREATED);
    }

}
