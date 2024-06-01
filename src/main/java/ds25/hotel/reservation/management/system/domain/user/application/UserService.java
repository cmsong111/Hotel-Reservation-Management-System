package ds25.hotel.reservation.management.system.domain.user.application;

import ds25.hotel.reservation.management.system.config.AppConfig;
import ds25.hotel.reservation.management.system.di.Service;
import ds25.hotel.reservation.management.system.domain.user.User;
import ds25.hotel.reservation.management.system.domain.user.UserRepository;
import ds25.hotel.reservation.management.system.ui.dto.user.UserDto;
import jakarta.inject.Inject;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserService {
	final private UserRepository userRepository;

	@Inject
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	/**
	 * 유저 정보 수정 메소드
	 *
	 * @param newUser 수정할 유저 정보
	 * @return 수정된 유저 정보
	 * @throws IOException 파일 저장 실패
	 * @author 김남주
	 */
	public UserDto updateUser(UserDto newUser) throws Exception {
		log.info("updateUser method called");
		Optional<User> oldUser = userRepository.findById(newUser.getId());
		UserDto savedUser;

		if (oldUser.isEmpty()) {
			throw new Exception("존재하지 않는 유저입니다.");
		}

		if (!newUser.getName().isEmpty()) {
			oldUser.get().setName(newUser.getName());
			log.info("name changed");
		}
		if (!newUser.getPhone().isEmpty()) {
			oldUser.get().setPhone(newUser.getPhone());
			log.info("phone changed");
		}
		if (!newUser.getEmail().isEmpty()) {
			oldUser.get().setEmail(newUser.getEmail());
			log.info("email changed");
		}
		if (!newUser.getPassword().isEmpty()){
			oldUser.get().setPassword(newUser.getPassword());
			log.info("password changed");
		}
		savedUser = modelMapper.map(userRepository.save(oldUser.get()), UserDto.class);
		AppConfig.getInstance().setUser(savedUser);

		log.info("user info changed");
		return savedUser;

	}

	public UserDto changePassword(UserDto user, String oldPassword, String newPassword, String newPassword2) throws Exception {
		log.info("changePassword method called");
		Optional<User> oldUser = userRepository.findById(user.getId());

		if (oldUser.isEmpty()) {
			throw new Exception("존재하지 않는 유저입니다.");
		}
		if (!oldUser.get().getPassword().equals(oldPassword)) {
			throw new Exception("현재 비밀번호가 일치하지 않습니다.");
		}
		if (!newPassword.equals(newPassword2)) {
			throw new Exception("비밀번호가 일치하지 않습니다.");
		}

		oldUser.get().setPassword(newPassword);

		return modelMapper.map(userRepository.save(oldUser.get()), UserDto.class);
	}

	/**
	 * 모든 유저 정보를 불러오는 메소드
	 *
	 * @return 유저 정보 리스트
	 * @author 김남주
	 */
	public List<User> findAll() {
		log.info("User findAll method called");
		return userRepository.findAll();
	}

	/**
	 * 유저 정보를 삭제하는 메소드
	 *
	 * @param user 삭제할 유저 정보
	 * @throws IOException 파일 저장 실패
	 * @author 김남주
	 */
	public void deleteUser(User user) throws Exception {
		userRepository.delete(user);
	}





	public void initUserData(List<User> users) {
		log.info("UserService init");

		userRepository.saveAll(users);

		for (User user : userRepository.findAll()) {
			log.info("User : {}", user);
		}
	}
}
