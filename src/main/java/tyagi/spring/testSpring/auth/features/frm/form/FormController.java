package tyagi.spring.testSpring.auth.features.frm.form;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import tyagi.spring.testSpring.auth.features.frm.form.modelDto.FormRequest;
import tyagi.spring.testSpring.auth.features.frm.form.modelDto.FormResponse;
import tyagi.spring.testSpring.helper.ResponseDto;

@RestController
@RequestMapping("/api/v1/forms")
@RequiredArgsConstructor
@Validated
public class FormController {

    private final FormService mainService;
    private final String PERMISSION = "FORM";

    @PostMapping
    @PreAuthorize("hasAuthority('" + PERMISSION + "_C')") // untuk set hak akses
    public ResponseEntity<ResponseDto<FormResponse>> tambahData(
            @RequestBody @Valid FormRequest formRequest,
            @Parameter(hidden = true) final Errors errors,
            @Parameter(hidden = true) final UriComponentsBuilder uriComponentsBuilder) {
        if (errors.hasErrors()) {
            ResponseDto<FormResponse> responseDto = new ResponseDto<>();
            for (ObjectError error : errors.getAllErrors()) {
                responseDto.getErrorMessage().add(error.getDefaultMessage());
            }
            responseDto.setStatus(false);
            responseDto.setMessage("Gagal menyimpan data");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseDto);
        }
        return mainService.tambahData(formRequest);
    }
}
