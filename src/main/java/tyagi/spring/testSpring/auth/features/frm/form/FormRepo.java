package tyagi.spring.testSpring.auth.features.frm.form;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;

public interface FormRepo extends JpaRepository<Form, Long>, QuerydslPredicateExecutor<Form> {

        @Modifying
        @Query("UPDATE Gedung SET deletedAt = CURRENT_TIMESTAMP, deletedBy = :deletedBy WHERE id = :id")
        void deleteGedung(@Param("id") Long id, @Param("deletedBy") Long deletedBy);

        Optional<Form> findBySlugAndDeletedByNull(String kode);
}
