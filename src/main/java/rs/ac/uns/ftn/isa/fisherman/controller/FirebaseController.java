package rs.ac.uns.ftn.isa.fisherman.controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import rs.ac.uns.ftn.isa.fisherman.dto.ImageDto;
import rs.ac.uns.ftn.isa.fisherman.service.FirebaseService;
import java.io.IOException;

@RestController
@RequestMapping(value = "/firebase", produces = MediaType.APPLICATION_JSON_VALUE)
public class FirebaseController {

    @Autowired
    private FirebaseService firebaseService;
    private final Logger logger= LoggerFactory.getLogger(FirebaseController.class);

    @RequestMapping(value="/uploadCabinImage/{cabin}",method = {RequestMethod.POST})
    public void uploadCabinImage(@PathVariable("cabin") String cabin,@RequestParam("file") MultipartFile multipartFile) {
        try {
            firebaseService.uploadCabinImage(multipartFile,cabin);
        } catch (IOException e) {
            logger.error(e.toString());
        }
    }

    @PostMapping("/download")
    public void download(@RequestBody ImageDto img) throws IOException {
        firebaseService.download(img.getUrl());
    }

    @RequestMapping(value="/uploadAdventureImage/{adventure}",method = {RequestMethod.POST})
    public void uploadAdventureImage(@PathVariable("adventure") String adventure,@RequestParam("file") MultipartFile multipartFile) {
        try {
            firebaseService.uploadAdventureImage(multipartFile,adventure);
        } catch (IOException e) {
            logger.error(e.toString());
        }
    }

    @RequestMapping(value="/uploadBoatImage/{boat}",method = {RequestMethod.POST})
    public void uploadBoatImage(@PathVariable("boat") String boat,@RequestParam("file") MultipartFile multipartFile) {
        System.out.println("HIT -/upload | File Name : {}"+ multipartFile.getOriginalFilename());
        try {
            firebaseService.uploadBoatImage(multipartFile,boat);
        } catch (IOException e) {
            logger.error(e.toString());
        }
    }


}
