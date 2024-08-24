package tyagi.spring.testSpring.auth.features.frm.form;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Table(schema = "frm", indexes = {
        @Index(columnList = "deletedBy")
})
public class Form {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "name tidak boleh null")
    private String name;

    @NotNull(message = "slug tidak boleh null")
    private String slug;

    private String description;

    private Boolean limit_one_response;

    @CreatedDate
    private LocalDateTime createdAt;
    private Long createdBy;

    @LastModifiedDate
    private LocalDateTime updatedAt;
    private Long updatedBy;

    private LocalDateTime deletedAt;
    private Long deletedBy;

    private Boolean isActive = true;
    private Boolean isEditable = true;
    private Boolean isDeletable = true;
}
