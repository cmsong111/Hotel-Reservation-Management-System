package gs25.hotel.reservation.management.system.screens;

import gs25.hotel.reservation.management.system.configuration.Singleton;
import gs25.hotel.reservation.management.system.entity.user.User;
import gs25.hotel.reservation.management.system.observer.Observable;
import gs25.hotel.reservation.management.system.observer.Observer;
import gs25.hotel.reservation.management.system.provider.UserProvider;
import gs25.hotel.reservation.management.system.service.user.UserService;
import lombok.extern.slf4j.Slf4j;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Optional;


@Slf4j
public class LoginPage extends JFrame implements Observer, ActionListener {
    private UserService userService = Singleton.getInstance().getUserService();
    UserProvider userProvider = Singleton.getInstance().getUserProvider();

    private Panel panel;
    private JLabel label;
    private JTextField tx_ID;
    private JPasswordField tx_PassWord;
    private JButton btn_Login, btn_SignUp, btn_logout;


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

        Singleton.getInstance().getUserProvider().registerObserver(this);

        setTitle("호텔 예약 시스템");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        panel = new Panel();

        label = new JLabel("GS25 호텔 예약 시스템");
        label.setBounds(100, 100, 400, 100);

        tx_ID = new JTextField("아이디");
        tx_ID.setBounds(100, 300, 400, 50);

        tx_PassWord = new JPasswordField("비밀번호");
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

        add(label);
        add(tx_ID);
        add(tx_PassWord);
        add(btn_Login);
        add(btn_SignUp);
        add(btn_logout);
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
            Optional<User> user = userService.login(id, password);
            if (!user.isPresent()) {
                JOptionPane.showMessageDialog(null, "아이디 또는 비밀번호가 틀렸습니다.");
            } else {
                JOptionPane.showMessageDialog(null, "로그인 성공");
                new MainPage();
                userProvider.removeObserver(this);
                dispose();
            }
        } else if (command.equals("signup")) {
            log.info("회원가입 버튼 클릭");
        } else if (command.equals("logout")) {
            log.info("로그아웃 버튼 클릭");
            userProvider.updateUser(null);
            JOptionPane.showMessageDialog(null, "로그아웃 성공");
        }
    }
}
