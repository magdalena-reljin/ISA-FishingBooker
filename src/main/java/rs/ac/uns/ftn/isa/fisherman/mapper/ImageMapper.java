package rs.ac.uns.ftn.isa.fisherman.mapper;
import rs.ac.uns.ftn.isa.fisherman.dto.ImageDto;
import rs.ac.uns.ftn.isa.fisherman.model.Image;
import java.util.HashSet;
import java.util.Set;

public class ImageMapper {

    public Image ImageDtoToImage(ImageDto image){
        return new Image(image.getId(),image.getUrl());
    }

    public ImageDto ImageToImageDto(Image image) { return new ImageDto(image.getId(),image.getUrl());}

    public Set<Image> ImageDtoSToImages(Set<ImageDto> imageDtoS){
        Set<Image> images = new HashSet<>();
        for(ImageDto imageDto: imageDtoS)
            images.add(ImageDtoToImage(imageDto));
        return images;
    }
    public Set<ImageDto> ImageToImageDtoS(Set<Image> images){
        Set<ImageDto> imagesDtoS = new HashSet<>();
        for(Image image: images)
            imagesDtoS.add(ImageToImageDto(image));
        return imagesDtoS;
    }
}
