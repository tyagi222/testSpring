package tyagi.spring.testSpring.auth.user.hakAkses;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HakAksesRepo extends JpaRepository<HakAkses, Long> {

    List<HakAkses> findByKodeIn(List<String> listKode);
}
