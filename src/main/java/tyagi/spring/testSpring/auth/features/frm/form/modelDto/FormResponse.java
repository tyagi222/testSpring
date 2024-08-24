package tyagi.spring.testSpring.auth.features.frm.form.modelDto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FormResponse {

    private Long id;
    private String name;
    private String slug;
    private String description;
    private Boolean limit_one_response;
    private Long creator_id;
    
    private Boolean isActive;
    private Boolean isEditable;
    private Boolean isDeletable;

    private String createdAt;
    private String updatedAt;

    public void setCreatedAt(LocalDateTime createdAt) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        this.createdAt = createdAt.format(formatter);
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        this.updatedAt = updatedAt.format(formatter);
    }
    
    public void setCreatedBy(Long createdBy) {
        this.creator_id = createdBy;
    }

}
