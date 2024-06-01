package ds25.hotel.reservation.management.system.ui.admin;


import ds25.hotel.reservation.management.system.di.DiContext;
import ds25.hotel.reservation.management.system.domain.hotel.application.HotelRoomService;
import ds25.hotel.reservation.management.system.domain.hotel.application.HotelRoomTypeService;
import ds25.hotel.reservation.management.system.ui.dto.hotel.HotelRoomDto;
import java.awt.*;
import javax.swing.*;
import lombok.extern.slf4j.Slf4j;

@Slf4j

public class EmptyRoomCheck extends JFrame {
    private HotelRoomTypeService hotelRoomTypeService;
    private HotelRoomDto hotelRoomDto;
    private HotelRoomService hotelRoomService;
    private JButton[] roomButtons;

    public EmptyRoomCheck() {
        setTitle("빈 방 조회");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        HotelRoomDto hotelRoomDto1 = new HotelRoomDto();
        hotelRoomService = DiContext.getInstance().getComponent(HotelRoomService.class);
        hotelRoomTypeService = DiContext.getInstance().getComponent(HotelRoomTypeService.class);

        //hotelRoomDto = SpringBridge.getInstance().getBean(HotelRoomDto.class);

        //hotelRoomDto = hotelRoomDto.getRoomNumber();// DB에서 객실 정보를 가져오는거 해야됨
        int numberOfRooms = Math.toIntExact(hotelRoomDto.getRoomNumber()); // 객실 개수 가져와야됨

        roomButtons = new JButton[numberOfRooms]; // 객실 개수만큼 버튼 생성
        for (int i = 0; i < numberOfRooms; i++) {
            roomButtons[i] = new JButton("객실 " + (i+1));
            roomButtons[i].setPreferredSize(new Dimension(120, 100));
        }

        roomButtons = new JButton[numberOfRooms];
        for (int i = 0; i < numberOfRooms; i++) {
            roomButtons[i] = new JButton("객실 " + (i+1));
            roomButtons[i].setPreferredSize(new Dimension(120, 100));
        }

        // 버튼을 담을 패널 생성
        JPanel buttonPanel = new JPanel(new GridLayout(2, 5, 10, 10)); // 2행 5열 그리드 레이아웃
        for (int i = 0; i < numberOfRooms; i++) {
            buttonPanel.add(roomButtons[i]);
        }


        // 컴포넌트들을 프레임에 추가
        add(buttonPanel, BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

}
