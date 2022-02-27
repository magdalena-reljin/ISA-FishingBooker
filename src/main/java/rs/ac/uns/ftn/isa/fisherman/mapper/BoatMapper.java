package rs.ac.uns.ftn.isa.fisherman.mapper;

import rs.ac.uns.ftn.isa.fisherman.dto.BoatDto;
import rs.ac.uns.ftn.isa.fisherman.model.Boat;

public class BoatMapper {
    private AddressMapper addressMapper=new AddressMapper();
    private AdditionalServiceMapper additionalServiceMapper=new AdditionalServiceMapper();

    public Boat BoatDtoToBoat(BoatDto boat){
        return new Boat(boat.getId(),boat.getName(),boat.getType(),boat.getLength(), boat.getEngineCode(), boat.getEnginePower(),
                boat.getMaxSpeed(), boat.getNavigationEquipment(), addressMapper.dtotoaddress(boat.getAddressDto()),boat.getDescription(),
                boat.getMaxPeople(), boat.getRules(), boat.getFishingEquipment(), boat.getPrice(), boat.getRating(), boat.getCancelingCondition());
    }

}
