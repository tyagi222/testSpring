package tyagi.spring.testSpring.auth.user.userAkses;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUserAkses is a Querydsl query type for UserAkses
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUserAkses extends EntityPathBase<UserAkses> {

    private static final long serialVersionUID = 1807773167L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QUserAkses userAkses = new QUserAkses("userAkses");

    public final tyagi.spring.testSpring.auth.user.hakAkses.QHakAkses hakAkses;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final tyagi.spring.testSpring.auth.user.QUser user;

    public QUserAkses(String variable) {
        this(UserAkses.class, forVariable(variable), INITS);
    }

    public QUserAkses(Path<? extends UserAkses> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QUserAkses(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QUserAkses(PathMetadata metadata, PathInits inits) {
        this(UserAkses.class, metadata, inits);
    }

    public QUserAkses(Class<? extends UserAkses> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.hakAkses = inits.isInitialized("hakAkses") ? new tyagi.spring.testSpring.auth.user.hakAkses.QHakAkses(forProperty("hakAkses")) : null;
        this.user = inits.isInitialized("user") ? new tyagi.spring.testSpring.auth.user.QUser(forProperty("user")) : null;
    }

}

