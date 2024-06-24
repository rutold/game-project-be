package strike.ex.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Exp")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ExpEntity {
    @Id
    private Integer id;

    @Column(name = "experience_value")
    private Integer experienceValue;
}
