package strike.ex.persistence.domain;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
public class TopScoresRequest {
    private long id;
    private String name;
    private String difficulty;
    private long score;
    private String player;
    public TopScoresRequest(long id, String name, String difficulty, long score, String player) {
        this.id = id;
        this.name = name;
        this.difficulty = difficulty;
        this.score = score;
        this.player = player;
    }
}