package rs.ac.uns.ftn.isa.fisherman.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import rs.ac.uns.ftn.isa.fisherman.dto.CabinDto;
import rs.ac.uns.ftn.isa.fisherman.model.Cabin;
import rs.ac.uns.ftn.isa.fisherman.repository.CabinRepository;
import rs.ac.uns.ftn.isa.fisherman.service.CabinOwnerService;

public class CabinMapper {
    private AddressMapper addressMapper=new AddressMapper();
    private AdditionalServiceMapper additionalServiceMapper=new AdditionalServiceMapper();
    private ImageMapper imageMapper= new ImageMapper();
    @Autowired
    private CabinOwnerService cabinOwnerService;

    public Cabin CabinDtoToCabin(CabinDto cabin){

        return new Cabin(cabin.getId(),cabin.getName(),cabin.getDescription(),cabin.getNumOfRooms(),cabin.getBedsPerRoom(),
                cabin.getRules(),cabin.getPrice(),addressMapper.dtotoaddress(cabin.getAddress()),
                additionalServiceMapper.AdditionalServicesDtoToAdditionalServices(cabin.getAdditionalServices()),
                cabin.getRating(),imageMapper.ImageDtosToImages(cabin.getImages()),cabinOwnerService.findByUsername(cabin.getOwnerUsername()));
    }
}
