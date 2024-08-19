package ds25.hotel.reservation.management.system.global.common

open class BusinessException : RuntimeException {
    constructor(message: String) : super(message)
    constructor(message: String, cause: Throwable) : super(message, cause)
}
