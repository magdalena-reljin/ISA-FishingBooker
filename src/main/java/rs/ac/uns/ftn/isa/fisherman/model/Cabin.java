package rs.ac.uns.ftn.isa.fisherman.model;

import javax.persistence.*;

@Entity
@Table(name="cabins")
public class Cabin {
    @Id
    @SequenceGenerator(name = "cabin_sequence_generator", sequenceName = "cabin_sequence", initialValue = 100)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cabin_sequence_generator")
    @Column(name = "id", unique = true)
    protected Long id;

    @Column(name="name")
    protected String name;

    @Column(name="description")
    protected String description;

    @Embedded
    private  Address address;
}
