package strike.ex.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "enemies")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EnemyEntity {
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
    @JoinColumn(name = "exp_id")
    private ExpEntity exp;


}
