package rs.ac.uns.ftn.isa.fisherman.mapper;

import rs.ac.uns.ftn.isa.fisherman.dto.AdditionalServicesDto;
import rs.ac.uns.ftn.isa.fisherman.model.AdditionalServices;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class AdditionalServiceMapper {
    public AdditionalServices AdditionalServiceDtoToAdditionalService(AdditionalServicesDto additionalServicesDto){
        return new AdditionalServices((Long)additionalServicesDto.getId(),additionalServicesDto.getName(),additionalServicesDto.getPrice());
    }
    public AdditionalServicesDto AdditionalServiceToAdditionalServiceDto(AdditionalServices additionalServices){
        return new AdditionalServicesDto(additionalServices.getId(),additionalServices.getName(),additionalServices.getPrice());
    }
    public Set<AdditionalServices> AdditionalServicesDtoToAdditionalServices(Set<AdditionalServicesDto> additionalServicesDtos){
        Set<AdditionalServices> additionalServices = new HashSet<>();
        for(AdditionalServicesDto additionalServiceDto: additionalServicesDtos)
              additionalServices.add(AdditionalServiceDtoToAdditionalService(additionalServiceDto));
        return additionalServices;
    }
    public Set<AdditionalServicesDto> AdditionalServicesToAdditionalServiceDtos(Set<AdditionalServices> additionalServices){
        Set<AdditionalServicesDto> additionalServiceDtos = new HashSet<>();
        for(AdditionalServices additionalService: additionalServices)
            additionalServiceDtos.add(AdditionalServiceToAdditionalServiceDto(additionalService));
        return additionalServiceDtos;
    }
}
