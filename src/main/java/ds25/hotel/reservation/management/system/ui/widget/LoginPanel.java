package ds25.hotel.reservation.management.system.ui.widget;


import java.awt.*;
import javax.swing.*;

public class LoginPanel extends JPanel  {
    public JButton btn_logout;
    public JButton btn_myPage;

    public LoginPanel() {
        setLayout(new BorderLayout());

        JPanel eastPanel = new JPanel();

        btn_logout = new JButton("back");
        btn_logout.setActionCommand("back");

        btn_myPage = new JButton("My Page");
        btn_myPage.setActionCommand("myPage");

        eastPanel.add(btn_logout);

        JPanel westPanel = new JPanel();
        westPanel.add(btn_myPage);

        add(new JLabel(), BorderLayout.CENTER);
        add(eastPanel, BorderLayout.EAST);
        add(westPanel, BorderLayout.WEST);
    }
}
