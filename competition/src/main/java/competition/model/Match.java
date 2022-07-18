package competition.model;


import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "match")
public class Match extends AuditModel implements Serializable {

    private static final Long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "matchSeq", sequenceName = "match_sequence", initialValue = 1, allocationSize = 1)
    @GeneratedValue(generator = "matchSeq", strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    Long id;

    @ManyToOne(targetEntity = Country.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "country_id", nullable = false)
    Country country;

    @ManyToOne(targetEntity = Tournament.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "tournament_id", nullable = false)
    Tournament tournament;

    @Column(name = "datetime", nullable = false, columnDefinition = "timestamp")
    LocalDateTime datetime;

    @ManyToOne(targetEntity = Team.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "home_team_id")
    Team homeTeam;

    @ManyToOne(targetEntity = Team.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "visitor_team_id")
    Team visitorTeam;

    @Column(name = "home_team_score", nullable = false, columnDefinition = "bigint")
    Long homeTeamScore;

    @Column(name = "visitor_team_score", nullable = false, columnDefinition = "bigint")
    Long visitorTeamScore;

    public Match() {
    }

    public Match(Country country, Tournament tournament, LocalDateTime datetime, Team homeTeam, Team visitorTeam, Long homeTeamScore, Long visitorTeamScore) {
        this.country = country;
        this.tournament = tournament;
        this.datetime = datetime;
        this.homeTeam = homeTeam;
        this.visitorTeam = visitorTeam;
        this.homeTeamScore = homeTeamScore;
        this.visitorTeamScore = visitorTeamScore;
    }

    public Long getId() {
        return id;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public Tournament getTournament() {
        return tournament;
    }

    public void setTournament(Tournament tournament) {
        this.tournament = tournament;
    }

    public LocalDateTime getDatetime() {
        return datetime;
    }

    public void setDatetime(LocalDateTime datetime) {
        this.datetime = datetime;
    }

    public Team getHomeTeam() {
        return homeTeam;
    }

    public void setHomeTeam(Team homeTeam) {
        this.homeTeam = homeTeam;
    }

    public Team getVisitorTeam() {
        return visitorTeam;
    }

    public void setVisitorTeam(Team visitorTeam) {
        this.visitorTeam = visitorTeam;
    }

    public Long getHomeTeamScore() {
        return homeTeamScore;
    }

    public void setHomeTeamScore(Long homeTeamScore) {
        this.homeTeamScore = homeTeamScore;
    }

    public Long getVisitorTeamScore() {
        return visitorTeamScore;
    }

    public void setVisitorTeamScore(Long visitorTeamScore) {
        this.visitorTeamScore = visitorTeamScore;
    }
}
