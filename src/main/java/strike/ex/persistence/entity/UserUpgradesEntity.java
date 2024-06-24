package strike.ex.persistence.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "user_upgrades")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserUpgradesEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @JsonBackReference
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "upgrade_id", nullable = false)
    @JsonBackReference
    private UpgradesEntity upgrade;

    @Column(name = "tier", nullable = false)
    private int tier;

    @JsonProperty("userId")
    public Long getUserId() {
        return user.getId();
    }

    @JsonProperty("upgradeId")
    public Long getUpgradeId() {
        return upgrade.getId();
    }
}
