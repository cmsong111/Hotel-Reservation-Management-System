package ds25.hotel.reservation.management.system.global.config

import org.springframework.context.ApplicationContext
import org.springframework.context.ApplicationContextAware
import org.springframework.stereotype.Component

/**
 * ApplicationContextProvider
 *
 * 해당 클래스는 스프링 컨테이너에 등록된 빈을 가져오기 위한 클래스입니다.
 */
@Component
object ApplicationContextProvider : ApplicationContextAware {

    /**
     * 스프링 컨테이너 객체
     */
    private var applicationContext: ApplicationContext? = null

    /**
     * 스프링 컨테이너에 등록된 빈을 등록하는 메소드
     *
     * @param context 스프링 컨테이너 객체
     */
    override fun setApplicationContext(context: ApplicationContext) {
        applicationContext = context
    }

    /**
     * 스프링 컨테이너에 등록된 빈을 가져오는 메소드
     *
     * @param clazz 빈 객체의 클래스
     * @return 빈 객체
     */
    fun <T> getBean(clazz: Class<T>): T {
        return applicationContext!!.getBean(clazz)
    }
}
