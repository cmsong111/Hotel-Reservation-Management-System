package ds25.hotel.reservation.management.system.service.user;

import com.google.gson.Gson;
import ds25.hotel.reservation.management.system.configuration.Singleton;
import ds25.hotel.reservation.management.system.dto.user.UserDto;
import ds25.hotel.reservation.management.system.entity.user.User;
import ds25.hotel.reservation.management.system.repository.user.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class UserService {


    UserRepository userRepository;
    ModelMapper modelMapper = new ModelMapper();
    Gson gson = new Gson();
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
    public UserDto login(String id, String password) throws Exception {
        log.info("User Login method is called");

        if (id == null || id.equals("")) {
            throw new Exception("Id is null");
        } else if (password == null || password.equals("")) {
            throw new Exception("Password is null");
        }

        Optional<User> user = userRepository.findByIdAndPassword(id, password);
        if (user.isEmpty()) {
            throw new Exception("Id or Password is wrong");
        }
        Singleton.getInstance().setUser(modelMapper.map(user.get(), UserDto.class));

        return modelMapper.map(user.get(), UserDto.class);
    }

    /**
     * 회원가입 메소드
     *
     * @param user 유저 정보
     * @return 회원가입 성공시 유저 정보, 실패시 null
     * @author 김남주
     */
    public UserDto register(UserDto user) throws Exception {
        log.info("User Register method is called");

        if (user.getName().isEmpty()) {
            throw new Exception("이름을 입력해주세요.");
        } else if (user.getId().isEmpty()) {
            throw new Exception("아이디를 입력해주세요.");
        } else if (user.getPassword().isEmpty()) {
            throw new Exception("비밀번호를 입력해주세요.");
        } else if (user.getPhone().isEmpty()) {
            throw new Exception("전화번호를 입력해주세요.");
        } else if (user.getEmail().isEmpty()) {
            throw new Exception("이메일을 입력해주세요.");
        } else if (userRepository.existsById(user.getId())) {
            throw new Exception("이미 존재하는 아이디입니다.");
        }

        User newUser = modelMapper.map(user, User.class);

        return modelMapper.map(userRepository.save(newUser), UserDto.class);
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
        Singleton.getInstance().setUser(savedUser);

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

    /**
     * 로그아웃 하는 메소드
     *
     * @author 김남주
     */
    public void logout() {
        log.info("User logout method called");
        instance.setUser(null);
    }

    public boolean isExistId(String id) {
        log.info("isExistId method called");
        return userRepository.existsById(id);
    }

    public void initUserData(List<User> users) {
        log.info("UserService init");

        userRepository.saveAll(users);

        for (User user : userRepository.findAll()) {
            log.info("User : {}", user);
        }
    }
}
