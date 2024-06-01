package ds25.hotel.reservation.management.system.ui.dto.user;

import ds25.hotel.reservation.management.system.domain.user.User;
import ds25.hotel.reservation.management.system.domain.user.UserGrade;
import ds25.hotel.reservation.management.system.domain.user.UserRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
	Long id;
	String password;
	String name;
	String phone;
	String email;
	UserRole role;
	UserGrade grade;

	public static UserDto from(User user) {
		return UserDto.builder()
				.id(user.getId())
				.email(user.getEmail())
				.name(user.getName())
				.phone(user.getPhone())
				.role(user.getRole())
				.grade(user.getGrade())
				.build();
	}
}
