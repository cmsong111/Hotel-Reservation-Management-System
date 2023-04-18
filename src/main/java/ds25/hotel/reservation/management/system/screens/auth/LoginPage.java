package ds25.hotel.reservation.management.system.screens.auth;

import ds25.hotel.reservation.management.system.configuration.Singleton;
import ds25.hotel.reservation.management.system.configuration.SpringBridge;
import ds25.hotel.reservation.management.system.dto.user.UserDto;
import ds25.hotel.reservation.management.system.entity.user.UserRole;
import ds25.hotel.reservation.management.system.screens.HotelSelectionPage;
import ds25.hotel.reservation.management.system.screens.admin.AdminHotelSelectPage;
import ds25.hotel.reservation.management.system.screens.widget.EastPanel;
import ds25.hotel.reservation.management.system.screens.widget.NorthPanel;
import ds25.hotel.reservation.management.system.screens.widget.SouthPanel;
import ds25.hotel.reservation.management.system.screens.widget.WestPanel;
import ds25.hotel.reservation.management.system.service.user.UserService;
import lombok.extern.slf4j.Slf4j;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


@Slf4j
public class LoginPage extends JFrame implements ActionListener {
    private UserService userService;
    private Panel centerPanel;
    private JLabel label;
    private JTextField tx_ID;
    private JPasswordField tx_PassWord;
    private JButton btn_Login, btn_SignUp;
    private JLabel lb_ID, lb_PassWord;


    public LoginPage() {
        userService = SpringBridge.getInstance().getBean(UserService.class);

        setTitle("호텔 예약 시스템");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setLayout(new BorderLayout());

        centerPanel = new Panel(new GridLayout(7, 2, 30, 30));

        lb_ID = new JLabel("ID");
        lb_ID.setHorizontalAlignment(SwingConstants.CENTER);
        tx_ID = new JTextField("user");

        lb_PassWord = new JLabel("Password");
        lb_PassWord.setHorizontalAlignment(SwingConstants.CENTER);
        tx_PassWord = new JPasswordField("user");

        btn_Login = new JButton("로그인");
        btn_Login.setActionCommand("login");
        btn_Login.addActionListener(this);

        btn_SignUp = new JButton("회원가입");
        btn_SignUp.setActionCommand("signup");
        btn_SignUp.addActionListener(this);

        centerPanel.add(new JLabel());
        centerPanel.add(new JLabel());
        centerPanel.add(lb_ID);
        centerPanel.add(tx_ID);
        centerPanel.add(lb_PassWord);
        centerPanel.add(tx_PassWord);
        centerPanel.add(new JLabel());
        centerPanel.add(new JLabel());
        centerPanel.add(btn_Login);
        centerPanel.add(btn_SignUp);

        add(centerPanel, BorderLayout.CENTER);
        add(new NorthPanel(), BorderLayout.NORTH);
        add(new WestPanel(), BorderLayout.WEST);
        add(new EastPanel(), BorderLayout.EAST);
        add(new SouthPanel(), BorderLayout.SOUTH);

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
                Singleton.getInstance().setAdmin(user);
                new AdminHotelSelectPage();
            } else if (user != null) {
                JOptionPane.showMessageDialog(null, "로그인 성공");
                new HotelSelectionPage();
                dispose();
            }
        } else if (command.equals("signup")) {
            log.info("회원가입 버튼 클릭");
            new UserRegisterPage();
        }
    }
}
