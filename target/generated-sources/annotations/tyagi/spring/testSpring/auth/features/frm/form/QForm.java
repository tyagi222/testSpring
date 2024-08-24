package tyagi.spring.testSpring.auth.features.frm.form;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QForm is a Querydsl query type for Form
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QForm extends EntityPathBase<Form> {

    private static final long serialVersionUID = 1686111128L;

    public static final QForm form = new QForm("form");

    public final DateTimePath<java.time.LocalDateTime> createdAt = createDateTime("createdAt", java.time.LocalDateTime.class);

    public final NumberPath<Long> createdBy = createNumber("createdBy", Long.class);

    public final DateTimePath<java.time.LocalDateTime> deletedAt = createDateTime("deletedAt", java.time.LocalDateTime.class);

    public final NumberPath<Long> deletedBy = createNumber("deletedBy", Long.class);

    public final StringPath description = createString("description");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final BooleanPath isActive = createBoolean("isActive");

    public final BooleanPath isDeletable = createBoolean("isDeletable");

    public final BooleanPath isEditable = createBoolean("isEditable");

    public final BooleanPath limit_one_response = createBoolean("limit_one_response");

    public final StringPath name = createString("name");

    public final StringPath slug = createString("slug");

    public final DateTimePath<java.time.LocalDateTime> updatedAt = createDateTime("updatedAt", java.time.LocalDateTime.class);

    public final NumberPath<Long> updatedBy = createNumber("updatedBy", Long.class);

    public QForm(String variable) {
        super(Form.class, forVariable(variable));
    }

    public QForm(Path<? extends Form> path) {
        super(path.getType(), path.getMetadata());
    }

    public QForm(PathMetadata metadata) {
        super(Form.class, metadata);
    }

}

