package ds25.hotel.reservation.management.system.ui.admin;

import ds25.hotel.reservation.management.system.di.DiContext;
import ds25.hotel.reservation.management.system.domain.hotel.application.HotelRoomService;
import ds25.hotel.reservation.management.system.domain.hotel.application.HotelRoomTypeService;
import ds25.hotel.reservation.management.system.ui.widget.EastPanel;
import ds25.hotel.reservation.management.system.ui.widget.NorthPanel;
import ds25.hotel.reservation.management.system.ui.widget.SouthPanel;
import ds25.hotel.reservation.management.system.ui.widget.WestPanel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AdminHotelRoomStatusPage extends JFrame implements ActionListener { // 방 10개 띄워놓고 객실현황 확인하는 틀(?)

    private HotelRoomService hotelRoomService;
    private HotelRoomTypeService hotelRoomTypeService;
    private JPanel roomListPanel;

    public AdminHotelRoomStatusPage(long hotelIdx) {
        super("호텔 예약 현황");
        roomListPanel = new JPanel(new GridLayout(-1, 1, 5, 1));

        hotelRoomService = DiContext.getInstance().getComponent(HotelRoomService.class);
        hotelRoomTypeService = DiContext.getInstance().getComponent(HotelRoomTypeService.class);


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
