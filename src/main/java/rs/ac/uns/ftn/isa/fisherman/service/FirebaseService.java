package rs.ac.uns.ftn.isa.fisherman.service;
import org.springframework.web.multipart.MultipartFile;
import rs.ac.uns.ftn.isa.fisherman.dto.ImageDto;
import java.io.IOException;
import java.util.Set;

public interface FirebaseService {
     void download(String fileName) throws IOException;
     void uploadCabinImage(MultipartFile images,String cabinName) throws IOException;
     void uploadAdventureImage(MultipartFile newImage,String adventureName)throws IOException;
}
