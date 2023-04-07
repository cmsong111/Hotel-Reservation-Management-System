package ds25.hotel.reservation.management.system.dto.user;

import ds25.hotel.reservation.management.system.entity.user.UserGrade;
import ds25.hotel.reservation.management.system.entity.user.UserRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    String id;
    String password;
    String name;
    String phone;
    String email;
    UserRole role;
    UserGrade grade;
}
