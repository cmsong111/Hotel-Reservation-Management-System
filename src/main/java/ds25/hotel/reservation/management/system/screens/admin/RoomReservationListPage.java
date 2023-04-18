package ds25.hotel.reservation.management.system.screens.admin;

import ds25.hotel.reservation.management.system.configuration.SpringBridge;
import ds25.hotel.reservation.management.system.screens.widget.EastPanel;
import ds25.hotel.reservation.management.system.screens.widget.NorthPanel;
import ds25.hotel.reservation.management.system.screens.widget.SouthPanel;
import ds25.hotel.reservation.management.system.screens.widget.WestPanel;
import ds25.hotel.reservation.management.system.service.hotel.HotelReservationService;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RoomReservationListPage extends JFrame implements ActionListener, ListSelectionListener { // ReservationPage 버튼 누르면 나오는 페이지로 생각중


    private JList hotelList;
    private JScrollPane reservationListScrollPane;
    private JPanel reservationListPanel;
    private DefaultListModel reservationListModel;
    private HotelReservationService hotelReservationService;

    public RoomReservationListPage(Long hotelRoomIdx) {
        hotelReservationService = SpringBridge.getInstance().getBean(HotelReservationService.class);

        reservationListModel = new DefaultListModel();
        hotelList = new JList(reservationListModel);
        hotelList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        hotelList.addListSelectionListener(this);

        reservationListScrollPane = new JScrollPane(hotelList);
        reservationListScrollPane.setPreferredSize(new Dimension(250, 80));

        hotelReservationService.getHotelReservationByHotelRoomIdx(hotelRoomIdx).forEach(hotelReservationDto -> {
            reservationListModel.addElement(hotelReservationDto);
        });

        setTitle("예약 목록");
        setSize(1000, 800);


        setLayout(new BorderLayout());

        add(reservationListScrollPane, BorderLayout.CENTER);
        add(new NorthPanel(hotelRoomIdx.toString() + "객실"), BorderLayout.NORTH);
        add(new WestPanel(), BorderLayout.WEST);
        add(new SouthPanel(), BorderLayout.SOUTH);
        add(new EastPanel(), BorderLayout.EAST);

        setVisible(true);


    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void valueChanged(ListSelectionEvent e) {

    }
}
