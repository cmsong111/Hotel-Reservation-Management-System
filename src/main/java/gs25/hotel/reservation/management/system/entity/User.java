package gs25.hotel.reservation.management.system.entity;

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
public class User {
    int idx;
    String id;
    String password;
    String name;
    String phone;
    String email;
    String role;
    String grade;
}
