package competition.oas;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

public class AddMatchOAS {

    @Schema(name = "AddMatchOAS.Request")
    public class Request{
        @Schema(example = "1", description = "Country for match")
        public Long countryId;

        @Schema(example = "2")
        public Long tournamentId;

        @Schema(example = "2")
        public Long homeTeamId;

        @Schema(example = "1")
        public Long visitorTeamId;

        @Schema(example = "4")
        public Long homeTeamScore;

        @Schema(example = "7")
        public Long visitorTeamScore;
    }

    @Schema(name = "AddMatchOAS.Response")
    public class Response{
        public String message;
        public Object payload;
        public Long status;
    }
}
