package tyagi.spring.testSpring.exceptions;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.NoHandlerFoundException;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import tyagi.spring.testSpring.helper.ResponseDto;

@RestControllerAdvice
public class ExceptionResolver {
    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseDto<String> handleNoHandlerFound(NoHandlerFoundException e, WebRequest request) {
        String url = ((ServletWebRequest) request).getRequest().getRequestURI().toString();
        ResponseDto<String> response = new ResponseDto<>();
        response.setStatus(false);
        response.setCode(HttpStatus.NOT_FOUND.value());
        response.getErrorMessage().add("page not found - URL:" + url);
        response.setMessage("Halaman Tidak ditemukan");
        return response;
    }

    @ExceptionHandler(MaxUploadSizeExceededException.class)
    @ResponseStatus(HttpStatus.BAD_GATEWAY)
    public ResponseDto<String> handleMaxSizeException(MaxUploadSizeExceededException exc) {
        ResponseDto<String> response = new ResponseDto<>();
        response.setStatus(false);
        response.setCode(HttpStatus.BAD_GATEWAY.value());
        response.getErrorMessage().add("File to large !");
        response.setMessage("Failed Upload file");
        return response;
    }

    @ExceptionHandler(NoSuchElementException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseDto<Boolean> noSuchElementException(NoSuchElementException exc) {
        ResponseDto<Boolean> response = new ResponseDto<>();
        response.setStatus(false);
        response.setCode(HttpStatus.NOT_FOUND.value());
        response.getErrorMessage().add(exc.getMessage());
        response.setMessage("Data tidak ditemukan");
        return response;
    }

    @ExceptionHandler(NullPointerException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseDto<Boolean> nullPointerException(NullPointerException exc) {
        ResponseDto<Boolean> response = new ResponseDto<>();
        response.setStatus(false);
        response.setCode(HttpStatus.NOT_FOUND.value());
        response.getErrorMessage().add("Data tidak ditemukan di basis data");
        response.getErrorMessage().add(exc.getLocalizedMessage());
        response.setMessage("Gagal mengambil data");
        return response;
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseDto<Boolean> illegalArgumentException(IllegalArgumentException iae) {
        ResponseDto<Boolean> response = new ResponseDto<>();
        response.setStatus(false);
        response.setCode(HttpStatus.NOT_FOUND.value());
        response.getErrorMessage().add(iae.getLocalizedMessage());
        response.setMessage("Gagal mengambil data");
        return response;
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseDto<Boolean> notFoundException(NotFoundException nfe) {
        ResponseDto<Boolean> response = new ResponseDto<>();
        response.setStatus(false);
        response.setCode(HttpStatus.NOT_FOUND.value());
        response.getErrorMessage().add(nfe.getLocalizedMessage());
        response.setMessage("Data Tidak Ditemukan.");
        return response;
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseDto<Boolean> resouceNotFoundException(ResourceNotFoundException nfe) {
        ResponseDto<Boolean> response = new ResponseDto<>();
        response.setStatus(false);
        response.setCode(HttpStatus.NOT_FOUND.value());
        response.setPayload(false);
        response.getErrorMessage().add(nfe.getLocalizedMessage());
        response.setMessage(nfe.getCustomMessage());
        return response;
    }

    @ExceptionHandler(InternalAuthenticationServiceException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseDto<Boolean> internalAuthenticationServiceException(InternalAuthenticationServiceException iase) {
        ResponseDto<Boolean> response = new ResponseDto<>();
        response.setStatus(false);
        response.setCode(HttpStatus.BAD_REQUEST.value());
        response.getErrorMessage().add(iase.getLocalizedMessage());
        response.setMessage("Login Gagal.");
        return response;
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseDto<Boolean> constraintViolationException(ConstraintViolationException cv) {
        ResponseDto<Boolean> response = new ResponseDto<>();
        response.setStatus(false);
        response.setCode(HttpStatus.BAD_REQUEST.value());
        for (ConstraintViolation<?> iterable_element : cv.getConstraintViolations()) {
            response.getErrorMessage().add(iterable_element.getMessage());
        }
        response.setMessage("Gagal menyimpan data");
        response.setPayload(false);
        return response;
    }

    @ExceptionHandler(DuplicateResourceException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseDto<Boolean> duplicateResourceException(DuplicateResourceException dre) {
        ResponseDto<Boolean> response = new ResponseDto<>();
        response.setStatus(false);
        response.setCode(HttpStatus.BAD_REQUEST.value());
        response.getErrorMessage().add(dre.getLocalizedMessage());
        response.setMessage(dre.getCustomMessage());
        response.setPayload(false);
        return response;
    }

    @ExceptionHandler(CustomIllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseDto<Boolean> customIllegalArgumentException(CustomIllegalArgumentException ciae) {
        ResponseDto<Boolean> response = new ResponseDto<>();
        response.setStatus(false);
        response.setCode(HttpStatus.BAD_REQUEST.value());
        if (ciae.getErrorMessage().isEmpty()) {
            response.getErrorMessage().add(ciae.getLocalizedMessage());
        } else {
            response.setErrorMessage(ciae.getErrorMessage());
        }
        response.setMessage(ciae.getCustomMessage());
        response.setPayload(false);
        return response;
    }

    @ExceptionHandler(MappingException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseDto<Boolean> mappingException(MappingException me) {
        ResponseDto<Boolean> response = new ResponseDto<>();
        response.setStatus(false);
        response.setCode(HttpStatus.BAD_REQUEST.value());
        response.getErrorMessage().add(me.getLocalizedMessage());
        response.setMessage(me.getCustomMessage());
        response.setPayload(false);
        return response;
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseDto<Boolean> dataIntegrityViolationException(DataIntegrityViolationException dre) {
        String msg = dre.getMessage();
        if (dre.getCause().getCause() instanceof SQLException) {
            SQLException e = (SQLException) dre.getCause().getCause();
            msg = e.getLocalizedMessage();
        }
        ResponseDto<Boolean> response = new ResponseDto<>();
        response.setStatus(false);
        response.setCode(HttpStatus.BAD_REQUEST.value());
        response.getErrorMessage().add(msg);
        response.setMessage(dre.getMessage());
        response.setPayload(false);
        return response;
    }

    @ExceptionHandler(CustomUnauthorizedException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseDto<Boolean> customIllegalArgumentException(CustomUnauthorizedException cue) {
        ResponseDto<Boolean> response = new ResponseDto<>();
        response.setStatus(false);
        response.setCode(HttpStatus.UNAUTHORIZED.value());
        response.getErrorMessage().add(cue.getLocalizedMessage());
        response.setMessage(cue.getCustomMessage());
        response.setPayload(false);
        return response;
    }

    @ExceptionHandler(CustomInternalServerErrorException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseDto<Boolean> customInternalServerErrorException(CustomInternalServerErrorException cisee) {
        ResponseDto<Boolean> response = new ResponseDto<>();
        response.setStatus(false);
        response.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        response.getErrorMessage().add(cisee.getLocalizedMessage());
        response.setMessage(cisee.getCustomMessage());
        response.setPayload(false);
        return response;
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseDto<Boolean> httpMessageNotReadableException(HttpMessageNotReadableException hmnre) {
        ResponseDto<Boolean> response = new ResponseDto<>();
        response.setStatus(false);
        response.setCode(HttpStatus.BAD_REQUEST.value());
        response.getErrorMessage().add(hmnre.getLocalizedMessage());
        response.setMessage(hmnre.getMessage());
        response.setPayload(false);
        return response;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public ResponseDto<Boolean> handleValidationExceptions(MethodArgumentNotValidException ex) {
        ResponseDto<Boolean> response = new ResponseDto<>();
        response.setStatus(false);
        response.setCode(HttpStatus.UNPROCESSABLE_ENTITY.value());

        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
            errors.put(error.getField(), error.getDefaultMessage()));
        
        List<String> error = new ArrayList<>(errors.values());
        response.getErrorMessage().addAll(error);

        response.setMessage(ex.getMessage());
        response.setPayload(false);
        return response;
    }
}
