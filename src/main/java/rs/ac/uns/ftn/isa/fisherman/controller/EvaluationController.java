package rs.ac.uns.ftn.isa.fisherman.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/evaluations", produces = MediaType.APPLICATION_JSON_VALUE)
public class EvaluationController {
   /* @GetMapping("/getAllEvaluations")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<EvaluationDto>> getAllReports () {
        Set<OwnersReportDto>reports= new HashSet<>();
        for(OwnersReport ownersReport: ownersReportService.getAllReports())
            reports.add(ownersReportMapper.ownersReportToDto(ownersReport));
        return new ResponseEntity<>(reports, HttpStatus.OK);
    }

    @PostMapping(value= "/sendReviewResponse")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> sendReviewResponse(@RequestBody OwnersReportDto ownersReportDto) {
        ownersReportService.sendReviewResponse(ownersReportDto.getClientUsername(),ownersReportDto.getOwnersUsername(),ownersReportDto.getComment());
        ownersReportService.setReviewStatus(ownersReportDto.getId());
        return new ResponseEntity<>("Success", HttpStatus.OK);
    }*/

}
