package competition.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "team")
public class Team extends AuditModel implements Serializable {

    private static final Long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "countrySeq", sequenceName = "country_sequence", initialValue = 1, allocationSize = 1)
    @GeneratedValue(generator = "countrySeq", strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "name", nullable = false, columnDefinition = "varchar(255)")
    private String name;

    @ManyToOne(targetEntity = Country.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "country_id")
    private Country country;

    public Team() {
        super();
    }

    public Team(String name, Country country) {
        super();
        this.name = name;
        this.country = country;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }
}
