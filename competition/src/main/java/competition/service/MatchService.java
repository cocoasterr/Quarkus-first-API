package competition.service;

import competition.model.Country;
import competition.model.Match;
import competition.model.Team;
import competition.model.Tournament;
import competition.utility.DateUtil;
import io.vertx.core.json.JsonObject;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;
import javax.ws.rs.core.Response;
import java.time.LocalDateTime;
import java.util.List;

@ApplicationScoped
public class MatchService {
    @Inject
    EntityManager em;

    @Transactional
    public Response add(JsonObject params){
        Match match = new Match();
        Country country = Country.findById(params.getLong("countryId", -99L));
        Tournament tournament = Tournament.findById(params.getLong("tournamentId", -99L));
        Team homeTeam = Team.findById(params.getLong("homeTeamId", -99L));
        Team visitorTeam = Team.findById(params.getLong("visitTeamId", -99L));

        if(country == null || tournament == null || homeTeam == null || visitorTeam == null){
            return Response.status(Response.Status.BAD_REQUEST).build();
        }

        match.setVisitorTeamScore(params.getLong("matchScore"));
        match.setDatetime(DateUtil.stringToLocalDateTime(params.getString("datetime"), "dd-MM-yyyy"));
        match.setHomeTeamScore(params.getLong("halfScore"));
        match.setCountry(country);
        match.setTournament(tournament);
        match.setHomeTeam(homeTeam);
        match.setVisitorTeam(visitorTeam);
        match.persist();

        return Response.ok().build();
    }

    public Response getAll(){
        List<Match> countries = Match.findAll().list();
        return Response.ok().entity(countries).build();
    }

    public Response getAllFilter(){
        StringBuilder sb = new StringBuilder();
        sb.append(" SELECT c.name ");
        sb.append(" FROM bootcamp.country c ");
        Query query = em.createNativeQuery(sb.toString());
        List<String> result = query.getResultList();
        return Response.ok().entity(result).build();
    }

    @Transactional
    public Response update(Long id, JsonObject params){
        Match match = Match.findById(id);
        if(match == null){
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        Country country = Country.findById(params.getLong("countryId", -99L));
        Tournament tournament = Tournament.findById(params.getLong("tournamentId", -99L));
        Team homeTeam = Team.findById(params.getLong("homeTeamId", -99L));
        Team visitorTeam = Team.findById(params.getLong("visitTeamId", -99L));

        if(country == null || tournament == null || homeTeam == null || visitorTeam == null){
            return Response.status(Response.Status.BAD_REQUEST).build();
        }

        match.setVisitorTeamScore(params.getLong("matchScore"));
        match.setDatetime(LocalDateTime.parse(params.getString("datetime")));
        match.setHomeTeamScore(params.getLong("halfScore"));
        match.setCountry(country);
        match.setTournament(tournament);
        match.setHomeTeam(homeTeam);
        match.setVisitorTeam(visitorTeam);
        match.persist();

        return Response.ok().entity(match).build();
    }

    @Transactional
    public Response delete(Long id){
        Match match = Match.findById(id);
        if(match == null){
            return Response.status(Response.Status.BAD_REQUEST).build();
        }

        match.delete();
        return Response.ok().build();
    }
}
