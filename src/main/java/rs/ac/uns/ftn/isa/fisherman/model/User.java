package rs.ac.uns.ftn.isa.fisherman.model;
import javax.persistence.*;

@Entity
@Table(name="users")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "role", discriminatorType = DiscriminatorType.STRING)
public abstract class User {

    @Id
    @SequenceGenerator(name = "account_sequence_generator", sequenceName = "account_sequence", initialValue = 100)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "account_sequence_generator")
    @Column(name = "id", unique = true)
    protected Long id;

    @Column(name="name")
    protected String name;
    @Column(name="lastName")
    protected String lastName;
    @Column(name="email")
    protected String email;
    @Column(name="password")
    protected String password;
    @Column(name="phoneNum")
    protected String phoneNum;
    @Embedded
    private Address address;
    public User(){}
    public User(Long id, String name, String lastName, String email, String password, String phoneNum, Address address) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.phoneNum = phoneNum;
        this.address = address;

    }
}
