package ds25.hotel.reservation.management.system.screens.admin;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class ReservationEditForm extends JFrame { // 리스트에서 바로 수정하는거 생각중
    private JLabel nameLabel, phoneLabel, dateLabel, timeLabel;
    private JTextField nameTextField, phoneTextField, dateTextField, timeTextField;
    private JButton saveButton;
    private int reservationId;

    public ReservationEditForm(int id) {
        // 프레임 설정
        setTitle("Edit Reservation");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // 레이블 생성
        nameLabel = new JLabel("Name:");
        phoneLabel = new JLabel("Phone:");
        dateLabel = new JLabel("Date:");
        timeLabel = new JLabel("Time:");

        // 텍스트 필드 생성
        nameTextField = new JTextField(20);
        phoneTextField = new JTextField(20);
        dateTextField = new JTextField(20);
        timeTextField = new JTextField(20);

        // 저장 버튼 생성
        saveButton = new JButton("Save");
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateReservation();
            }
        });

        // 컴포넌트를 패널에 추가
        JPanel panel = new JPanel(new GridLayout(5, 2));
        panel.add(nameLabel);
        panel.add(nameTextField);
        panel.add(phoneLabel);
        panel.add(phoneTextField);
        panel.add(dateLabel);
        panel.add(dateTextField);
        panel.add(timeLabel);
        panel.add(timeTextField);
        panel.add(new JLabel(""));
        panel.add(saveButton);

        // 프레임에 패널 추가
        add(panel);
        setVisible(true);

        // DB에서 예약 정보 가져오기
        reservationId = id;
        getReservationData();
    }

    // DB에서 예약 정보 가져오기
    private void getReservationData() {
        try {
            // JDBC 드라이버 로드
            Class.forName("com.mysql.cj.jdbc.Driver");

            // DB 연결
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/mydatabase", "root", "password");

            // 쿼리문 실행
            Statement stmt = con.createStatement();
            String query = "SELECT * FROM reservations WHERE id=" + reservationId;
            ResultSet rs = stmt.executeQuery(query);

            // 결과값을 텍스트 필드에 설정
            if (rs.next()) {
                nameTextField.setText(rs.getString("name"));
                phoneTextField.setText(rs.getString("phone"));
                dateTextField.setText(rs.getString("date"));
                timeTextField.setText(rs.getString("time"));
            }
            // DB 연결 해제
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    // 예약 정보 업데이트
    private void updateReservation() {
        try {
            // JDBC 드라이버 로드
            Class.forName("com.mysql.cj.jdbc.Driver");

            // DB 연결
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/mydatabase", "root", "password");

            // 쿼리문 실행
            Statement stmt = con.createStatement();
            String query = "UPDATE reservations SET name='" + nameTextField.getText() +
                    "', phone='" + phoneTextField.getText() +
                    "', date='" + dateTextField.getText() +
                    "', time='" + timeTextField.getText() +
                    "' WHERE id=" + reservationId;
            int result = stmt.executeUpdate(query);

            // 업데이트 결과 확인
            if (result > 0) {
                JOptionPane.showMessageDialog(this, "Reservation updated successfully!");
            } else {
                JOptionPane.showMessageDialog(this, "Failed to update reservation.");
            }

            // DB 연결 해제
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}