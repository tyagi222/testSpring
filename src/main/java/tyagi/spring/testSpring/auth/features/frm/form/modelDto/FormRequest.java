package tyagi.spring.testSpring.auth.features.frm.form.modelDto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class FormRequest {

    @NotNull(message = "The name field is required.")
    private String name;

    @NotNull(message = "The slug field is required.")
    private String slug;

    private String description;
    private Boolean limit_one_response;

}
