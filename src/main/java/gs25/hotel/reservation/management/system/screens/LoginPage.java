package gs25.hotel.reservation.management.system.screens;

import gs25.hotel.reservation.management.system.configuration.Singleton;
import gs25.hotel.reservation.management.system.entity.User;
import gs25.hotel.reservation.management.system.observer.UserData;
import gs25.hotel.reservation.management.system.service.UserService;
import lombok.extern.slf4j.Slf4j;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

@Slf4j
public class LoginPage extends JFrame implements Observer, ActionListener {

    private User loginUser;
    private UserData userData;
    private UserService userService = Singleton.getInstance().getUserService();

    JLabel label;
    JTextField tx_ID;
    JPasswordField tx_PassWord;
    JLabel logoImage;
    JButton btn_Login, btn_SignUp;

    @Override
    public void update(Observable o, Object arg) {
        loginUser = (User) arg;
    }

    public LoginPage(UserData userData) {
        this.userData = userData;
        userData.registerObserver(this);
    }

    public LoginPage() {
        setTitle("호텔 예약 시스템");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(600, 650);


        logoImage = new JLabel();

        logoImage.setIcon(new ImageIcon(new ImageIcon(getClass().getResource("/images/Hotel_Icon.png")).getImage().getScaledInstance(200, 200, java.awt.Image.SCALE_SMOOTH)));
        logoImage.setBounds(100, 150, 200, 200);


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

        add(label);
        add(tx_ID);
        add(tx_PassWord);
        add(btn_Login);
        add(btn_SignUp);
        add(logoImage);


        setVisible(true);


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        String id = tx_ID.getText();
        String password = tx_PassWord.getText();

        if (command.equals("login")) {
            log.info("로그인 버튼 클릭");
            userService.login(id, password);
            // 로그인 버튼 클릭 시
            // 로그인 성공 시
            // 로그인 실패 시
        } else if (command.equals("signup")) {
            log.info("회원가입 버튼 클릭");
            // 회원가입 버튼 클릭 시
            // 회원가입 성공 시
            // 회원가입 실패 시
        }
    }
}
