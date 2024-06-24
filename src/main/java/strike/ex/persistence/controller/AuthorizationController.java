package strike.ex.persistence.controller;

import strike.ex.persistence.business.impl.user.CreateUserUseCaseImpl;
import strike.ex.persistence.domain.LoginRequest;
import strike.ex.persistence.domain.LoginResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tokens")
@RequiredArgsConstructor
public class AuthorizationController {

    private final CreateUserUseCaseImpl userService;

    @CrossOrigin(origins = "http://localhost:5173")
    @PostMapping
    public ResponseEntity<String> login(@RequestBody @Valid LoginRequest loginRequest) {
        LoginResponse loginResponse = userService.login(loginRequest);

        HttpHeaders headers = new HttpHeaders();
        headers.add("JWT-Token", loginResponse.getAccessToken());
        headers.add("Access-Control-Expose-Headers", "JWT-Token");

        return ResponseEntity.status(HttpStatus.CREATED)
                .headers(headers)
                .body("successful");
    }
}