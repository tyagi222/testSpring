package tyagi.spring.testSpring.auth.user.hakAkses;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QHakAkses is a Querydsl query type for HakAkses
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QHakAkses extends EntityPathBase<HakAkses> {

    private static final long serialVersionUID = -701870893L;

    public static final QHakAkses hakAkses = new QHakAkses("hakAkses");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath kode = createString("kode");

    public final StringPath nama = createString("nama");

    public final SetPath<tyagi.spring.testSpring.auth.user.userAkses.UserAkses, tyagi.spring.testSpring.auth.user.userAkses.QUserAkses> userAkses = this.<tyagi.spring.testSpring.auth.user.userAkses.UserAkses, tyagi.spring.testSpring.auth.user.userAkses.QUserAkses>createSet("userAkses", tyagi.spring.testSpring.auth.user.userAkses.UserAkses.class, tyagi.spring.testSpring.auth.user.userAkses.QUserAkses.class, PathInits.DIRECT2);

    public QHakAkses(String variable) {
        super(HakAkses.class, forVariable(variable));
    }

    public QHakAkses(Path<? extends HakAkses> path) {
        super(path.getType(), path.getMetadata());
    }

    public QHakAkses(PathMetadata metadata) {
        super(HakAkses.class, metadata);
    }

}

