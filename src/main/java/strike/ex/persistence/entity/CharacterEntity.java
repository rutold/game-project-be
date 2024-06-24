package strike.ex.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "characters")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CharacterEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "walk_speed")
    private int walkSpeed;

    @Column(name = "health")
    private int health;

    @OneToMany
    @JoinTable(
            name = "character_abilities",
            joinColumns = @JoinColumn(name = "character_id"),
            inverseJoinColumns = @JoinColumn(name = "ability_id")
    )
    private List<AbilityEntity> abilities;

    @Column(name = "damage_multiplier")
    private double damageMultiplier;

    @Column(name = "cooldown_reduction")
    private double cooldownReduction;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "atlas_json_url")
    private String atlasJsonUrl;

    @Column(name = "cost")
    private int cost;


}
