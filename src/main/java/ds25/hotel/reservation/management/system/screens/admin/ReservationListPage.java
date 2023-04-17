package ds25.hotel.reservation.management.system.screens.admin;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class ReservationListPage extends JFrame { // ReservationPage 버튼 누르면 나오는 페이지로 생각중
    JTable table;
    JScrollPane scrollPane;

    public ReservationListPage() {
        setTitle("예약 리스트");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        String[] columnNames = {"ID", "Name", "Phone", "Date"};
        // Object[][] rowData = getReservationData();
        // table = new JTable(rowData, columnNames);


        scrollPane = new JScrollPane(table);


        add(scrollPane, BorderLayout.CENTER);
        setVisible(true);
    }

//    // DB에서 예약 데이터 가져오기
//    private Object[][] getReservationData() {
//        Object[][] rowData = null;
//
//        try {
//            // JDBC 드라이버 로드
//            Class.forName("com.mysql.cj.jdbc.Driver");
//
//            // DB 연결
//            Connection con = DriverManager.getConnection(
//                    "jdbc:mysql://localhost:3306/mydatabase", "root", "password");
//
//            // 쿼리문 실행
//            Statement stmt = con.createStatement();
//            String query = "SELECT * FROM reservations";
//            ResultSet rs = stmt.executeQuery(query);
//
//            // 결과값을 2차원 배열로 변환
//            int rowCount = getRowCount(rs);
//            int columnCount = getColumnCount(rs);
//            rowData = new Object[rowCount][columnCount];
//
//            int i = 0;
//            while (rs.next()) {
//                rowData[i][0] = rs.getInt("id");
//                rowData[i][1] = rs.getString("name");
//                rowData[i][2] = rs.getString("phone");
//                rowData[i][3] = rs.getString("date");
//                rowData[i][4] = rs.getString("time");
//                i++;
//            }
//
//            // DB 연결 해제
//            rs.close();
//            stmt.close();
//            con.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        return rowData;
//    }
//
//    // 결과값의 행 개수 가져오기
//    private int getRowCount(ResultSet rs) throws SQLException {
//        rs.last();
//        int rowCount = rs.getRow();
//        rs.beforeFirst();
//        return rowCount;
//    }
//
//    // 결과값의 열 개수 가져오기
//    private int getColumnCount(ResultSet rs) throws SQLException {
//        ResultSetMetaData metaData = rs.getMetaData();
//        return metaData.getColumnCount();
//    }

    public static void main(String[] args) {
        new ReservationListPage();
    }
}