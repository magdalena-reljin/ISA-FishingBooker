package rs.ac.uns.ftn.isa.fisherman.dto;

import java.time.LocalDateTime;
import java.util.Set;

public class QuickReservationAdventureDto extends  ReservationDto{
    private AdventureDto adventureDto;
    private Set<AdditionalServicesDto> addedAdditionalServices;
    private Integer discount;

    public QuickReservationAdventureDto(Long id, LocalDateTime startDate, LocalDateTime endDate, Double price, String clientUsername, String clientFullName, AdventureDto adventureDto, Set<AdditionalServicesDto> addedAdditionalServices, Integer discount) {
        super(id, startDate, endDate, price, clientUsername, clientFullName);
        this.adventureDto = adventureDto;
        this.addedAdditionalServices = addedAdditionalServices;
        this.discount = discount;
    }

    public QuickReservationAdventureDto() {}

    public AdventureDto getAdventureDto() {
        return adventureDto;
    }

    public void setAdventureDto(AdventureDto adventureDto) {
        this.adventureDto = adventureDto;
    }

    public Set<AdditionalServicesDto> getAddedAdditionalServices() {
        return addedAdditionalServices;
    }

    public void setAddedAdditionalServices(Set<AdditionalServicesDto> addedAdditionalServices) {
        this.addedAdditionalServices = addedAdditionalServices;
    }

    public Integer getDiscount() {
        return discount;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }
}