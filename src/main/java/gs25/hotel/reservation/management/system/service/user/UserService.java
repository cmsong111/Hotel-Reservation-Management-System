package gs25.hotel.reservation.management.system.service.user;

import gs25.hotel.reservation.management.system.configuration.Singleton;
import gs25.hotel.reservation.management.system.entity.user.User;
import gs25.hotel.reservation.management.system.repository.user.UserRepository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

public class UserService {
    UserRepository userRepository = Singleton.getInstance().getUserRepository();
    Singleton instance = Singleton.getInstance();

    public void init() {
        userRepository.loadFromJson();
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
        Optional<User> user = userRepository.findByIdAndPassword(id, password);

        if (user.isPresent()) {
            instance.getUserProvider().updateUser(user.get());
        } else {
            instance.getUserProvider().updateUser(null);
        }
        return user;
    }

    /**
     * 회원가입 메소드
     *
     * @param user 유저 정보
     * @return 회원가입 성공시 유저 정보, 실패시 null
     * @author 김남주
     */
    public Optional<User> register(User user) throws IOException {
        boolean isExist = userRepository.isExistId(user.getId());
        if (isExist || user.getIdx() != 0) {
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
    public Optional<User> updateUser(User user) throws IOException {
        if (user.getIdx() == 0) {
            throw new IllegalArgumentException("유저 정보가 없습니다.");
        }
        Optional<User> updatedUser = userRepository.save(user);

        if (updatedUser.isPresent()) {
            instance.userProvider.updateUser(updatedUser.get());
        }
        return updatedUser;
    }

    /**
     * 모든 유저 정보를 불러오는 메소드
     *
     * @return 유저 정보 리스트
     * @author 김남주
     */
    public ArrayList<User> findAll() {
        return userRepository.findAll();
    }

    /**
     * 유저 정보를 삭제하는 메소드
     *
     * @param user 삭제할 유저 정보
     * @throws IOException 파일 저장 실패
     * @author 김남주
     */
    public void deleteUser(User user) throws IOException {
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
}
