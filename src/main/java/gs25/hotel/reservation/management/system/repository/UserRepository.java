package gs25.hotel.reservation.management.system.repository;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import gs25.hotel.reservation.management.system.entity.User;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Optional;

public class UserRepository {
    Gson gson = new Gson();
    ArrayList<User> userList = new ArrayList<>();
    int idx = 0;

    public UserRepository() {
        loadFromJson();
    }

    /**
     * Json으로 저장된 유저 정보를 불러오는 메소드
     *
     * @Author 김남주
     */
    public void loadFromJson() {
        Reader reader = new InputStreamReader(
                getClass().getClassLoader().getResourceAsStream("db/user.json"), StandardCharsets.UTF_8);
        userList = gson.fromJson(reader, new TypeToken<ArrayList<User>>() {
        }.getType());
        idx = userList.get(userList.size() - 1).getIdx();
    }

    /**
     * 유저 정보를 Json으로 저장하는 메소드
     *
     * @Author 김남주
     */
    public void saveToJson() throws IOException {
        FileWriter file = new FileWriter(getClass().getClassLoader().getResource("db/user.json").getPath());
        userList.sort(Comparator.comparing(User::getIdx));
        gson.toJson(userList, file);
        file.flush();
        file.close();
    }

    /**
     * 유저 정보를 추가하는 메소드
     *
     * @param user 추가할 유저 정보
     * @throws IOException 파일 저장 실패 시
     * @Author 김남주
     */
    public Optional<User> save(User user) throws IOException {
        if (user.getIdx() != 0) {
            for (int i = 0; i < userList.size(); i++) {
                if (userList.get(i).getIdx() == user.getIdx()) {
                    userList.set(i, user);
                    break;
                }
            }
        } else {
            user.setIdx(++idx);
            userList.add(user);
        }
        saveToJson();
        return Optional.of(user);
    }


    /**
     * 유저 정보를 삭제하는 메소드
     *
     * @param user 삭제할 유저 정보
     * @Author 김남주
     */
    public void delete(User user) throws IOException {
        for (int i = 0; i < userList.size(); i++) {
            if (userList.get(i).getIdx() == user.getIdx()) {
                userList.remove(i);
                break;
            }
        }
        saveToJson();
    }

    /**
     * idx를 통해 유저 정보를 불러오는 메소드
     *
     * @param idx 유저 고유 번호
     * @return Optional<User>
     * @Author 김남주
     */
    public Optional<User> findByIdx(int idx) {
        for (User user : userList) {
            if (user.getIdx() == idx) {
                return Optional.of(user);
            }
        }
        return Optional.empty();
    }

    /**
     * ID와 비밀번호를 통해 유저 정보를 불러오는 메소드
     *
     * @param id       - 아이디
     * @param password 비밀번호
     * @return Optional<User>
     * @Author 김남주
     */
    public Optional<User> findByIdAndPassword(String id, String password) {
        for (User user : userList) {
            if (user.getId().equals(id) && user.getPassword().equals(password)) {
                return Optional.of(user);
            }
        }
        return Optional.empty();
    }

    /**
     * ID를 통해 유저 정보를 불러오는 메소드
     *
     * @param id - 체크 할 아이디
     * @return 중복 시 true, 중복 아닐 시 false
     */
    public boolean isExistId(String id) {
        for (User user : userList) {
            if (user.getId().equals(id)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 유저 리스트를 불러오는 메소드
     * @return 유저 리스트
     * @Author 김남주
     */
    public ArrayList<User> findAll() {
        return userList;
    }
}
