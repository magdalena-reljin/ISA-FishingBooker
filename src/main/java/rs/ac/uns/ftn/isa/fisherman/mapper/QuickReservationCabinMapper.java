package rs.ac.uns.ftn.isa.fisherman.mapper;

import com.google.api.services.storage.model.Bucket;
import rs.ac.uns.ftn.isa.fisherman.dto.AdditionalServicesDto;
import rs.ac.uns.ftn.isa.fisherman.dto.QuickReservationCabinDto;
import rs.ac.uns.ftn.isa.fisherman.model.AdditionalServices;
import rs.ac.uns.ftn.isa.fisherman.model.QuickReservationCabin;

import java.util.HashSet;
import java.util.Set;

public class QuickReservationCabinMapper {
    private final CabinMapper cabinMapper=new CabinMapper();
    private final AdditionalServiceMapper additionalServiceMapper=new AdditionalServiceMapper();
    private final PaymentInformationMapper paymentInformationMapper= new PaymentInformationMapper();
    private final OwnersReportMapper ownersReportMapper=new OwnersReportMapper();
    public QuickReservationCabin dtoToQuickReservation(QuickReservationCabinDto quickReservationCabinDto) {
        Set<AdditionalServices> additionalServicesSet=new HashSet<>();
        if(quickReservationCabinDto.getAddedAdditionalServices()!=null)
             additionalServicesSet=additionalServiceMapper.additionalServicesDtoToAdditionalServices(quickReservationCabinDto
                     .getAddedAdditionalServices());
        return new QuickReservationCabin(quickReservationCabinDto.getId(),quickReservationCabinDto.getStartDate(),
                quickReservationCabinDto.getEndDate(),null,
                paymentInformationMapper.dtoToPaymentInformation(quickReservationCabinDto.getPaymentInformationDto()),
                ownersReportMapper.dtoToOwnersReport(quickReservationCabinDto.getOwnersReportDto()),
                cabinMapper.cabinDtoEditToCabin(quickReservationCabinDto.getCabinDto()),quickReservationCabinDto.getDiscount(),additionalServicesSet);
    }
    public QuickReservationCabinDto quickReservationToDto(QuickReservationCabin quickReservationCabin) {
        Set<AdditionalServicesDto> additionalServicesSet=new HashSet<>();
        if(quickReservationCabin.getAddedAdditionalServices()!=null)
            additionalServicesSet=additionalServiceMapper.additionalServicesToAdditionalServiceDtoS(quickReservationCabin
                    .getAddedAdditionalServices());
        String clientUsername="";
        String clientFullName="";
        if(quickReservationCabin.getClient()!=null){
            clientUsername=quickReservationCabin.getClient().getUsername();
            clientFullName=quickReservationCabin.getClient().getName()+" "+quickReservationCabin.getClient().getLastName();
        }
        return new QuickReservationCabinDto(quickReservationCabin.getId(),quickReservationCabin.getStartDate(),
                quickReservationCabin.getEndDate(),clientUsername,clientFullName,
                paymentInformationMapper.paymentInformationToDto(quickReservationCabin.getPaymentInformation()),
                quickReservationCabin.isSuccessfull(),
                ownersReportMapper.ownersReportToDto(quickReservationCabin.getOwnersReport()),
                cabinMapper.cabinToCabinDto(quickReservationCabin.getCabin()),
                additionalServicesSet,quickReservationCabin.getDiscount());
    }
}
