package tyagi.spring.testSpring.auth.user.hakAkses;

import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tyagi.spring.testSpring.auth.user.userAkses.UserAkses;

// @Data // untuk set setter dan getter secara otomatis
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(schema = "usrm")
public class HakAkses {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String kode;
    private String nama;

    @OneToMany(mappedBy = "hakAkses", fetch = FetchType.EAGER)
    private Set<UserAkses> userAkses;
}
