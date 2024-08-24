package tyagi.spring.testSpring.auth.modelDto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationRequest {

    @Valid
    @Email(message = "Email should be valid")
    @NotNull(message = "Email not be null")
    private String email;
    
    @Size(min = 5, message = "Password should have at least 5 characters")
    @NotNull(message = "Password not be null")
    private String password;

}
