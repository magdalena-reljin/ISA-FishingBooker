package rs.ac.uns.ftn.isa.fisherman.dto;

import java.time.LocalDateTime;

public class SearchAvailablePeriodsDto {
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private double price;

    public SearchAvailablePeriodsDto(LocalDateTime startDate, LocalDateTime endDate, double price) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.price = price;
    }

    public SearchAvailablePeriodsDto() {}

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
