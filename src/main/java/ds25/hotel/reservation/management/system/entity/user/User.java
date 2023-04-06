package ds25.hotel.reservation.management.system.entity.user;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    String id;
    String password;
    String name;
    String phone;
    String email;
    UserRole role;
    UserGrade grade;
}
