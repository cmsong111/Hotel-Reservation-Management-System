package ds25.hotel.reservation.management.system.domain.user.domain

import ds25.hotel.reservation.management.system.domain.user.dto.SignUpRequest
import jakarta.persistence.*
import org.hibernate.annotations.Fetch
import org.springframework.security.crypto.password.PasswordEncoder

@Entity(name = "users")
class User(
    @Column(unique = true)
    var email: String,
    var password: String,
    var name: String,
    var phone: String
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0
        private set

    @Enumerated
    @ElementCollection(fetch = FetchType.EAGER)
    var roles: MutableSet<UserRole> = mutableSetOf(UserRole.USER)

    /** 회원가입 시 사용할 생성자 */
    constructor(signUpRequest: SignUpRequest, passwordEncoder: PasswordEncoder) : this(
        email = signUpRequest.email,
        password = passwordEncoder.encode(signUpRequest.password),
        name = signUpRequest.name,
        phone = signUpRequest.phone
    )


    /**
     * 비밀번호 변경
     * @param password 변경할 비밀번호
     * @param passwordEncoder 비밀번호 인코더
     */
    fun changePassword(password: String, passwordEncoder: PasswordEncoder) {
        this.password = passwordEncoder.encode(password)
    }

    /**
     * 사용자 정보 변경
     * @param name 변경할 이름
     * @param phone 변경할 전화번호
     */
    fun update(name: String, phone: String) {
        this.name = name
        this.phone = phone
    }

    override fun toString(): String {
        return "User(id=$id, email='$email', name='$name', phone='$phone', roles=$roles, password='$password')"
    }


}
