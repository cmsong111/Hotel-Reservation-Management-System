package ds25.hotel.reservation.management.system.screens;

import ds25.hotel.reservation.management.system.configuration.DependencyInjection;
import ds25.hotel.reservation.management.system.configuration.Singleton;
import ds25.hotel.reservation.management.system.entity.user.User;
import ds25.hotel.reservation.management.system.entity.user.UserGrade;
import ds25.hotel.reservation.management.system.entity.user.UserRole;
import ds25.hotel.reservation.management.system.service.user.UserService;
import lombok.extern.slf4j.Slf4j;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

@Slf4j
public class UserRegisterPage extends JFrame implements ActionListener {

    private UserService userService = new UserService();

    private JTextField idTextField;
    private JPasswordField passwordTextField;
    private JPasswordField passwordConfirmField;
    private JTextField nameTextField;
    private JTextField phoneTextField;
    private JTextField emailTextField;

    private JButton checkIdButton;
    private JButton registerButton;
    private JButton cancelButton;
    private Panel panel;

    public UserRegisterPage() {
        setTitle("회원가입");
        setResizable(false);

        panel = new Panel();

        idTextField = new JTextField("user");
        idTextField.setBounds(100, 100, 400, 30);

        passwordTextField = new JPasswordField("user");
        passwordTextField.setBounds(100, 150, 400, 30);

        passwordConfirmField = new JPasswordField("user");
        passwordConfirmField.setBounds(100, 200, 400, 30);

        nameTextField = new JTextField("Name");
        nameTextField.setBounds(100, 250, 400, 30);

        phoneTextField = new JTextField("Phone");
        phoneTextField.setBounds(100, 300, 400, 30);

        emailTextField = new JTextField("Email");
        emailTextField.setBounds(100, 350, 400, 30);

        checkIdButton = new JButton("Check ID");
        checkIdButton.setBounds(100, 450, 100, 30);
        checkIdButton.setActionCommand("Check ID");
        checkIdButton.addActionListener(this);

        registerButton = new JButton("Register");
        registerButton.setBounds(100, 400, 100, 30);
        registerButton.setActionCommand("Register");
        registerButton.addActionListener(this);

        cancelButton = new JButton("Cancel");
        cancelButton.setBounds(400, 400, 100, 30);
        cancelButton.setActionCommand("Cancel");
        cancelButton.addActionListener(this);

        add(idTextField);
        add(passwordTextField);
        add(passwordConfirmField);
        add(nameTextField);
        add(phoneTextField);
        add(emailTextField);
        add(checkIdButton);
        add(registerButton);
        add(cancelButton);
        add(panel);

        setSize(600, 600);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if (command.equals("Register")) {
            if (!passwordTextField.getText().equals(passwordConfirmField.getText())) {
                JOptionPane.showMessageDialog(null, "비밀번호가 일치하지 않습니다.");
                return;
            }
            // Builder 패턴을 사용하여 객체 생성
            User user = User.builder()
                    .id(idTextField.getText())
                    .password(passwordTextField.getText())
                    .name(nameTextField.getText())
                    .phone(phoneTextField.getText())
                    .email(emailTextField.getText())
                    .role(UserRole.USER)
                    .grade(UserGrade.BRONZE)
                    .build();
            try {
                log.info(( userService.register(user)).toString());
                JOptionPane.showMessageDialog(null, "회원가입이 완료되었습니다.");
                dispose();
            } catch (Exception ex) {
                ex.printStackTrace();
                log.info("회원가입 실패");
                JOptionPane.showMessageDialog(null, "회원가입에 실패 했습니다.");
            }

        } else if (command.equals("Check ID")) {
            if (userService.isExistId(idTextField.getText())) {
                JOptionPane.showMessageDialog(null, "이미 사용중인 Id 입니다.");
            } else {
                JOptionPane.showMessageDialog(null, "사용 가능한 Id 입니다.");
            }

        } else if (command.equals("Cancel")) {
            dispose();
        }
    }
}
