package ds25.hotel.reservation.management.system.domain.user.presentation;

import ds25.hotel.reservation.management.system.global.config.ApplicationContextProvider;
import ds25.hotel.reservation.management.system.global.configuration.Singleton;
import ds25.hotel.reservation.management.system.domain.user.dto.UserDto;
import ds25.hotel.reservation.management.system.screens.widget.EastPanel;
import ds25.hotel.reservation.management.system.screens.widget.LoginPanel;
import ds25.hotel.reservation.management.system.screens.widget.NorthPanel;
import ds25.hotel.reservation.management.system.screens.widget.WestPanel;
import ds25.hotel.reservation.management.system.domain.user.application.UserService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserInfo extends JFrame implements ActionListener {

    private JPanel centerPanel;
    private LoginPanel loginPanel;
    private JTextField tx_ID, tx_PassWord, tx_Name, tx_Phone, tx_Email;
    JButton btn_back,btn_edit,btn_save;
    private UserDto user = Singleton.getInstance().getUser();
    private final UserService userService;


    public UserInfo() {
        userService = ApplicationContextProvider.INSTANCE.getBean(UserService.class);


        setLayout(new BorderLayout());
        centerPanel = new JPanel();

        centerPanel.setLayout(new GridLayout(7, 2, 30, 30));

        centerPanel.add(new JLabel("ID"));
        tx_ID = new JTextField(user.getId());
        tx_ID.setEditable(false);
        centerPanel.add(tx_ID);

        centerPanel.add(new JLabel("Password"));
        tx_PassWord = new JTextField(user.getPassword());
        tx_PassWord.setEditable(false);
        centerPanel.add(tx_PassWord);

        centerPanel.add(new JLabel("Name"));
        tx_Name = new JTextField(user.getName());
        tx_Name.setEditable(false);
        centerPanel.add(tx_Name);

        centerPanel.add(new JLabel("Phone"));
        tx_Phone = new JTextField(user.getPhone());
        tx_Phone.setEditable(false);
        centerPanel.add(tx_Phone);

        centerPanel.add(new JLabel("Email"));
        tx_Email = new JTextField(user.getEmail());
        tx_Email.setEditable(false);
        centerPanel.add(tx_Email);


        loginPanel = new LoginPanel();
        loginPanel.btn_myPage.setEnabled(false);
        loginPanel.btn_logout.addActionListener(this);


        add(centerPanel, BorderLayout.CENTER);
        add(new NorthPanel(), BorderLayout.NORTH);
        add(new WestPanel(), BorderLayout.WEST);
        add(new EastPanel(), BorderLayout.EAST);


        JPanel southPanel = new JPanel(new BorderLayout());
        JPanel updatePanel = new JPanel(new GridLayout(1, 2));



        btn_back = new JButton("뒤로가기");
        btn_back.addActionListener(this);
        btn_back.setActionCommand("back");
        southPanel.add(btn_back, BorderLayout.WEST);

        btn_edit = new JButton("수정하기");
        btn_edit.addActionListener(this);
        btn_edit.setActionCommand("edit");
        updatePanel.add(btn_edit);

        btn_save = new JButton("저장하기");
        btn_save.addActionListener(this);
        btn_save.setActionCommand("save");
        btn_save.setEnabled(false);
        updatePanel.add(btn_save);

        updatePanel.add(btn_save);
        updatePanel.add(btn_edit);


        southPanel.add(updatePanel, BorderLayout.EAST);


        add(southPanel, BorderLayout.SOUTH);

        setSize(800, 600);
        setLocationRelativeTo(null);
        setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if (command.equals("back")) {
            dispose();
        } else if (command.equals("edit")) {
            tx_PassWord.setEditable(true);
            tx_Name.setEditable(true);
            tx_Phone.setEditable(true);
            tx_Email.setEditable(true);
            btn_edit.setEnabled(false);
            btn_save.setEnabled(true);
        } else if (command.equals("save")) {
            user.setPassword(tx_PassWord.getText());
            user.setName(tx_Name.getText());
            user.setPhone(tx_Phone.getText());
            user.setEmail(tx_Email.getText());

            try {
                userService.updateUser(tx_Name.getText(), tx_Phone.getText());
                JOptionPane.showMessageDialog(null, "수정되었습니다.");

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
            tx_PassWord.setEditable(false);
            tx_Name.setEditable(false);
            tx_Phone.setEditable(false);
            tx_Email.setEditable(false);
            btn_edit.setEnabled(true);
            btn_save.setEnabled(false);

        } else if (command.equals("logout")) {
            new LoginPage();
            dispose();
        }
    }
}
