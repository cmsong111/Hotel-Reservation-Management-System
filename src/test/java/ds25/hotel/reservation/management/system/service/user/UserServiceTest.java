package ds25.hotel.reservation.management.system.service.user;

import ds25.hotel.reservation.management.system.entity.user.User;
import ds25.hotel.reservation.management.system.entity.user.UserRole;
import ds25.hotel.reservation.management.system.configuration.Singleton;
import ds25.hotel.reservation.management.system.entity.user.UserGrade;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


class UserServiceTest {

    static Singleton singleton = Singleton.getInstance();
    UserService userService = singleton.getUserService();

    @BeforeAll
    static void initialize() {
        singleton.init();
    }

    @Test
    void init() {
        ArrayList<User> users = userService.findAll();
        assertNotEquals(users.size(), 0);
    }

    @Test
    void login() {
        User user = User.builder()
                .idx(1)
                .id("admin")
                .password("admin")
                .name("admin")
                .phone("010-0000-0000")
                .email("admin@admin.com")
                .role(UserRole.ADMIN)
                .grade(UserGrade.GOLD)
                .build();
        User login = userService.login(user.getId(), user.getPassword()).orElse(null);
        assertEquals(user, login);
    }


    @Test
    void registerAndDeleteUser() throws IOException {
        User user = User.builder()
                .idx(0)
                .id("user123")
                .password("user123")
                .name("user123")
                .phone("010-1234-5678")
                .email("user@hotel.com")
                .role(UserRole.USER)
                .build();


        Optional<User> register = userService.register(user);
        assertNotEquals(0, register.get().getIdx());
        assertEquals(user.getId(), register.get().getId());
        assertEquals(user.getPassword(), register.get().getPassword());
        assertEquals(user.getName(), register.get().getName());
        assertEquals(user.getPhone(), register.get().getPhone());
        assertEquals(user.getEmail(), register.get().getEmail());
        assertEquals(user.getRole(), register.get().getRole());

        userService.deleteUser(register.get());
    }

    @Test
    void updateUser() throws IOException {
        Optional<User> user = userService.login("user", "user");
        user.get().setName("name");

        User udpated = userService.updateUser(user.get()).get();

        assertEquals(user.get().getIdx(),udpated.getIdx());
        assertEquals(user.get().getId(), udpated.getId());
        assertEquals(user.get().getPassword(), udpated.getPassword());
        assertEquals("name", udpated.getName());
        assertEquals(user.get().getPhone(), udpated.getPhone());
        assertEquals(user.get().getEmail(), udpated.getEmail());
        assertEquals(user.get().getRole(), udpated.getRole());
    }
}
