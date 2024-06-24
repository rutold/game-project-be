package strike.ex.persistence.domain;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SaveUserScoreRequest {

    @NotBlank
    private String game;
    @NotBlank
    private long userid;
    @NotBlank
    private int score;

}
