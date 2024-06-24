package strike.ex.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "upgrades")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpgradesEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "upgrade_multiplier")
    private double upgradeMultiplier;

    @Column(name = "number_of_tiers")
    private int numberOfTiers;

    @Column(name = "base_cost")
    private int baseCost;

    @Column(name = "increase_per_tier")
    private double increasePerTier;

    @Column(name = "image_url")
    private String imageUrl;
}
