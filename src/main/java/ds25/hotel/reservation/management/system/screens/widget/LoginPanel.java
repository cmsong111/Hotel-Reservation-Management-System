package ds25.hotel.reservation.management.system.screens.widget;


import javax.swing.*;
import java.awt.*;

public class LoginPanel extends JPanel  {
    public JButton btn_logout;

    public LoginPanel() {
        setLayout(new BorderLayout());

        JPanel eastPanel = new JPanel();

        btn_logout = new JButton("Log out");
        btn_logout.setActionCommand("logout");

        eastPanel.add(btn_logout);

        JPanel westPanel = new JPanel();
        westPanel.add(new Label("Welcome"));

        add(new JLabel(), BorderLayout.CENTER);
        add(eastPanel, BorderLayout.EAST);
        add(westPanel, BorderLayout.WEST);
    }
}
