package ds25.hotel.reservation.management.system.domain.user.dto

data class SignUpRequest(
    /** 이메일 */
    val email: String,
    /** 비밀번호 */
    val password: String,
    /** 이름 */
    val name: String,
    /** 전화번호 */
    val phone: String
)
