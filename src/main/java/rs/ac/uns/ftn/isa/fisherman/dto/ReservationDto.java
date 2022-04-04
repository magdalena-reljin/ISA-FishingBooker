package rs.ac.uns.ftn.isa.fisherman.dto;


import java.time.LocalDateTime;

public class ReservationDto {

    protected Long id;
    protected LocalDateTime startDate;
    protected LocalDateTime endDate;
    protected String clientUsername;
    protected String clientFullName;
    private boolean evaluated;
    private PaymentInformationDto paymentInformationDto;
    private boolean successfull;
    private OwnersReportDto ownersReportDto;

    public boolean isEvaluated() {
        return evaluated;
    }

    public void setEvaluated(boolean evaluated) {
        this.evaluated = evaluated;
    }

    public ReservationDto(Long id, LocalDateTime startDate, LocalDateTime endDate, String clientUsername, String clientFullName, PaymentInformationDto paymentInformationDto, boolean successfull,OwnersReportDto ownersReportDto) {
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.clientUsername = clientUsername;
        this.clientFullName = clientFullName;
        this.paymentInformationDto = paymentInformationDto;
        this.successfull = successfull;
        this.ownersReportDto=ownersReportDto;
    }

    public OwnersReportDto getOwnersReportDto() {
        return ownersReportDto;
    }

    public void setOwnersReportDto(OwnersReportDto ownersReportDto) {
        this.ownersReportDto = ownersReportDto;
    }

    public ReservationDto(){}

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

    public String getClientUsername() {
        return clientUsername;
    }

    public void setClientUsername(String clientUsername) {
        this.clientUsername = clientUsername;
    }

    public String getClientFullName() {
        return clientFullName;
    }

    public void setClientFullName(String clientFullName) {
        this.clientFullName = clientFullName;
    }

    public PaymentInformationDto getPaymentInformationDto() {
        return paymentInformationDto;
    }

    public void setPaymentInformationDto(PaymentInformationDto paymentInformationDto) {
        this.paymentInformationDto = paymentInformationDto;
    }

    public boolean isSuccessfull() {
        return successfull;
    }

    public void setSuccessfull(boolean successfull) {
        this.successfull = successfull;
    }
}
