package rs.ac.uns.ftn.isa.fisherman.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import rs.ac.uns.ftn.isa.fisherman.dto.CabinDto;
import rs.ac.uns.ftn.isa.fisherman.dto.ImageDto;
import rs.ac.uns.ftn.isa.fisherman.service.FirebaseService;

import java.io.IOException;

@RestController
@RequestMapping(value = "/firebase", produces = MediaType.APPLICATION_JSON_VALUE
)
@CrossOrigin
public class FirebaseController {
    @Autowired
    private FirebaseService firebaseService;

    @RequestMapping(value="/uploadCabinImage/{cabin}",method = {RequestMethod.POST})
    public void uploadCabinImage(@PathVariable("cabin") String cabin,@RequestParam("file") MultipartFile multipartFile) throws IOException {
        System.out.println("HIT -/upload | File Name : {}"+ multipartFile.getOriginalFilename());
        try {
            firebaseService.uploadCabinImage(multipartFile,cabin);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @PostMapping("/download")
    public void download(@RequestBody ImageDto img) throws IOException {
        System.out.println("HIT -/download | File Name : {}"+ img.getUrl());
        firebaseService.download(img.getUrl());
    }

    @RequestMapping(value="/uploadAdventureImage/{adventure}",method = {RequestMethod.POST})
    public void uploadAdventureImage(@PathVariable("adventure") String adventure,@RequestParam("file") MultipartFile multipartFile) throws IOException {
        System.out.println("HIT -/upload | File Name : {}"+ multipartFile.getOriginalFilename());
        try {
            firebaseService.uploadAdventureImage(multipartFile,adventure);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value="/uploadBoatImage/{boat}",method = {RequestMethod.POST})
    public void uploadBoatImage(@PathVariable("boat") String boat,@RequestParam("file") MultipartFile multipartFile) throws IOException {
        System.out.println("HIT -/upload | File Name : {}"+ multipartFile.getOriginalFilename());
        try {
            firebaseService.uploadBoatImage(multipartFile,boat);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
