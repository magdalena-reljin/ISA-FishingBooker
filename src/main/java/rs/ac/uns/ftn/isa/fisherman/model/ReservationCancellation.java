package rs.ac.uns.ftn.isa.fisherman.model;

import javax.persistence.*;

@MappedSuperclass
public class ReservationCancellation {

    @Id
    @SequenceGenerator(name = "period_sequence_generator", sequenceName = "period_sequence", initialValue = 100)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "period_sequence_generator")
    @Column(name = "id", unique = true)
    protected Long id;
    @ManyToOne(cascade=CascadeType.MERGE)
    @JoinColumn(name="users_id")
    protected Client client;

    public ReservationCancellation(Long id, Client client) {
        this.id = id;
        this.client = client;
    }

    public ReservationCancellation(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}
