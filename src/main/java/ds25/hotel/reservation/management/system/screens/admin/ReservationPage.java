package ds25.hotel.reservation.management.system.screens.admin;

import javax.swing.*;
import java.awt.event.*;

public class ReservationPage extends JFrame implements ActionListener {
    JLabel idLabel,nameLabel, phoneLabel, dateLabel;
    JTextField idField, nameField, phoneField, dateField;
    JButton addButton, updateButton;

    public ReservationPage() {
        setTitle("Reservation Page");
        setSize(320, 240);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(null);

        idLabel = new JLabel("아이디:");
        idLabel.setBounds(10, 110, 80, 25);
        panel.add(idLabel);

        idField = new JTextField(20);
        idField.setBounds(100, 110, 165, 25);
        panel.add(idField);


        nameLabel = new JLabel("이름:");
        nameLabel.setBounds(10, 20, 80, 25);
        panel.add(nameLabel);

        nameField = new JTextField(20);
        nameField.setBounds(100, 20, 165, 25);
        panel.add(nameField);


        phoneLabel = new JLabel("번호:");
        phoneLabel.setBounds(10, 50, 80, 25);
        panel.add(phoneLabel);

        phoneField = new JTextField(20);
        phoneField.setBounds(100, 50, 165, 25);
        panel.add(phoneField);

        dateLabel = new JLabel("날짜:");
        dateLabel.setBounds(10, 80, 80, 25);
        panel.add(dateLabel);

        dateField = new JTextField(20);
        dateField.setBounds(100, 80, 165, 25);
        panel.add(dateField);

        // 추가 및 수정 버튼
        addButton = new JButton("예약 정보 추가");
        addButton.setBounds(10, 150, 140, 25);
        addButton.addActionListener(this);
        panel.add(addButton);

        updateButton = new JButton("예약 수정 페이지");
        updateButton.setBounds(160, 150, 140, 25);
        updateButton.addActionListener(this);
        panel.add(updateButton);

        add(panel);
        setVisible(true);
    }

    // 버튼 클릭 이벤트 처리
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addButton) {
            // 예약 추가 기능 구현
        } else if (e.getSource() == updateButton) {
            // 예약 수정 기능 구현
        }
    }

    public static void main(String[] args) {
        new ReservationPage();
    }
}