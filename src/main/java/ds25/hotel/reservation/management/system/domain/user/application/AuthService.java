package ds25.hotel.reservation.management.system.domain.user.application;

import ds25.hotel.reservation.management.system.config.AppConfig;
import ds25.hotel.reservation.management.system.di.Service;
import ds25.hotel.reservation.management.system.domain.user.User;
import ds25.hotel.reservation.management.system.domain.user.UserRepository;
import ds25.hotel.reservation.management.system.ui.auth.data.LoginForm;
import ds25.hotel.reservation.management.system.ui.auth.data.RegisterForm;
import ds25.hotel.reservation.management.system.ui.dto.user.UserDto;
import ds25.hotel.reservation.management.system.util.PasswordEncryptor;
import jakarta.inject.Inject;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class AuthService {
	final private UserRepository userRepository;
	final private PasswordEncryptor passwordEncryptor;
	final private Validator validator;

	@Inject
	public AuthService(UserRepository userRepository, PasswordEncryptor passwordEncryptor, Validator validator) {
		this.userRepository = userRepository;
		this.passwordEncryptor = passwordEncryptor;
		this.validator = validator;
	}

	/**
	 * 로그인 메소드
	 *
	 * @param loginForm 로그인 폼
	 * @return 로그인 성공시 유저 정보, 실패시 null
	 * @author 김남주
	 */
	public UserDto login(LoginForm loginForm) throws Exception {
		log.info("User Log in method is called");

		Optional<User> user = userRepository.findByEmail(loginForm.email());
		if (user.isEmpty() || !passwordEncryptor.matches(loginForm.password(), user.get().getPassword())) {
			throw new Exception("Invalid email or password");
		}

		AppConfig.getInstance().setUser(UserDto.from(user.get()));

		return UserDto.from(user.get());
	}

	public boolean isExistEmail(String email) {
		log.info("isExistId method called");
		return userRepository.existsByEmail(email);
	}

	/**
	 * 회원가입 메소드
	 *
	 * @param registerForm 유저 정보
	 * @return 회원가입 성공시 유저 정보, 실패시 null
	 * @author 김남주
	 */
	public UserDto register(RegisterForm registerForm) throws RuntimeException {
		log.info("User Register method is called");

		Set<ConstraintViolation<RegisterForm>> violations = validator.validate(registerForm);
		if (!violations.isEmpty()) {
			String messages = violations.stream().map(ConstraintViolation::getMessage).collect(Collectors.joining());
			throw new RuntimeException(messages);
		}

		User newUser = userRepository.save(User.builder()
				.email(registerForm.email())
				.name(registerForm.name())
				.password(registerForm.password())
				.phone(registerForm.phone())
				.build());

		return UserDto.from(newUser);
	}

	/**
	 * 로그아웃 하는 메소드
	 *
	 * @author 김남주
	 */
	public void logout() {
		log.info("User logout method called");
		AppConfig.getInstance().setUser(null);
	}

}
