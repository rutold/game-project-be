package strike.ex.persistence.domain;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
public class TopGamesRequest {
    private long id;
    private String name;
    private String difficulty;
    private long count;
    public TopGamesRequest(long id, String name, String difficulty, long count) {
        this.id = id;
        this.name = name;
        this.difficulty = difficulty;
        this.count = count;
    }
}