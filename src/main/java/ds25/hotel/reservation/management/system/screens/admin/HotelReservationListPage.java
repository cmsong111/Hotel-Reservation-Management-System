package ds25.hotel.reservation.management.system.screens.admin;

import ds25.hotel.reservation.management.system.global.configuration.Singleton;
import ds25.hotel.reservation.management.system.global.configuration.SpringBridge;
import ds25.hotel.reservation.management.system.dto.hotel.HotelReservationDto;
import ds25.hotel.reservation.management.system.pattern.observer.Observable;
import ds25.hotel.reservation.management.system.pattern.observer.Observer;
import ds25.hotel.reservation.management.system.provider.HotelReservationProvider;
import ds25.hotel.reservation.management.system.screens.widget.NorthPanel;
import ds25.hotel.reservation.management.system.service.hotel.HotelReservationService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


public class HotelReservationListPage  extends JFrame implements ActionListener , Observer {



    HotelReservationProvider hotelReservationProvider = Singleton.getInstance().getHotelReservationProvider();




    HotelReservationService hotelReservationService;
    java.util.List<HotelReservationDto> hotelReservationDtoArrayList = new ArrayList<>();
    private JTable reservationTable;
    private DefaultTableModel tableModel;
    private Long hotelIdx;
    JButton btn_reload, btn_back;
    JPanel southPanel;
    public HotelReservationListPage(Long id){
        hotelReservationProvider.registerObserver(this);
        hotelReservationService = SpringBridge.getInstance().getBean(HotelReservationService.class);

        hotelReservationDtoArrayList = hotelReservationService.getHotelReservationByHotelId(id);
        hotelIdx = id;
        setTitle("예약 관리");
        setSize(1000, 600);
        setLocationRelativeTo(null);

        setLayout(new BorderLayout());

        southPanel = new JPanel();

        // Create table model and set column names
        String[] columnNames = {"Reservation ID", "User ID", "Hotel ID", "Room Type ID", "Room ID", "Check-in Date", "Check-out Date", "People Count", "Total Price"};
        tableModel = new DefaultTableModel(columnNames, 0);

        // Populate table data
        for (HotelReservationDto reservationDto : hotelReservationDtoArrayList) {
            Object[] rowData = {
                    reservationDto.getIdx(),
                    reservationDto.getUserId(),
                    reservationDto.getHotelIdx(),
                    reservationDto.getHotelRoomTypeIdx(),
                    reservationDto.getHotelRoomIdx(),
                    reservationDto.getCheckInDate(),
                    reservationDto.getCheckOutDate(),
                    reservationDto.getPeopleCount(),
                    reservationDto.getTotalPrice()
            };
            tableModel.addRow(rowData);
        }

        // Create JTable with table model
        reservationTable = new JTable(tableModel);

        // Add table to scroll pane
        JScrollPane tableScrollPane = new JScrollPane(reservationTable);

        // Add scroll pane to main panel
        //mainPanel.add(tableScrollPane, BorderLayout.CENTER);
        southPanel.setLayout(new BorderLayout());
        btn_reload = new JButton("새로고침");
        btn_reload.setActionCommand("reload");
        btn_reload.addActionListener(this);
        btn_back = new JButton("뒤로 가기");
        btn_back.setActionCommand("back");
        btn_back.addActionListener(this);
        southPanel.add(btn_reload, BorderLayout.WEST);
        southPanel.add(btn_back, BorderLayout.EAST);
        JLabel label = new JLabel("© DS25 Team, Hotel Reservation Management System. All rights reserved.");

        southPanel.add(label, BorderLayout.CENTER);


        add(southPanel, BorderLayout.SOUTH);
        add(new NorthPanel(), BorderLayout.NORTH);


        add(tableScrollPane, BorderLayout.CENTER);

        setVisible(true);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        switch (command){
            case "reload":{
                hotelReservationProvider.removeObserver(this);
                dispose();
                new HotelReservationListPage(hotelIdx);
            }
        }

    }


    @Override
    public void update(Observable o, Object arg) {
        hotelReservationProvider.removeObserver(this);
        dispose();
        new HotelReservationListPage(hotelIdx);
    }
}
