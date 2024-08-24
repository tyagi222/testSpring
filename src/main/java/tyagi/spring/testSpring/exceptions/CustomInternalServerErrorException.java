package tyagi.spring.testSpring.exceptions;

import java.util.ArrayList;
import java.util.List;

public class CustomInternalServerErrorException extends RuntimeException {

    private String customMessage;
    private List<String> errorMessage = new ArrayList<>();

    public CustomInternalServerErrorException(String arg0, String customMessage) {
        super(arg0);
        this.customMessage = customMessage;
    }

    public CustomInternalServerErrorException(List<String> arg0, String customMessage) {
        super();
        this.customMessage = customMessage;
        this.errorMessage = arg0;
    }

    public String getCustomMessage() {
        return customMessage;
    }

    public void setCustomMessage(String customMessage) {
        this.customMessage = customMessage;
    }

    public List<String> getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(List<String> errorMessage) {
        this.errorMessage = errorMessage;
    }
}
