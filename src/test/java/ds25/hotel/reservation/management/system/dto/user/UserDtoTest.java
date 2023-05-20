package ds25.hotel.reservation.management.system.dto.user;

import ds25.hotel.reservation.management.system.entity.user.UserRole;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserDtoTest {
    @Test
    void builder() {

        String id = "testId";
        String password = "testPassword";
        String name = "testName";
        String phone = "testPhone";
        String email = "testEmail";

        UserDto userDto = UserDto.builder()
                .id(id)
                .password(password)
                .name(name)
                .phone(phone)
                .email(email)
                .build();

        assertEquals(id, userDto.getId());
        assertEquals(password, userDto.getPassword());
        assertEquals(name, userDto.getName());
        assertEquals(phone, userDto.getPhone());
        assertEquals(email, userDto.getEmail());
    }

    @Test
    void allArgsConstructorCompareWithBuilder() {
        String id = "testId";
        String password = "testPassword";
        String name = "testName";
        String phone = "testPhone";
        String email = "testEmail";

        UserDto userDto1 = new UserDto(id, password, name, phone, email, UserRole.USER, null);

        UserDto userDto2m = UserDto.builder()
                .name(name)
                .phone(phone)
                .email(email)
                .id(id)
                .password(password)
                .role(UserRole.USER)
                .build();

        assertEquals(userDto1.getId(), userDto2m.getId());
        assertEquals(userDto1.getPassword(), userDto2m.getPassword());
        assertEquals(userDto1.getName(), userDto2m.getName());
        assertEquals(userDto1.getPhone(), userDto2m.getPhone());
        assertEquals(userDto1.getEmail(), userDto2m.getEmail());
        assertEquals(userDto1.getRole(), userDto2m.getRole());
    }

    @Test
    void CompareChangeOrder() {
        String id = "testId";
        String password = "testPassword";
        String name = "testName";
        String phone = "testPhone";
        String email = "testEmail";

        UserDto userDto1 = UserDto.builder()
                .phone(phone)
                .name(name)
                .password(password)
                .email(email)
                .role(UserRole.USER)
                .id(id)
                .build();

        UserDto userDto2m = UserDto.builder()
                .name(name)
                .phone(phone)
                .email(email)
                .id(id)
                .password(password)
                .role(UserRole.USER)
                .build();

        assertEquals(userDto1.getId(), userDto2m.getId());
        assertEquals(userDto1.getPassword(), userDto2m.getPassword());
        assertEquals(userDto1.getName(), userDto2m.getName());
        assertEquals(userDto1.getPhone(), userDto2m.getPhone());
        assertEquals(userDto1.getEmail(), userDto2m.getEmail());
        assertEquals(userDto1.getRole(), userDto2m.getRole());
    }
}
