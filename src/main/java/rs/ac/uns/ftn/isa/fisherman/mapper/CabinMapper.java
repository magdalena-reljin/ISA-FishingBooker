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

        Cabin c= new Cabin(cabin.getId(),cabin.getName(),cabin.getDescription(),cabin.getNumOfRooms(),cabin.getBedsPerRoom(),
                cabin.getRules(),cabin.getPrice(),addressMapper.dtotoaddress(cabin.getAddressDto()),
                cabin.getRating());

        System.out.println("user "+c.getId());
        System.out.println("user "+c.getName());
        System.out.println("user "+c.getDescription());
        System.out.println("user "+c.getNumOfRooms());
        System.out.println("user "+c.getBedsPerRoom());
        System.out.println("user "+c.getRules());
        System.out.println("user "+c.getPrice());
        System.out.println("user "+c.getAddress().getLatitude());
        System.out.println("user "+c.getAddress().getLongitude());
        System.out.println("user "+c.getAddress().getStreetAndNum());
        System.out.println("user "+c.getAddress().getCity());
        System.out.println("user "+c.getAddress().getCountry());
        System.out.println("user "+c.getRating());
        return c;
    }
}
