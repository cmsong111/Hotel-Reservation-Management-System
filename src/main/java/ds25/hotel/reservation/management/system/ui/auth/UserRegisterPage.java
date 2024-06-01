package ds25.hotel.reservation.management.system.ui.auth;

import ds25.hotel.reservation.management.system.di.DiContext;
import ds25.hotel.reservation.management.system.domain.user.application.AuthService;
import ds25.hotel.reservation.management.system.ui.auth.data.RegisterForm;
import ds25.hotel.reservation.management.system.ui.dto.user.UserDto;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import javax.swing.*;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UserRegisterPage extends JFrame implements ActionListener {

	private final AuthService authService;

	private final JTextField idTextField;
	private final JPasswordField passwordTextField;
	private final JPasswordField passwordConfirmField;
	private final JTextField nameTextField;
	private final JTextField phoneTextField;
	private final JTextField emailTextField;

	private final JButton checkIdButton;
	private final JButton registerButton;
	private final JButton cancelButton;
	private final Panel panel;

	public UserRegisterPage() {
		authService = DiContext.getInstance().getComponent(AuthService.class);

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

		setSize(600, 800);
		setLocationRelativeTo(null);
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();

		if (command.equals("Register")) {
			if (!Arrays.toString(passwordTextField.getPassword()).equals(Arrays.toString(passwordConfirmField.getPassword()))) {
				JOptionPane.showMessageDialog(null, "비밀번호가 일치하지 않습니다.");
				return;
			}
			try {
				UserDto registeted_user = authService.register(new RegisterForm(
						idTextField.getText(),
						Arrays.toString(passwordTextField.getPassword()),
						nameTextField.getText(),
						phoneTextField.getText()
				));
				log.info(registeted_user.toString());
				JOptionPane.showMessageDialog(null, "회원가입이 완료되었습니다.");
				dispose();
			} catch (Exception ex) {
				String message = ex.getMessage();
				JOptionPane.showMessageDialog(null, message);
			}

		} else if (command.equals("Check ID")) {
			if (authService.isExistEmail(idTextField.getText())) {
				JOptionPane.showMessageDialog(null, "이미 사용중인 Id 입니다.");
			} else {
				JOptionPane.showMessageDialog(null, "사용 가능한 Id 입니다.");
			}

		} else if (command.equals("Cancel")) {
			dispose();
		}
	}
}
