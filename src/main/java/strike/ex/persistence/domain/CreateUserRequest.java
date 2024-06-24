package strike.ex.persistence.domain;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserRequest {

    @NotBlank
    private String username;
    @NotBlank
    @Size(min=5, max=15, message="{register.password.size}")
    private String password;
    @NotBlank
    private String email;

}
