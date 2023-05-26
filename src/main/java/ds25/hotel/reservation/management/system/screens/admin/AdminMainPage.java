package ds25.hotel.reservation.management.system.screens.admin;

import ds25.hotel.reservation.management.system.configuration.SpringBridge;
import ds25.hotel.reservation.management.system.dto.hotel.HotelDto;
import ds25.hotel.reservation.management.system.screens.widget.EastPanel;
import ds25.hotel.reservation.management.system.screens.widget.NorthPanel;
import ds25.hotel.reservation.management.system.screens.widget.SouthPanel;
import ds25.hotel.reservation.management.system.screens.widget.WestPanel;
import ds25.hotel.reservation.management.system.service.hotel.HotelService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminMainPage extends JFrame implements ActionListener {

    JButton btn_hotelManage, btn_roomState, btn_reservationManage, btn_back;
    JPanel btn_panel;
    JTextArea textArea;
    HotelDto hotelDto;
    HotelService hotelService;

    /**
     * 기본 생성자
     *
     * @param hotelId 호텔 인덱스 번호
     */
    public AdminMainPage(Long hotelId) {
        hotelService = SpringBridge.getInstance().getBean(HotelService.class);
        hotelDto = hotelService.findHotel(hotelId).get();

        setTitle("관리자 메인 페이지");
        setSize(800, 600);
        setLocationRelativeTo(null);

        setLayout(new BorderLayout());

        JPanel mainPanel = new JPanel(new GridLayout(4, 1, 10, 10));


        btn_panel = new JPanel(new GridLayout(1, 4, 10, 10));
        btn_hotelManage = new JButton("호텔 관리");
        btn_hotelManage.setActionCommand("hotelManage");
        btn_hotelManage.addActionListener(this);

        btn_roomState = new JButton("객실 현황");
        btn_roomState.setActionCommand("roomState");
        btn_roomState.addActionListener(this);

        btn_reservationManage = new JButton("예약 관리");
        btn_reservationManage.setActionCommand("reservationManage");
        btn_reservationManage.addActionListener(this);

        btn_back = new JButton("뒤로 가기");
        btn_back.setActionCommand("back");
        btn_back.addActionListener(this);

        btn_panel.add(btn_hotelManage);
        btn_panel.add(btn_roomState);
        btn_panel.add(btn_reservationManage);
        btn_panel.add(btn_back);

        textArea = new JTextArea();
        textArea.setEditable(true);
        textArea.setText("관리자 페이지 입니다.");
        textArea.setLineWrap(true);

        mainPanel.add(new JPanel());
        mainPanel.add(btn_panel);
        mainPanel.add(textArea);
        mainPanel.add(new JPanel());


        add(mainPanel, BorderLayout.CENTER);
        add(new NorthPanel(), BorderLayout.NORTH);
        add(new WestPanel(), BorderLayout.WEST);
        add(new EastPanel(), BorderLayout.EAST);
        add(new SouthPanel(), BorderLayout.SOUTH);

        setLocationRelativeTo(null);
        setVisible(true);

    }


    public static void main(String[] args) {
        // 서울 호텔의 인덱스 번호는 1입니다.
        new AdminMainPage(1L);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        switch (command) {
            case "hotelManage" -> new HotelManagePage(hotelDto.getIdx());
            case "roomState" -> new AdminHotelRoomStatusPage(hotelDto.getIdx());
            case "reservationManage" -> new HotelReservationListPage(hotelDto.getIdx());
            case "back" -> {
                new AdminHotelSelectPage();
                dispose();
            }
            default -> throw new IllegalStateException("Unexpected value: " + command);
        }
    }
}
