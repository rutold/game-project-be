package strike.ex.persistence.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AbilityDTO {
    private Long id;
    private String name;
    private String imageUrl;
    private String atlasJsonUrl;
    private int damage;
    private int walkSpeed;
    private double cooldown;
    private int projectileCount;
    private boolean multiHit;
    private int multiHitCount;
}
