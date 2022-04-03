package rs.ac.uns.ftn.isa.fisherman.mapper;
import rs.ac.uns.ftn.isa.fisherman.dto.QuickReservationAdventureDto;
import rs.ac.uns.ftn.isa.fisherman.model.FishingInstructor;
import rs.ac.uns.ftn.isa.fisherman.model.QuickReservationAdventure;

public class QuickReservationAdventureMapper {
    private AdditionalServiceMapper additionalServiceMapper = new AdditionalServiceMapper();
    private AdventureMapper adventureMapper = new AdventureMapper();
    private PaymentInformationMapper paymentInformationMapper=new PaymentInformationMapper();
    public QuickReservationAdventure dtoToQuickReservationAdventure(QuickReservationAdventureDto quickReservationAdventureDto, FishingInstructor fishingInstructor){
        return new QuickReservationAdventure(quickReservationAdventureDto.getId(),quickReservationAdventureDto.getStartDate(),quickReservationAdventureDto.getEndDate(),null,paymentInformationMapper.dtoToPaymentInformation(quickReservationAdventureDto.getPaymentInformationDto())
        ,adventureMapper.adventureDtoToAdventure(quickReservationAdventureDto.getAdventureDto()),
                fishingInstructor,quickReservationAdventureDto.getDiscount(),additionalServiceMapper.additionalServicesDtoToAdditionalServices(quickReservationAdventureDto.getAddedAdditionalServices()));
    }
    public QuickReservationAdventureDto quickAdventureReservationToQuickAdventureReservationDto(QuickReservationAdventure quickReservationAdventure) {
        String fullName="";
        String clientUsername= "";
        if(quickReservationAdventure.getClient() !=null){
            clientUsername=quickReservationAdventure.getClient().getUsername();
            fullName=quickReservationAdventure.getClient().getName()+" "+quickReservationAdventure.getClient().getLastName();
        }

        return new QuickReservationAdventureDto(quickReservationAdventure.getId(),quickReservationAdventure.getStartDate(),
                quickReservationAdventure.getEndDate(), clientUsername, fullName,paymentInformationMapper
                .paymentInformationToDto(quickReservationAdventure.getPaymentInformation()),quickReservationAdventure.isSuccessfull(),
                adventureMapper.adventureToAdventureDto(quickReservationAdventure.getAdventure()),additionalServiceMapper
                .additionalServicesToAdditionalServiceDtoS(quickReservationAdventure.getAddedAdditionalServices())
,quickReservationAdventure.getDiscount());
    }
}
