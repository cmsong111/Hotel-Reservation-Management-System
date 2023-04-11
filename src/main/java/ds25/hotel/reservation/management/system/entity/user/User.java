package ds25.hotel.reservation.management.system.entity.user;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

/**
 * 유저 정보를 담는 클래스
 *
 * @author 김남주
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "users")
public class User {
    @Id
    @Setter(AccessLevel.NONE)
    String id;
    String password;
    String name;
    String phone;
    String email;
    UserRole role;
    UserGrade grade;
}
