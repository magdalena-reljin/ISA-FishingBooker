package rs.ac.uns.ftn.isa.fisherman.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.ftn.isa.fisherman.dto.CabinEvaluationDto;
import rs.ac.uns.ftn.isa.fisherman.mapper.CabinEvaluationMapper;
import rs.ac.uns.ftn.isa.fisherman.model.CabinEvaluation;
import rs.ac.uns.ftn.isa.fisherman.service.CabinEvaluationService;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping(value = "/cabinEvaluation", produces = MediaType.APPLICATION_JSON_VALUE)
public class CabinEvaluationController {

    @Autowired
    CabinEvaluationService cabinEvaluationService;
    private final CabinEvaluationMapper cabinEvaluationMapper=new CabinEvaluationMapper();

    @PreAuthorize("hasRole('CLIENT')")
    @PostMapping("/addEvaluation")
    public ResponseEntity<String> addEvaluation(@RequestBody CabinEvaluationDto cabinEvaluationDto){
        if(cabinEvaluationService.addEvaluation(cabinEvaluationDto)){
            return new ResponseEntity<>("Evaluation successfully added.", HttpStatus.OK);
        }else{
            return new ResponseEntity<>("Reservation already has evaluation!", HttpStatus.BAD_REQUEST);
        }
    }
    @PreAuthorize("hasRole('CABINOWNER')")
    @GetMapping("/findByCabinId/{cabinId}")
    public ResponseEntity<Set<CabinEvaluationDto>> findByCabinId(@PathVariable("cabinId")Long cabinId){
        Set<CabinEvaluationDto> cabinEvaluations=new HashSet<>();
        for(CabinEvaluation cabinEvaluation: cabinEvaluationService.findByCabinId(cabinId))
            cabinEvaluations.add(cabinEvaluationMapper.cabinEvaluationToDto(cabinEvaluation));
        return new ResponseEntity<>(cabinEvaluations, HttpStatus.BAD_REQUEST);
    }
}
