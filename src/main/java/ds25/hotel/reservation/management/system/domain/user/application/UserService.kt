package ds25.hotel.reservation.management.system.domain.user.application

import ds25.hotel.reservation.management.system.global.config.AuthContext
import ds25.hotel.reservation.management.system.domain.user.dao.UserRepository
import ds25.hotel.reservation.management.system.domain.user.domain.User
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserService(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder
) {
    @Transactional
    fun updateUser(name: String, phone: String) {
        val user: User = userRepository.findByEmail(AuthContext.userEmail!!)
            ?: throw RuntimeException("User not found")

        user.update(name, phone)
    }

    @Transactional
    fun changePassword(originalPassword: String, newPassword: String, confirmPassword: String) {
        val user = userRepository.findByEmail(AuthContext.userEmail!!)
            ?: throw RuntimeException("User not found")

        if (!passwordEncoder.matches(originalPassword, user.password)) {
            throw RuntimeException("Password is incorrect")
        }

        if (newPassword != confirmPassword) {
            throw RuntimeException("Password does not match")
        }

        user.changePassword(newPassword, passwordEncoder)
    }

    @Transactional
    fun deleteUser() {
        val user = userRepository.findByEmail(AuthContext.userEmail!!)
            ?: throw RuntimeException("User not found")

        userRepository.delete(user)
        AuthContext.clear()
    }

}
