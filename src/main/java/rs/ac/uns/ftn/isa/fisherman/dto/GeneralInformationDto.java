package rs.ac.uns.ftn.isa.fisherman.dto;

public class GeneralInformationDto {
    private Double ownerRating;
    private Double avgRating;
    private ReservationsPointsDto reservationsPointsDto;

    public GeneralInformationDto(Double ownerRating, Double avgRating, ReservationsPointsDto reservationsPointsDto) {
        this.ownerRating = ownerRating;
        this.avgRating = avgRating;
        this.reservationsPointsDto = reservationsPointsDto;
    }

    public GeneralInformationDto() {
    }

    public Double getOwnerRating() {
        return ownerRating;
    }

    public void setOwnerRating(Double ownerRating) {
        this.ownerRating = ownerRating;
    }

    public Double getAvgRating() {
        return avgRating;
    }

    public void setAvgRating(Double avgRating) {
        this.avgRating = avgRating;
    }

    public ReservationsPointsDto getReservationsPointsDto() {
        return reservationsPointsDto;
    }

    public void setReservationsPointsDto(ReservationsPointsDto reservationsPointsDto) {
        this.reservationsPointsDto = reservationsPointsDto;
    }
}
