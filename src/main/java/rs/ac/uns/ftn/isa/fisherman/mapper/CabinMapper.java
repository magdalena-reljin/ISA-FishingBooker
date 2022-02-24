package rs.ac.uns.ftn.isa.fisherman.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import rs.ac.uns.ftn.isa.fisherman.dto.CabinDto;
import rs.ac.uns.ftn.isa.fisherman.model.Cabin;
import rs.ac.uns.ftn.isa.fisherman.service.CabinOwnerService;

public class CabinMapper {
    private AddressMapper addressMapper=new AddressMapper();
    @Autowired
    private CabinOwnerService cabinOwnerService;

    public Cabin CabinDtoToCabin(CabinDto cabin){
        return new Cabin(cabin.getId(),cabin.getName(),cabin.getDescription(),cabin.getNumOfRooms(),cabin.getBedsPerRoom(),
                cabin.getRules(),cabin.getPrice(),addressMapper.dtotoaddress(cabin.getAddressDto()),
                cabin.getRating());

    }
    public CabinDto CabinToCabinDto(Cabin cabin){
        ImageMapper imageMapper=new ImageMapper();
        AdditionalServiceMapper additionalServiceMapper=new AdditionalServiceMapper();
        return new CabinDto(cabin.getId(),cabin.getName(),cabin.getDescription(),cabin.getNumOfRooms(),cabin.getBedsPerRoom(),
                cabin.getRules(),cabin.getPrice(),addressMapper.adressToDTO(cabin.getAddress()),
                additionalServiceMapper.AdditionalServicesToAdditionalServiceDtos(cabin.getAdditionalServices()),
                cabin.getRating(),imageMapper.ImageToImageDtos(cabin.getImages()),cabin.getCabinOwner().getUsername());
    }
}
