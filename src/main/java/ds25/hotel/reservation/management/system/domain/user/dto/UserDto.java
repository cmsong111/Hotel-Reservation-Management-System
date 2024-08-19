package ds25.hotel.reservation.management.system.domain.user.dto;

import ds25.hotel.reservation.management.system.domain.user.domain.UserGrade;
import ds25.hotel.reservation.management.system.domain.user.domain.UserRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    String id;
    String password;
    String name;
    String phone;
    String email;
    UserRole role;
    UserGrade grade;

    public static class Builder {
        private String id;
        private String password;
        private String name;
        private String phone;
        private String email;
        private UserRole role;
        private UserGrade grade;

        public static builder builder() {
            return new builder();
        }

        public static class builder {
            private String id;
            private String password;
            private String name;
            private String phone;
            private String email;
            private UserRole role;
            private UserGrade grade;

            public builder id(String id) {
                this.id = id;
                return this;
            }

            public builder password(String password) {
                this.password = password;
                return this;
            }

            public builder name(String name) {
                this.name = name;
                return this;
            }

            public builder phone(String phone) {
                this.phone = phone;
                return this;
            }

            public builder email(String email) {
                this.email = email;
                return this;
            }

            public builder role(UserRole role) {
                this.role = role;
                return this;
            }

            public builder grade(UserGrade grade) {
                this.grade = grade;
                return this;
            }

            public UserDto build() {
                // perform any desired validation checks here
                return new UserDto(id, password, name, phone, email, role, grade);
            }
        }
    }
}
