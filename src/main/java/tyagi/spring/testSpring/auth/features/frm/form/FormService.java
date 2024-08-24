package tyagi.spring.testSpring.auth.features.frm.form;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import tyagi.spring.testSpring.auth.features.frm.form.modelDto.FormRequest;
import tyagi.spring.testSpring.auth.features.frm.form.modelDto.FormResponse;
import tyagi.spring.testSpring.auth.user.User;
import tyagi.spring.testSpring.exceptions.DuplicateResourceException;
import tyagi.spring.testSpring.helper.ResponseDto;

@Service
@Transactional
@RequiredArgsConstructor
public class FormService {

    private final String FEATURE_NAME = "Form";

    private final FormRepo mainRepo;

    private final ModelMapper modelMapper;

    public ResponseEntity<ResponseDto<FormResponse>> tambahData(FormRequest request) {
        // get user login
        User userLogin = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        // logic
        Optional<Form> cekSlug = mainRepo.findBySlugAndDeletedByNull(request.getSlug());
        if (cekSlug.isPresent()) {
            throw new DuplicateResourceException(String.format("The slug has already been taken.", FEATURE_NAME, request.getSlug()),
                    String.format("Invalid field", FEATURE_NAME));
        }

        Form form = modelMapper.map(request, Form.class);
        form.setCreatedBy(userLogin.getId());
        form = mainRepo.save(form);

        // setup response
        FormResponse gedungResponse = modelMapper.map(form, FormResponse.class);
        ResponseDto<FormResponse> response = new ResponseDto<>();
        response.setStatus(true);
        response.setCode(HttpStatus.CREATED.value());
        response.setMessage(String.format("Create form success", FEATURE_NAME));
        response.setPayload(gedungResponse);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
