package rs.ac.uns.ftn.isa.fisherman.service.impl;

import com.google.auth.Credentials;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import rs.ac.uns.ftn.isa.fisherman.model.Image;
import rs.ac.uns.ftn.isa.fisherman.service.AdventureService;
import rs.ac.uns.ftn.isa.fisherman.service.BoatService;
import rs.ac.uns.ftn.isa.fisherman.service.CabinService;
import rs.ac.uns.ftn.isa.fisherman.service.FirebaseService;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;
@Service
public class FirebaseServiceImpl implements FirebaseService {
    private String downloadUrl="https://firebasestorage.googleapis.com/v0/b/isafisherman-94973.appspot.com/o/%s?alt=media";
    private String tempUrl="";
    @Autowired
    private CabinService cabinService;
    @Autowired
    private AdventureService adventureService;
    @Autowired
    private BoatService boatService;
    private String uploadFile(File file, String fileName) throws IOException {
        String currentDirectory = System.getProperty("user.dir");
        BlobId blobId = BlobId.of("isafisherman-94973.appspot.com", fileName);
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId).setContentType("media").build();
        Credentials credentials = GoogleCredentials.fromStream(new FileInputStream(currentDirectory+"/src/main/resources/firebaseToken.json"));
        Storage storage = StorageOptions.newBuilder().setCredentials(credentials).build().getService();
        storage.create(blobInfo, Files.readAllBytes(file.toPath()));
        return String.format(downloadUrl, URLEncoder.encode(fileName, StandardCharsets.UTF_8));
    }
    private File convertToFile(MultipartFile multipartFile, String fileName) throws IOException  //NOSONAR
    {
        File tempFile = new File(fileName);
        try (FileOutputStream fos = new FileOutputStream(tempFile)) {
            fos.write(multipartFile.getBytes());
            fos.close();
        }
        return tempFile;
    }

    private String getExtension(String fileName) {
        return fileName.substring(fileName.lastIndexOf("."));
    }
    private String upload(MultipartFile multipartFile) {

        try {
            String fileName = multipartFile.getOriginalFilename();
            fileName = UUID.randomUUID().toString().concat(this.getExtension(fileName));

            File file = this.convertToFile(multipartFile, fileName);
            tempUrl = this.uploadFile(file, fileName);
            file.delete();
            return  fileName;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }
    
    @Override
    public void download(String fileName) throws IOException {
        String currentDirectory = System.getProperty("user.dir");
        String destFilePath = currentDirectory+"\\src\\frontend\\src\\assets\\" + fileName;
        Credentials credentials = GoogleCredentials.fromStream(new FileInputStream(currentDirectory+"/src/main/resources/firebaseToken.json"));
        Storage storage = StorageOptions.newBuilder().setCredentials(credentials).build().getService();
        Blob blob = storage.get(BlobId.of("isafisherman-94973.appspot.com", fileName));
        blob.downloadTo(Paths.get(destFilePath));
    }

    @Override
    public void uploadCabinImage(MultipartFile newImage,String cabinName) throws IOException {
        String newFileName= upload(newImage);
        download(newFileName);
        Image image = new Image(null,newFileName);
        cabinService.addNewImage(cabinName,image);
    }

    @Override
    public void uploadAdventureImage(MultipartFile newImage,String adventureName) throws IOException {
        String newFileName= upload(newImage);
        download(newFileName);
        Image image = new Image(null,newFileName);
        adventureService.addNewImage(adventureName,image);
    }

    @Override
    public void uploadBoatImage(MultipartFile newImage,String boatName) throws IOException {
        String newFileName= upload(newImage);
        download(newFileName);
        Image image = new Image(null,newFileName);
        boatService.addNewImage(boatName,image);
    }



}
