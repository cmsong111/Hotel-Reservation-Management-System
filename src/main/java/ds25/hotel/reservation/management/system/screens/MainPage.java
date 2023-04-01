package ds25.hotel.reservation.management.system.screens;

import ds25.hotel.reservation.management.system.configuration.Singleton;
import ds25.hotel.reservation.management.system.entity.user.User;
import ds25.hotel.reservation.management.system.observer.Observable;
import ds25.hotel.reservation.management.system.observer.Observer;
import ds25.hotel.reservation.management.system.provider.UserProvider;
import lombok.extern.slf4j.Slf4j;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Optional;

@Slf4j
public class MainPage extends JFrame implements Observer, ActionListener {
    UserProvider userProvider = Singleton.getInstance().getUserProvider();
    JLabel label = new JLabel("DS25 호텔 예약 관리 시스템");
    JLabel userLabel;
    JButton btn_logout = new JButton("로그아웃");
    JPanel panel = new JPanel();


    @Override
    public void update(Observable o, Object arg) {
        Optional<User> user = (Optional<User>) arg;
        if (user.isEmpty()) {
            log.info("로그아웃 되었습니다. 로그인 페이지로 이동합니다.");
            new LoginPage();
            userProvider.removeObserver(this);
            dispose();
        } else {
            userLabel.setText(user.get().getName() + "님 환영합니다.");
        }
    }

    public MainPage() {
        userProvider.registerObserver(this);

        setTitle("DS25 호텔 예약 관리 시스템");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        userLabel = new JLabel(userProvider.getUser().get().getName() + "님 환영합니다.");

        label.setBounds(100, 100, 400, 100);
        userLabel.setBounds(100, 200, 400, 100);
        btn_logout.setBounds(100, 300, 400, 100);

        btn_logout.addActionListener(this);
        btn_logout.setActionCommand("logout");

        add(label);
        add(userLabel);
        add(btn_logout);

        add(panel);


        setSize(800, 600);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if (command.equals("logout")) {
            log.info("로그아웃 버튼 클릭");
            userProvider.updateUser(null);
        }
    }
}
