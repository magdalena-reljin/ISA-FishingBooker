package rs.ac.uns.ftn.isa.fisherman.dto;

import java.time.LocalDateTime;

public class CabinEvaluationDto extends EvaluationDto{
    private CabinDto cabinDto;
    private CabinReservationDto cabinReservationDto;

    public CabinEvaluationDto(Long id, LocalDateTime date, String comment, Double grade, boolean approved, String clientUsername, CabinDto cabinDto, CabinReservationDto cabinReservationDto) {
        super(id, date, comment, grade, approved, clientUsername);
        this.cabinDto = cabinDto;
        this.cabinReservationDto = cabinReservationDto;
    }

    public CabinEvaluationDto() {}

    public CabinDto getCabinDto() {
        return cabinDto;
    }

    public void setCabinDto(CabinDto cabinDto) {
        this.cabinDto = cabinDto;
    }

    public CabinReservationDto getCabinReservationDto() {
        return cabinReservationDto;
    }

    public void setCabinReservationDto(CabinReservationDto cabinReservationDto) {
        this.cabinReservationDto = cabinReservationDto;
    }
}
