package tyagi.spring.testSpring.auth.modelDto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponse {

    private String name;
    private String email;
    private String accessToken;
    private List<String> hakAkses;

}
