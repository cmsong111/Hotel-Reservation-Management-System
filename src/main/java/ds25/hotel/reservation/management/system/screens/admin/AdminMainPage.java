package ds25.hotel.reservation.management.system.screens.admin;

import ds25.hotel.reservation.management.system.screens.admin.EmptyRoomCheck;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminMainPage extends JFrame {

    public AdminMainPage() {
        setTitle("관리자 메인 페이지");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel buttonPanel = new JPanel(new GridLayout(3, 3,10,10));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JButton button1 = new JButton("호텔룸 관리");
        button1.setPreferredSize(new Dimension(100, 50));
        button1.setMinimumSize(new Dimension(0, 0));

        JButton button2 = new JButton("버튼 2");
        button2.setPreferredSize(new Dimension(120, 70));
        button2.setMinimumSize(new Dimension(0, 0));

        JButton button3 = new JButton("버튼 3");
        button3.setPreferredSize(new Dimension(10, 20));
        button3.setMinimumSize(new Dimension(0, 0));

        JButton button4 = new JButton("버튼 4");
        JButton button5 = new JButton("버튼 5");
        JButton button6 = new JButton("버튼 6");
        JButton button7 = new JButton("빈 방 조회");
        JButton button8 = new JButton("버튼 8");
        JButton button9 = new JButton("버튼 9");

        button4.setPreferredSize(new Dimension(100, 100));
        button5.setPreferredSize(new Dimension(150, 100));
        button6.setPreferredSize(new Dimension(200, 100));
        button7.setPreferredSize(new Dimension(100, 150));
        button8.setPreferredSize(new Dimension(150, 150));
        button9.setPreferredSize(new Dimension(200, 150));

        // JPanel에 버튼을 추가합니다.
        buttonPanel.add(button1);
        buttonPanel.add(button2);
        buttonPanel.add(button3);
        buttonPanel.add(button4);
        buttonPanel.add(button5);
        buttonPanel.add(button6);
        buttonPanel.add(button7);
        buttonPanel.add(button8);
        buttonPanel.add(button9);

        JLabel label = new JLabel("Hotel Management System");
        label.setFont(new Font("Arial", Font.PLAIN, 24)); // 폰트 크기 설정


        JPanel textPanel = new JPanel();
        textPanel.add(label);

        add(textPanel, BorderLayout.NORTH);

        add(buttonPanel);

        setVisible(true);

        button7.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EmptyRoomCheck emptyRoomCheck = new EmptyRoomCheck();
            }
        });
    }



    public static void main(String[] args) {
        new AdminMainPage();
    }
}
