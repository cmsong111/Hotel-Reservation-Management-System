package ds25.hotel.reservation.management.system.ui.auth.data;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;

public record RegisterForm(
		@Email(message = "이메일 형식이 올바르지 않습니다.")
		String email,
		@Min(value = 8, message = "비밀번호는 최소 8자 이상이어야 합니다.")
		String password,
		@Min(value = 2, message = "이름은 최소 2자 이상이어야 합니다.")
		String name,
		@Pattern(regexp = "^\\d{3}-\\d{3,4}-\\d{4}$", message = "전화번호 형식이 올바르지 않습니다. (예: 010-1234-5678)")
		String phone
) {
}
