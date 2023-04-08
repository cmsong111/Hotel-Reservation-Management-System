package ds25.hotel.reservation.management.system.screens.admin;

import ds25.hotel.reservation.management.system.screens.auth.LoginPage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminMainPage extends JFrame implements ActionListener {

    JLabel label;
    Panel panel;
    JButton btn_hotel, btn_room, btn_user, btn_reservation, btn_logout;

    public AdminMainPage() {



        label = new JLabel("관리자 페이지");
        panel = new Panel();
        btn_logout = new JButton("로그아웃");

        btn_logout.setBounds(100, 100, 100, 100);
        btn_logout.addActionListener(this);
        btn_logout.setActionCommand("logout");


        add(label);
        add(btn_logout);
        add(panel);


        setTitle("관리자 페이지");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        setSize(500, 500);
        setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();

        if (cmd.equals("logout")) {
            this.dispose();
            new LoginPage();
        }
    }
}
