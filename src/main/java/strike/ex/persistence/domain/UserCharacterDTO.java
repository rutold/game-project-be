package strike.ex.persistence.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserCharacterDTO {
    private Long id;
    private Long userId;
    private Long characterId;
    private String name;
    private int walkSpeed;
    private int health;
    private List<AbilityDTO> abilities;
    private double damageMultiplier;
    private double cooldownReduction;
    private String imageUrl;
    private String atlasJsonUrl;
    private int cost;
}
