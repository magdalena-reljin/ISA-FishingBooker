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
    @Column(name= "price", nullable = false)
    private Double price;
    @ManyToOne
    @JoinColumn(name="users_id")
    protected Client client;

    public Reservation(Long id, LocalDateTime startDate, LocalDateTime endDate, Double price, Client client) {
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.price = price;
        this.client = client;
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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}
