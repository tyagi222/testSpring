package tyagi.spring.testSpring.auth.user;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUser is a Querydsl query type for User
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUser extends EntityPathBase<User> {

    private static final long serialVersionUID = 1157078758L;

    public static final QUser user = new QUser("user");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final BooleanPath isEnabled = createBoolean("isEnabled");

    public final BooleanPath isExpired = createBoolean("isExpired");

    public final BooleanPath isLocked = createBoolean("isLocked");

    public final StringPath name = createString("name");

    public final StringPath password = createString("password");

    public final EnumPath<Role> role = createEnum("role", Role.class);

    public final DateTimePath<java.time.LocalDateTime> tokenExpiredDateTime = createDateTime("tokenExpiredDateTime", java.time.LocalDateTime.class);

    public final SetPath<tyagi.spring.testSpring.auth.user.userAkses.UserAkses, tyagi.spring.testSpring.auth.user.userAkses.QUserAkses> userAkses = this.<tyagi.spring.testSpring.auth.user.userAkses.UserAkses, tyagi.spring.testSpring.auth.user.userAkses.QUserAkses>createSet("userAkses", tyagi.spring.testSpring.auth.user.userAkses.UserAkses.class, tyagi.spring.testSpring.auth.user.userAkses.QUserAkses.class, PathInits.DIRECT2);

    public final StringPath username = createString("username");

    public QUser(String variable) {
        super(User.class, forVariable(variable));
    }

    public QUser(Path<? extends User> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUser(PathMetadata metadata) {
        super(User.class, metadata);
    }

}

