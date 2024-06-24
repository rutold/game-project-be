package strike.ex.persistence.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "abilities")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AbilityEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "atlas_json_url")
    private String atlasJsonUrl;

    @ManyToOne
    @JoinColumn(name = "character_id")
    @JsonBackReference
    private CharacterEntity character;

    @Column(name = "damage")
    private int damage;

    @Column(name = "walk_speed")
    private int walkSpeed;

    @Column(name = "cooldown")
    private double cooldown;

    @Column(name = "projectile_count")
    private int projectilecount;

    @Column(name = "multi_hit")
    private boolean multiHit;

    @Column(name = "multi_hit_count")
    private int multiHitCount;


}
