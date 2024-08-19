package ds25.hotel.reservation.management.system.domain.user.presentation

import ds25.hotel.reservation.management.system.global.config.ApplicationContextProvider.getBean
import ds25.hotel.reservation.management.system.domain.user.application.AuthService
import ds25.hotel.reservation.management.system.domain.user.domain.UserRole
import ds25.hotel.reservation.management.system.domain.user.dto.LoginRequest
import ds25.hotel.reservation.management.system.screens.HotelSelectionPage
import ds25.hotel.reservation.management.system.screens.admin.AdminHotelSelectPage
import ds25.hotel.reservation.management.system.screens.widget.EastPanel
import ds25.hotel.reservation.management.system.screens.widget.NorthPanel
import ds25.hotel.reservation.management.system.screens.widget.SouthPanel
import ds25.hotel.reservation.management.system.screens.widget.WestPanel
import org.slf4j.LoggerFactory
import java.awt.BorderLayout
import java.awt.GridLayout
import java.awt.Panel
import java.awt.event.ActionEvent
import java.awt.event.ActionListener
import javax.swing.*


class LoginPage : JFrame(), ActionListener {
    private val authService: AuthService = getBean(AuthService::class.java)
    private val log = LoggerFactory.getLogger(this::class.java)
    private val centerPanel: Panel

    // 이메일 입력
    private val emailLabel: JLabel
    private val emailInput: JTextField

    // 비밀번호 입력
    private val passwordLabel: JLabel
    private val passwordInput: JPasswordField

    // 로그인 & 회원가입 버튼
    private val btnLogin: JButton
    private val btnSignup: JButton


    init {
        title = "호텔 예약 시스템"
        defaultCloseOperation = EXIT_ON_CLOSE
        isResizable = false
        layout = BorderLayout()

        centerPanel = Panel(GridLayout(7, 2, 30, 30))

        emailLabel = JLabel("ID")
        emailLabel.horizontalAlignment = SwingConstants.CENTER
        emailInput = JTextField("user@example.com")

        passwordLabel = JLabel("Password")
        passwordLabel.horizontalAlignment = SwingConstants.CENTER
        passwordInput = JPasswordField("user")

        btnLogin = JButton("로그인")
        btnLogin.addActionListener(this)

        btnSignup = JButton("회원가입")
        btnSignup.addActionListener(this)

        centerPanel.add(JLabel())
        centerPanel.add(JLabel())
        centerPanel.add(JLabel())
        centerPanel.add(JLabel())
        centerPanel.add(emailLabel)
        centerPanel.add(emailInput)
        centerPanel.add(passwordLabel)
        centerPanel.add(passwordInput)
        centerPanel.add(JLabel())
        centerPanel.add(JLabel())
        centerPanel.add(btnLogin)
        centerPanel.add(btnSignup)

        add(centerPanel, BorderLayout.CENTER)
        add(NorthPanel(), BorderLayout.NORTH)
        add(WestPanel(), BorderLayout.WEST)
        add(EastPanel(), BorderLayout.EAST)
        add(SouthPanel(), BorderLayout.SOUTH)

        setSize(600, 800)
        setLocationRelativeTo(null)
        isVisible = true
    }


    override fun actionPerformed(e: ActionEvent) {
        when (e.source) {
            // 로그인 버튼 클릭
            btnLogin -> {
                // 이메일과 비밀번호 입력 확인
                if (emailInput.text.isEmpty() || passwordInput.text.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "아이디와 비밀번호를 입력해주세요.")
                    return
                }

                // 로그인 요청
                val user = authService.login(
                    LoginRequest(
                        email = emailInput.text,
                        password = passwordInput.text
                    )
                )

                // 관리자일 경우 관리자 페이지로 이동할지 사용자 페이지로 이동할지 결정
                if (user.roles.contains(UserRole.ADMIN)) {
                    JOptionPane.showMessageDialog(null, "관리자 로그인 성공")
                    AdminHotelSelectPage()
                } else {
                    JOptionPane.showMessageDialog(null, "로그인 성공")
                    HotelSelectionPage()
                    dispose()
                }

            }
            // 회원가입 버튼 클릭
            btnSignup -> {
                log.info("회원가입 버튼 클릭")
                SignUpPage()
            }
        }
    }
}
