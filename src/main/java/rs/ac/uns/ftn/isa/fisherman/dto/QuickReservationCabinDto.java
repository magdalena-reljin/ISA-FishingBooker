package rs.ac.uns.ftn.isa.fisherman.dto;

import java.time.LocalDateTime;
import java.util.Set;

public class QuickReservationCabinDto extends ReservationDto {
    private CabinDto cabinDto;
    private Set<AdditionalServicesDto> addedAdditionalServices;
    private Integer discount;

    public CabinDto getCabinDto() {
        return cabinDto;
    }

    public void setCabinDto(CabinDto cabinDto) {
        this.cabinDto = cabinDto;
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

    public QuickReservationCabinDto(Long id, LocalDateTime startDate, LocalDateTime endDate, String clientUsername, String clientFullName, PaymentInformationDto paymentInformationDto, boolean successfull,OwnersReportDto ownersReportDto, CabinDto cabinDto, Set<AdditionalServicesDto> addedAdditionalServices, Integer discount) {
        super(id, startDate, endDate, clientUsername, clientFullName, paymentInformationDto, successfull,ownersReportDto);
        this.cabinDto = cabinDto;
        this.addedAdditionalServices = addedAdditionalServices;
        this.discount = discount;
    }

    public QuickReservationCabinDto(){}
}
