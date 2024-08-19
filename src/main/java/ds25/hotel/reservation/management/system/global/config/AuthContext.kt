package ds25.hotel.reservation.management.system.global.config

import ds25.hotel.reservation.management.system.domain.user.domain.User
import ds25.hotel.reservation.management.system.domain.user.domain.UserRole

object AuthContext {

    var userEmail: String? = null
        private set

    var userName: String? = null
        private set

    var userRole: MutableSet<UserRole> = mutableSetOf()
        private set

    fun setUser(user: User) {
        userEmail = user.email
        userRole = user.roles
        userName = user.name
    }

    fun clear() {
        userEmail = null
        userRole = mutableSetOf()
    }


}
