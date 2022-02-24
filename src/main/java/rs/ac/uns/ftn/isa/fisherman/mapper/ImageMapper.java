package rs.ac.uns.ftn.isa.fisherman.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import rs.ac.uns.ftn.isa.fisherman.dto.ImageDto;
import rs.ac.uns.ftn.isa.fisherman.model.Cabin;
import rs.ac.uns.ftn.isa.fisherman.model.Image;
import rs.ac.uns.ftn.isa.fisherman.service.CabinService;

import java.util.HashSet;
import java.util.Set;

public class ImageMapper {

    public Image ImageDtoToImage(ImageDto image){
        return new Image(image.getId(),image.getUrl());
    }

    public ImageDto ImageToImageDto(Image image) { return new ImageDto(image.getId(),image.getUrl());}

    public Set<Image> ImageDtosToImages(Set<ImageDto> imageDtos){
        Set<Image> images = new HashSet<>();
        for(ImageDto imageDto: imageDtos)
            images.add(ImageDtoToImage(imageDto));
        return images;
    }
    public Set<ImageDto> ImageToImageDtos(Set<Image> images){
        Set<ImageDto> imagesDtos = new HashSet<>();
        for(Image image: images)
            imagesDtos.add(ImageToImageDto(image));
        return imagesDtos;
    }
}
