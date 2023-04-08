package ds25.hotel.reservation.management.system.screens.auth;

import ds25.hotel.reservation.management.system.configuration.Singleton;
import ds25.hotel.reservation.management.system.configuration.SpringBridge;
import ds25.hotel.reservation.management.system.dto.user.UserDto;
import ds25.hotel.reservation.management.system.entity.user.User;
import ds25.hotel.reservation.management.system.entity.user.UserRole;
import ds25.hotel.reservation.management.system.pattern.observer.Observable;
import ds25.hotel.reservation.management.system.pattern.observer.Observer;
import ds25.hotel.reservation.management.system.provider.UserProvider;
import ds25.hotel.reservation.management.system.screens.HotelSelectionPage;
import ds25.hotel.reservation.management.system.screens.admin.AdminMainPage;
import ds25.hotel.reservation.management.system.service.user.UserService;
import lombok.extern.slf4j.Slf4j;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Optional;


@Slf4j
public class LoginPage extends JFrame implements Observer, ActionListener {
    private UserService userService;

    UserProvider userProvider = Singleton.getInstance().getUserProvider();

    private Panel panel;
    private JLabel label;
    private JTextField tx_ID;
    private JPasswordField tx_PassWord;
    private JButton btn_Login, btn_SignUp, btn_logout,btn_admin;

    @Override
    public void update(Observable o, Object arg) {
        Optional<User> user = (Optional<User>) arg;
        if (user.isEmpty()) {
            btn_logout.setVisible(false);
            log.info("로그아웃 되었습니다.");
        } else {
            btn_logout.setVisible(true);
            log.info(user.get().getName() + "가 로그인했습니다.");
        }
    }


    public LoginPage() {
        userService = SpringBridge.getInstance().getBean(UserService.class);
        Singleton.getInstance().getUserProvider().registerObserver(this);

        setTitle("호텔 예약 시스템");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        panel = new Panel();

        label = new JLabel("GS25 호텔 예약 시스템");
        label.setBounds(100, 100, 400, 100);

        tx_ID = new JTextField("user");
        tx_ID.setBounds(100, 300, 400, 50);

        tx_PassWord = new JPasswordField("user");
        tx_PassWord.setBounds(100, 370, 400, 50);

        btn_Login = new JButton("로그인");
        btn_Login.setBounds(100, 450, 400, 50);
        btn_Login.setActionCommand("login");
        btn_Login.addActionListener(this);

        btn_SignUp = new JButton("회원가입");
        btn_SignUp.setBounds(100, 520, 400, 50);
        btn_SignUp.setActionCommand("signup");
        btn_SignUp.addActionListener(this);

        btn_logout = new JButton("로그아웃");
        btn_logout.setBounds(100, 590, 400, 50);
        btn_logout.setActionCommand("logout");
        btn_logout.addActionListener(this);
        btn_logout.setVisible(false);

        btn_admin = new JButton("관리자");
        btn_admin.setBounds(100, 660, 400, 50);
        btn_admin.setActionCommand("admin");
        btn_admin.addActionListener(this);


        add(label);
        add(tx_ID);
        add(tx_PassWord);
        add(btn_Login);
        add(btn_SignUp);
        add(btn_logout);
        add(btn_admin);
        add(panel);

        setSize(600, 800);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        String id = tx_ID.getText();
        String password = tx_PassWord.getText();

        if (command.equals("login")) {
            log.info("로그인 버튼 클릭");
            UserDto user;
            try {
                user = userService.login(id, password);
            } catch (Exception exception) {
                String message = exception.getMessage();
                JOptionPane.showMessageDialog(null, message);
                return;
            }
            if (user != null && user.getRole().equals(UserRole.ADMIN)) {
            	JOptionPane.showMessageDialog(null, "관리자 로그인 성공");
            	new AdminMainPage();
            	userProvider.removeObserver(this);
            	dispose();
            } else
            if (user != null) {
                JOptionPane.showMessageDialog(null, "로그인 성공");
                new HotelSelectionPage();
                userProvider.removeObserver(this);
                dispose();
            }
        } else if (command.equals("signup")) {
            log.info("회원가입 버튼 클릭");
            new UserRegisterPage();
        } else if (command.equals("logout")) {
            log.info("로그아웃 버튼 클릭");
            userProvider.updateUser(null);
            JOptionPane.showMessageDialog(null, "로그아웃 성공");
        } else if (command.equals("admin")) {
            log.info("관리자 버튼 클릭");
            new LoginPage();
        }
    }
}
