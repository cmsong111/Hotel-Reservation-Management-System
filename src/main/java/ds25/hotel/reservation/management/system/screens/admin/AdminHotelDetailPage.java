package ds25.hotel.reservation.management.system.screens.admin;

import ds25.hotel.reservation.management.system.configuration.SpringBridge;
import ds25.hotel.reservation.management.system.dto.hotel.HotelRoomDto;
import ds25.hotel.reservation.management.system.dto.hotel.HotelRoomTypeDto;
import ds25.hotel.reservation.management.system.screens.widget.EastPanel;
import ds25.hotel.reservation.management.system.screens.widget.NorthPanel;
import ds25.hotel.reservation.management.system.screens.widget.SouthPanel;
import ds25.hotel.reservation.management.system.screens.widget.WestPanel;
import ds25.hotel.reservation.management.system.service.hotel.HotelRoomService;
import ds25.hotel.reservation.management.system.service.hotel.HotelRoomTypeService;
import lombok.extern.slf4j.Slf4j;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

@Slf4j
public class AdminHotelDetailPage extends JFrame implements ActionListener { // 방 10개 띄워놓고 객실현황 확인하는 틀(?)

    private HotelRoomService hotelRoomService;
    private HotelRoomTypeService hotelRoomTypeService;
    private JPanel roomListPanel;

    public AdminHotelDetailPage(long hotelIdx) {
        super("호텔 예약 현황");
        roomListPanel = new JPanel(new GridLayout(-1, 1));

        hotelRoomService = SpringBridge.getInstance().getBean(HotelRoomService.class);
        hotelRoomTypeService = SpringBridge.getInstance().getBean(HotelRoomTypeService.class);


        hotelRoomTypeService.findHotelRoomByHotelIdx(hotelIdx).forEach(hotelRoomTypeDto -> {
            log.info("{}", hotelRoomTypeDto);
            roomListPanel.add(new HotelRoomTypePanel(
                    hotelRoomTypeDto,
                    hotelRoomService.findByHotelTypeIdx(hotelRoomTypeDto.getIdx()
                    )
            ));
        });


        setLayout(new BorderLayout());

        add(new NorthPanel(), BorderLayout.NORTH);
        add(new EastPanel(), BorderLayout.EAST);
        add(new WestPanel(), BorderLayout.WEST);
        add(new SouthPanel(), BorderLayout.SOUTH);
        add(roomListPanel, BorderLayout.CENTER);


        setSize(1200, 1000);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if (command.equals("")) {
            //
        } else {
            log.info("{}", command);
        }

    }

}

@Slf4j
class HotelRoomTypePanel extends JPanel  {
    public HotelRoomTypePanel(HotelRoomTypeDto hotelRoomTypeDto, List<HotelRoomDto> hotelRoomDtoList) {
        JPanel roomPanel = new JPanel(new GridLayout(1, -1));
        for (HotelRoomDto hotelRoomDto : hotelRoomDtoList) {
            roomPanel.add(new JButton(
                    hotelRoomDto.getRoomNumber().toString() + "호")
            );
        }

        setLayout(new GridLayout(2, 1));
        add(new JLabel(hotelRoomTypeDto.getName()));
        add(roomPanel);
    }

}
