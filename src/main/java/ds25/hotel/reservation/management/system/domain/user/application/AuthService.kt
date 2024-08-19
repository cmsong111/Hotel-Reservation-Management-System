package ds25.hotel.reservation.management.system.domain.user.application

import ds25.hotel.reservation.management.system.global.config.AuthContext
import ds25.hotel.reservation.management.system.domain.user.dao.UserRepository
import ds25.hotel.reservation.management.system.domain.user.domain.User
import ds25.hotel.reservation.management.system.domain.user.dto.LoginRequest
import ds25.hotel.reservation.management.system.domain.user.dto.SignUpRequest
import ds25.hotel.reservation.management.system.domain.user.exception.AuthException
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class AuthService(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder
) {
    /**
     * 로그인
     * @param loginRequest 로그인 요청
     * @return 로그인한 사용자
     */
    @Transactional(readOnly = true)
    fun login(loginRequest: LoginRequest): User {
        // 사용자 조회
        val user: User = userRepository.findByEmail(loginRequest.email)
            ?: throw AuthException("User not found")

        // 비밀번호 확인
        if (!passwordEncoder.matches(loginRequest.password, user.password)) {
            throw AuthException("Password is incorrect")
        }

        AuthContext.setUser(user)

        return user
    }

    /**
     * 로그아웃
     */
    fun logout() {
        AuthContext.clear()
    }

    /**
     * 회원가입
     * @param user 사용자
     * @return 가입한 사용자
     */
    @Transactional
    fun signUp(signUpRequest: SignUpRequest): User {
        // 이메일 중복 확인
        if (userRepository.existsByEmail(signUpRequest.email)) {
            throw AuthException("Email is already in use")
        }
        // 사용자 생성
        val user = User(signUpRequest, passwordEncoder)
        println("user: $user")

        return userRepository.save(user)
    }

    /**
     * 아이디 사용가능 여부 확인
     * @param email 이메일
     * @return 사용가능 여부 (true: 사용가능, false: 사용불가)
     */
    @Transactional(readOnly = true)
    fun isAvailableEmail(email: String): Boolean {
        return !userRepository.existsByEmail(email)
    }
}
