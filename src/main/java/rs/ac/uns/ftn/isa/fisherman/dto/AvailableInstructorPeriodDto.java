package rs.ac.uns.ftn.isa.fisherman.dto;

import javax.persistence.Column;
import java.time.LocalDateTime;

public class AvailableInstructorPeriodDto {

    protected Long id;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String instructorUsername;

    public AvailableInstructorPeriodDto(Long id, LocalDateTime startDate, LocalDateTime endDate, String instructorUsername) {
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.instructorUsername = instructorUsername;
    }

    public AvailableInstructorPeriodDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public String getInstructorUsername() {
        return instructorUsername;
    }

    public void setInstructorUsername(String instructorUsername) {
        this.instructorUsername = instructorUsername;
    }
}
