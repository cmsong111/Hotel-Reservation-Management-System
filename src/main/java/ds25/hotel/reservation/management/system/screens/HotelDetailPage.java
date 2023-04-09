package ds25.hotel.reservation.management.system.screens;

import ds25.hotel.reservation.management.system.configuration.SpringBridge;
import ds25.hotel.reservation.management.system.dto.hotel.HotelDto;
import ds25.hotel.reservation.management.system.screens.auth.LoginPage;
import ds25.hotel.reservation.management.system.screens.widget.EastPanel;
import ds25.hotel.reservation.management.system.screens.widget.LoginPanel;
import ds25.hotel.reservation.management.system.screens.widget.NorthPanel;
import ds25.hotel.reservation.management.system.screens.widget.WestPanel;
import ds25.hotel.reservation.management.system.service.hotel.HotelRoomTypeService;
import ds25.hotel.reservation.management.system.service.hotel.HotelService;
import ds25.hotel.reservation.management.system.util.ImageLoader;
import lombok.extern.slf4j.Slf4j;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Optional;

@Slf4j
public class HotelDetailPage extends JFrame implements  ActionListener {
    HotelService hotelService = SpringBridge.getInstance().getBean(HotelService.class);
    HotelRoomTypeService hotelRoomTypeService = SpringBridge.getInstance().getBean(HotelRoomTypeService.class);
    Optional<HotelDto> hotelDto;

    private Panel centerPanel;
    JLabel userLabel, hotelImageLabel;
    JTextField number;
    JTextArea hotelDetailTextArea, hotelRoomTextArea;
    JButton btn_logout = new JButton("로그아웃"), btn_RoomDetail;



    public HotelDetailPage(long hotelIdx) {

        setTitle("DS25 호텔 예약 관리 시스템");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        hotelDto = hotelService.findHotel(hotelIdx);
        if (hotelDto.isEmpty()) {
            JOptionPane.showMessageDialog(null, "존재하지 않는 호텔입니다.");
            return;
        }

        centerPanel = new Panel(new GridLayout(0, 1));

        hotelImageLabel = new JLabel(ImageLoader.getImage(hotelDto.get().getImages().get(0).getImage()));


        btn_logout.addActionListener(this);
        btn_logout.setActionCommand("logout");

        hotelDetailTextArea = new JTextArea(hotelService.findHotel(hotelIdx).toString());
        hotelDetailTextArea.setEditable(true);
        hotelDetailTextArea.setLineWrap(true);

        hotelRoomTextArea = new JTextArea(hotelRoomTypeService.findHotelRoomByHotelIdx(hotelIdx).toString());
        hotelRoomTextArea.setEditable(true);
        hotelRoomTextArea.setLineWrap(true);

        btn_RoomDetail = new JButton("객실 상세보기");
        btn_RoomDetail.addActionListener(this);
        btn_RoomDetail.setActionCommand("roomDetail");

        hotelRoomTextArea = new JTextArea("1");
        hotelRoomTextArea.setEditable(true);


        centerPanel.add(hotelImageLabel);
        centerPanel.add(hotelDetailTextArea);
        centerPanel.add(hotelRoomTextArea);
        centerPanel.add(btn_RoomDetail);
        centerPanel.add(btn_logout);


        add(centerPanel, BorderLayout.CENTER);
        add(new NorthPanel(), BorderLayout.NORTH);
        add(new WestPanel(), BorderLayout.WEST);
        add(new EastPanel(), BorderLayout.EAST);
        add(new LoginPanel(), BorderLayout.SOUTH);

        setSize(600, 800);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if (command.equals("logout")) {
            log.info("로그아웃 버튼 클릭");
            this.dispose();
            new LoginPage();
        }
        if (command.equals("roomDetail")) {
            log.info("객실 상세보기 버튼 클릭");
            if (hotelRoomTextArea.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "객실 번호를 입력해주세요.");
                return;
            }
            new HotelRoomDetailPage(Long.valueOf(hotelRoomTextArea.getText()));
        }
    }
}
