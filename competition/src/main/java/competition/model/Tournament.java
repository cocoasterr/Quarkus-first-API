package competition.model;


import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "tournament")
public class Tournament extends AuditModel implements Serializable {

    private static final Long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "tournamentSeq", sequenceName = "tournament_sequence", initialValue = 1, allocationSize = 1)
    @GeneratedValue(generator = "tournamentSeq", strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    Long id;

    @Column(name = "name", nullable = false, columnDefinition = "varchar")
    String name;

    @ManyToOne(targetEntity = Country.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "country_id", nullable = false)
    Country country;

    public Tournament(String name, Country country) {
        this.name = name;
        this.country = country;
    }

    public Tournament() {
    }

    public Long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
