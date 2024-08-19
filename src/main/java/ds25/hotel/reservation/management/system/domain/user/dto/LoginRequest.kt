package ds25.hotel.reservation.management.system.domain.user.dto

/** 로그인 요청 DTO */
data class LoginRequest(
    /** 이메일 */
    val email: String,
    /** 비밀번호 */
    val password: String
)
