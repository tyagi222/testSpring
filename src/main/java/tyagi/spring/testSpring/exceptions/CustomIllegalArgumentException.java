package tyagi.spring.testSpring.exceptions;

import java.util.ArrayList;
import java.util.List;

public class CustomIllegalArgumentException extends RuntimeException {

    private String customMessage;
    private List<String> errorMessage = new ArrayList<>();

    public CustomIllegalArgumentException(String arg0, String customMessage) {
        super(arg0);
        this.customMessage = customMessage;
    }

    public CustomIllegalArgumentException(List<String> arg0, String customMessage) {
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
