package rs.ac.uns.ftn.isa.fisherman.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@MappedSuperclass
public class Reservation {

    @Id
    @SequenceGenerator(name = "period_sequence_generator", sequenceName = "period_sequence", initialValue = 100)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "period_sequence_generator")
    @Column(name = "id", unique = true)
    protected Long id;
    @Column(name= "startDate")
    private LocalDateTime startDate;
    @Column(name= "endDate")
    private LocalDateTime endDate;
    @Embedded
    private PaymentInformation paymentInformation;
    @ManyToOne(cascade=CascadeType.MERGE)
    @JoinColumn(name="users_id")
    protected Client client;
    @Column(name = "successfull")
    private boolean successfull;
    @Embedded
    private OwnersReport ownersReport;
    public Reservation(Long id, LocalDateTime startDate, LocalDateTime endDate, Client client, PaymentInformation paymentInformation,OwnersReport ownersReport) {
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.client = client;
        this.successfull=true;
        this.paymentInformation=paymentInformation;
        this.ownersReport=ownersReport;
    }

    public Reservation(Long id, LocalDateTime startDate, LocalDateTime endDate) {
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.client = new Client();
        this.successfull=true;
    }

    public OwnersReport getOwnersReport() {
        return ownersReport;
    }

    public void setOwnersReport(OwnersReport ownersReport) {
        this.ownersReport = ownersReport;
    }

    public Reservation(){}

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

    public PaymentInformation getPaymentInformation() {
        return paymentInformation;
    }

    public void setPaymentInformation(PaymentInformation paymentInformation) {
        this.paymentInformation = paymentInformation;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public boolean isSuccessfull() {
        return successfull;
    }

    public void setSuccessfull(boolean successfull) {
        this.successfull = successfull;
    }

}
