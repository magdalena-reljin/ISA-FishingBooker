package rs.ac.uns.ftn.isa.fisherman.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import rs.ac.uns.ftn.isa.fisherman.dto.ImageDto;
import rs.ac.uns.ftn.isa.fisherman.model.Image;
import rs.ac.uns.ftn.isa.fisherman.service.CabinService;

import java.util.HashSet;
import java.util.Set;

public class ImageMapper {
    private CabinMapper cabinMapper = new CabinMapper();
    @Autowired
    private CabinService cabinService;
    public ImageDto ImageToImageDto(Image image){
        return new ImageDto(image.getId(),image.getUrl(),image.getCabin().getName());
    }
    public Image ImageDtoToImage(ImageDto image){
        return new Image(image.getId(),image.getUrl(),cabinService.findByName(image.getCabin()));
    }
    public Set<Image> ImageDtosToImages(Set<ImageDto> imageDtos){
        Set<Image> images = new HashSet<>();
        for(ImageDto imageDto: imageDtos)
            images.add(ImageDtoToImage(imageDto));
        return images;
    }
    public Set<ImageDto> ImagesToImageDtos(Set<Image> images){
        Set<ImageDto> imageDtos = new HashSet<>();
        for(Image image: images)
            imageDtos.add(ImageToImageDto(image));
        return imageDtos;
    }
}
