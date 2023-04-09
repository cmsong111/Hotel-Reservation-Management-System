package ds25.hotel.reservation.management.system.screens.widget;


import javax.swing.*;
import java.awt.*;

public class LoginPanel extends JPanel {

    public LoginPanel() {
        setLayout(new BorderLayout());

        JPanel eastPanel = new JPanel();
        eastPanel.add(new JButton("Log out"));

        JPanel westPanel = new JPanel();
        westPanel.add(new Label("Welcome"));

        add(new JLabel(), BorderLayout.CENTER);
        add(eastPanel, BorderLayout.EAST);
        add(westPanel, BorderLayout.WEST);
    }
}
