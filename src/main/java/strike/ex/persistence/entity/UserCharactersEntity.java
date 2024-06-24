package strike.ex.persistence.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "user_characters")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserCharactersEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @JsonBackReference
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "character_id", nullable = false)
    @JsonBackReference
    private CharacterEntity character;
    @JsonProperty("userId")
    public Long getUserId() {
        return user.getId();
    }

    @JsonProperty("characterId")
    public Long getUpgradeId() {
        return character.getId();
    }
}
