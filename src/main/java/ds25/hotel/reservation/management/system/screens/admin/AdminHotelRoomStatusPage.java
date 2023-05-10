package ds25.hotel.reservation.management.system.screens.admin;

import ds25.hotel.reservation.management.system.configuration.SpringBridge;
import ds25.hotel.reservation.management.system.screens.widget.EastPanel;
import ds25.hotel.reservation.management.system.screens.widget.NorthPanel;
import ds25.hotel.reservation.management.system.screens.widget.SouthPanel;
import ds25.hotel.reservation.management.system.screens.widget.WestPanel;
import ds25.hotel.reservation.management.system.service.hotel.HotelRoomService;
import ds25.hotel.reservation.management.system.service.hotel.HotelRoomTypeService;
import lombok.extern.slf4j.Slf4j;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@Slf4j
public class AdminHotelRoomStatusPage extends JFrame implements ActionListener { // 방 10개 띄워놓고 객실현황 확인하는 틀(?)

    private HotelRoomService hotelRoomService;
    private HotelRoomTypeService hotelRoomTypeService;
    private JPanel roomListPanel;

    public AdminHotelRoomStatusPage(long hotelIdx) {
        super("호텔 예약 현황");
        roomListPanel = new JPanel(new GridLayout(-1, 1, 5, 1));

        hotelRoomService = SpringBridge.getInstance().getBean(HotelRoomService.class);
        hotelRoomTypeService = SpringBridge.getInstance().getBean(HotelRoomTypeService.class);


        hotelRoomTypeService.findHotelRoomByHotelIdx(hotelIdx).forEach(hotelRoomTypeDto -> {
            log.info("{}", hotelRoomTypeDto);
            JPanel roomTypePanel = new JPanel(new GridLayout(2, 1));
            JPanel roomButtonPanel = new JPanel(new GridLayout(1, -1, 1, 5));
            roomTypePanel.add(new JLabel(hotelRoomTypeDto.getName()));
            hotelRoomService.findByHotelRoomTypeIdx(hotelRoomTypeDto.getIdx()).forEach(hotelRoom -> {
                JButton button = new JButton(hotelRoom.getRoomNumber().toString() + "호");
                button.addActionListener(this);
                button.setActionCommand(hotelRoom.getIdx().toString());
                roomButtonPanel.add(button);

            });

            roomTypePanel.add(roomButtonPanel);
            roomListPanel.add(roomTypePanel);
        });


        setLayout(new BorderLayout());

        add(new NorthPanel(), BorderLayout.NORTH);
        add(new EastPanel(), BorderLayout.EAST);
        add(new WestPanel(), BorderLayout.WEST);
        add(new SouthPanel(), BorderLayout.SOUTH);
        add(roomListPanel, BorderLayout.CENTER);


        setSize(1200, 1000);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if (command.equals("")) {
            //
        } else {
            Long hotelRoomIdx = Long.parseLong(command);
            new RoomReservationListPage(hotelRoomIdx);
        }

    }
}
