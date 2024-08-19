package ds25.hotel.reservation.management.system.domain.user.presentation

import ds25.hotel.reservation.management.system.global.config.ApplicationContextProvider.getBean
import ds25.hotel.reservation.management.system.domain.user.application.AuthService
import ds25.hotel.reservation.management.system.domain.user.dto.SignUpRequest
import ds25.hotel.reservation.management.system.screens.widget.EastPanel
import ds25.hotel.reservation.management.system.screens.widget.NorthPanel
import ds25.hotel.reservation.management.system.screens.widget.WestPanel
import org.slf4j.LoggerFactory
import java.awt.BorderLayout
import java.awt.GridLayout
import java.awt.Panel
import java.awt.event.ActionEvent
import java.awt.event.ActionListener
import java.awt.event.KeyEvent
import java.awt.event.KeyListener
import javax.swing.*

/** 회원가입 페이지 */
class SignUpPage : JFrame(), ActionListener, KeyListener {
    private val authService: AuthService = getBean(AuthService::class.java)
    private val log = LoggerFactory.getLogger(this::class.java)

    private val emailJLabel: JLabel = JLabel("Email")

    private val passwordTextField: JPasswordField
    private val passwordConfirmField: JPasswordField
    private val nameTextField: JTextField
    private val phoneTextField: JTextField
    private val emailTextField: JTextField

    private val registerButton: JButton
    private val cancelButton: JButton
    private val panel: Panel

    init {
        title = "회원가입"
        isResizable = false
        layout = BorderLayout()

        panel = Panel()
        panel.layout = GridLayout(7, 2, 30, 30)


        panel.add(emailJLabel)
        emailTextField = JTextField("Email")
        emailTextField.addKeyListener(this)
        panel.add(emailTextField)

        panel.add(JLabel("Password"))
        passwordTextField = JPasswordField("user")
        panel.add(passwordTextField)

        panel.add(JLabel("Password Confirm"))
        passwordConfirmField = JPasswordField("user")
        panel.add(passwordConfirmField)

        panel.add(JLabel("Name"))
        nameTextField = JTextField("Name")
        panel.add(nameTextField)

        panel.add(JLabel("Phone"))
        phoneTextField = JTextField("Phone")
        panel.add(phoneTextField)

        registerButton = JButton("회원가입")
        registerButton.addActionListener(this)
        panel.add(registerButton)

        cancelButton = JButton("취소")
        cancelButton.addActionListener(this)
        panel.add(cancelButton)


        add(panel, BorderLayout.CENTER)
        add(NorthPanel(), BorderLayout.NORTH);
        add(WestPanel(), BorderLayout.WEST);
        add(EastPanel(), BorderLayout.EAST);

        setSize(600, 800)
        setLocationRelativeTo(null)
        isVisible = true
    }

    override fun actionPerformed(e: ActionEvent) {

        when (e.source) {
            registerButton -> {
                // 이메일 중복 확인
                if (!checkEmail()) {
                    JOptionPane.showMessageDialog(null, "이미 사용중인 Id 입니다.")
                    return
                }

                // 비밀번호 확인
                if (passwordTextField.text != passwordConfirmField.text) {
                    JOptionPane.showMessageDialog(null, "비밀번호가 일치하지 않습니다.")
                    return
                }

                // 회원가입
                authService.signUp(
                    SignUpRequest(
                        email = emailTextField.text,
                        password = passwordTextField.text,
                        name = nameTextField.text,
                        phone = phoneTextField.text
                    )
                )

                JOptionPane.showMessageDialog(null, "회원가입이 완료되었습니다.")
                dispose()
            }

            cancelButton -> {
                dispose()
            }
        }
    }

    override fun keyTyped(e: KeyEvent?) {
        checkEmail()
    }

    override fun keyPressed(e: KeyEvent?) {
        checkEmail()
    }

    override fun keyReleased(e: KeyEvent?) {
        checkEmail()
    }

    private fun checkEmail(): Boolean {
        return if (authService.isAvailableEmail(emailTextField.text)) {
            emailJLabel.text = "<html>Email<br>사용가능한 Id 입니다.</html>"
            true
        } else {
            emailJLabel.text = "<html>Email<br>이미 사용중인 Id 입니다.</html>"
            false
        }
    }

}
