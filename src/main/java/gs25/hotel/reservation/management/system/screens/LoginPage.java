package gs25.hotel.reservation.management.system.screens;

import gs25.hotel.reservation.management.system.entity.User;
import gs25.hotel.reservation.management.system.observer.UserData;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

public class LoginPage extends JFrame implements Observer , ActionListener {

    private User loginUser;
    private UserData userData;

    JTextField tx_ID;
    JPasswordField tx_PassWord;
    JLabel logoImage;

    @Override
    public void update(Observable o, Object arg) {
        loginUser = (User) arg;
    }

    public LoginPage(UserData userData) {
        this.userData = userData;
        userData.registerObserver(this);
    }

    public LoginPage() {
        setVisible(true);
        setTitle("호텔 예약 시스템");
        getContentPane().setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(600, 600);


        logoImage = new JLabel();
        logoImage.setBounds(50, 150, 200, 200);
        logoImage.setIcon(new ImageIcon ("./src/main/resources/images/Hotel_Icon.png"));


        JLabel label = new JLabel("GS25 호텔 예약 시스템");
        label.setBounds(100, 100, 100, 100);

        tx_ID = new JTextField("아이디");
        tx_ID.setBounds(100,300,400,50);
        tx_PassWord = new JPasswordField("비밀번호");
        tx_PassWord.setBounds(100,370,400,50);

        getContentPane().add(tx_ID);
        getContentPane().add(tx_PassWord);
        getContentPane().add(label);
        getContentPane().add(logoImage);


        tx_ID.setVisible(true);
        tx_PassWord.setVisible(true);
        logoImage.setVisible(true);
        label.setVisible(true);

        setVisible(true);



    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
