package ds25.hotel.reservation.management.system

import ds25.hotel.reservation.management.system.screens.LoadingPage
import ds25.hotel.reservation.management.system.domain.user.presentation.LoginPage
import ds25.hotel.reservation.management.system.global.config.CustomEventQueue
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import java.awt.Toolkit
import javax.swing.JFrame
import javax.swing.UIManager

@SpringBootApplication
class HotelReservationManagementSystem

fun main(args: Array<String>) {
    // 예외 처리를 위한 이벤트 큐 설정
    Toolkit.getDefaultToolkit().systemEventQueue.push(CustomEventQueue())

    // Design 변경
    UIManager.setLookAndFeel("com.formdev.flatlaf.FlatLightLaf")
    JFrame.setDefaultLookAndFeelDecorated(true)

    // 로딩 페이지 생성
    val loadingPage = LoadingPage()


    // 스프링 부트 애플리케이션 실행
    runApplication<HotelReservationManagementSystem>(*args)
    loadingPage.dispose()
    LoginPage()
}
