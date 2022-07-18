package competition.model;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name = "country")
public class Country extends AuditModel implements Serializable{

    private static final Long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "countrySeq", sequenceName = "country_sequence", initialValue = 1, allocationSize = 1)
    @GeneratedValue(generator = "countrySeq", strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", unique = true, nullable = false, columnDefinition = "varchar(255)")
    private String name;

    public Country(){
        super();
    }


    public Country(String name) {
        super();
        this.name = name;
    }

    public Long getId(){
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

}

