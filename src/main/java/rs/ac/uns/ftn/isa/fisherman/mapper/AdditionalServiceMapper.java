package rs.ac.uns.ftn.isa.fisherman.mapper;

import rs.ac.uns.ftn.isa.fisherman.dto.AdditionalServicesDto;
import rs.ac.uns.ftn.isa.fisherman.model.AdditionalServices;

import java.util.Optional;

public class AdditionalServiceMapper {
    public AdditionalServices AdditionalServiceDtoToAdditionalService(AdditionalServicesDto additionalServicesDto){
        return new AdditionalServices((Long)additionalServicesDto.getId(),additionalServicesDto.getName(),additionalServicesDto.getPrice());
    }
    public AdditionalServicesDto AdditionalServiceToAdditionalServiceDto(AdditionalServices additionalServices){
        return new AdditionalServicesDto(additionalServices.getId(),additionalServices.getName(),additionalServices.getPrice());
    }
}
