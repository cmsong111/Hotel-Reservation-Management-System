package ds25.hotel.reservation.management.system.service.user;

import ds25.hotel.reservation.management.system.configuration.Singleton;
import ds25.hotel.reservation.management.system.entity.user.User;
import ds25.hotel.reservation.management.system.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class UserService {

    UserRepository userRepository;
    Singleton instance = Singleton.getInstance();

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }



    /**
     * 로그인 메소드
     *
     * @param id       유저 아이디
     * @param password 유저 비밀번호
     * @return 로그인 성공시 유저 정보, 실패시 null
     * @author 김남주
     */
    public Optional<User> login(String id, String password) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            if (user.get().getPassword().equals(password)) {
                instance.getUserProvider().updateUser(user.get());
                return user;
            }
        }
        return Optional.empty();
    }

    /**
     * 회원가입 메소드
     *
     * @param user 유저 정보
     * @return 회원가입 성공시 유저 정보, 실패시 null
     * @author 김남주
     */
    public User register(User user) throws Exception {

        if (user.getIdx() != 0) {
            throw new IllegalArgumentException("이미 존재하는 아이디입니다.");
        }

        return userRepository.save(user);
    }


    /**
     * 유저 정보 수정 메소드
     *
     * @param user 수정할 유저 정보
     * @return 수정된 유저 정보
     * @throws IOException 파일 저장 실패
     * @author 김남주
     */
    public User updateUser(User user) throws Exception {
        if (user.getIdx() == 0) {
            throw new IllegalArgumentException("유저 정보가 없습니다.");
        }
        User updatedUser = userRepository.save(user);

        if (updatedUser != null) {
            instance.userProvider.updateUser(updatedUser);
        }
        return updatedUser;
    }

    /**
     * 모든 유저 정보를 불러오는 메소드
     *
     * @return 유저 정보 리스트
     * @author 김남주
     */
    public List<User> findAll() {
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

    /**
     * 로그아웃 하는 메소드
     *
     * @author 김남주
     */
    public void logout() {
        instance.getUserProvider().updateUser(null);
    }

    public boolean isExistId(String id) {
        return userRepository.existsById(id);
    }
}
