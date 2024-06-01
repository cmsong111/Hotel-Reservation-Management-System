package ds25.hotel.reservation.management.system.ui.auth.data;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;

public record LoginForm(
		@Email(message = "이메일 형식이 올바르지 않습니다.")
		String email,
		@Min(value = 8, message = "비밀번호는 최소 8자 이상이어야 합니다.")
		String password
) {
}
