package ds25.hotel.reservation.management.system.screens;

import ds25.hotel.reservation.management.system.configuration.Singleton;
import ds25.hotel.reservation.management.system.configuration.SpringBridge;
import ds25.hotel.reservation.management.system.dto.hotel.HotelReservationDto;
import ds25.hotel.reservation.management.system.dto.hotel.HotelRoomTypeDto;
import ds25.hotel.reservation.management.system.dto.user.UserDto;
import ds25.hotel.reservation.management.system.pattern.facoryMethod.HotelReservationFactoryMethod;
import ds25.hotel.reservation.management.system.screens.widget.EastPanel;
import ds25.hotel.reservation.management.system.screens.widget.NorthPanel;
import ds25.hotel.reservation.management.system.screens.widget.SouthPanel;
import ds25.hotel.reservation.management.system.screens.widget.WestPanel;
import ds25.hotel.reservation.management.system.service.hotel.HotelReservationService;
import ds25.hotel.reservation.management.system.service.hotel.HotelRoomTypeService;
import ds25.hotel.reservation.management.system.service.user.UserService;
import org.jdesktop.swingx.JXDatePicker;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Optional;

public class HotelReservationPage extends JFrame implements ActionListener {

    UserDto user;
    HotelReservationDto hotelReservationDto;

    UserService userService;
    HotelRoomTypeService hotelRoomTypeService;
    HotelReservationService hotelReservationService;


    private JLabel titleLabel, checkInLabel, checkOutLabel, roomTypeLabel, numGuestsLabel, numRoomsLabel;

    private JComboBox<String> roomTypeComboBox;
    private JSpinner numGuestsSpinner, numRoomsSpinner;
    private JXDatePicker checkInDatePicker, checkOutDatePicker;

    private JButton reserveButton, cancelButton;


    Optional<HotelRoomTypeDto> hotelRoomDto;

    public HotelReservationPage(Long hotelRoomIdx) {

        userService = SpringBridge.getInstance().getBean(UserService.class);
        hotelRoomTypeService = SpringBridge.getInstance().getBean(HotelRoomTypeService.class);
        hotelReservationService = SpringBridge.getInstance().getBean(HotelReservationService.class);

        user = Singleton.getInstance().getUser();
        hotelReservationDto = HotelReservationFactoryMethod.createReservation(hotelRoomIdx);
        hotelRoomDto = hotelRoomTypeService.findHotelRoomByIdx(hotelRoomIdx);

        if (user == null || hotelRoomDto.isEmpty()) {
            JOptionPane.showMessageDialog(null, "로그인이 필요합니다.");
            return;
        }


        // Add the title label to the top of the form
        titleLabel = new JLabel("Hotel Reservation Form");
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        add(titleLabel, BorderLayout.NORTH);

        // Add the form fields to the center of the form
        JPanel formPanel = new JPanel(new GridLayout(10, 2, 10, 10));
        checkInLabel = new JLabel("체크인 날짜");
        checkInDatePicker = new JXDatePicker();
        checkInDatePicker.setDate(getCurrentDate().getTime());
        checkInDatePicker.setFormats(new SimpleDateFormat("yyyy-MM-dd"));

        checkOutLabel = new JLabel("체크아웃 날짜");
        checkOutDatePicker = new JXDatePicker();
        checkOutDatePicker.setDate(getTomorrowDate().getTime());
        checkOutDatePicker.setFormats(new SimpleDateFormat("yyyy-MM-dd"));
        roomTypeLabel = new JLabel("방 유형");
        roomTypeComboBox = new JComboBox<>(new String[]{"Single", "Double", "Suite"});
        numGuestsLabel = new JLabel("Number of Guests: ");
        numGuestsSpinner = new JSpinner(new SpinnerNumberModel(1, 1, 10, 1));
        numRoomsLabel = new JLabel("Number of Rooms: ");
        numRoomsSpinner = new JSpinner(new SpinnerNumberModel(1, 1, 5, 1));

        reserveButton = new JButton("Reserve");
        cancelButton = new JButton("Cancel");

        reserveButton.addActionListener(this);
        cancelButton.addActionListener(this);

        formPanel.add(new JLabel());
        formPanel.add(new JLabel());
        formPanel.add(checkInLabel);
        formPanel.add(checkInDatePicker);
        formPanel.add(checkOutLabel);
        formPanel.add(checkOutDatePicker);
        formPanel.add(roomTypeLabel);
        formPanel.add(roomTypeComboBox);
        formPanel.add(numGuestsLabel);
        formPanel.add(numGuestsSpinner);
        formPanel.add(numRoomsLabel);
        formPanel.add(numRoomsSpinner);
        formPanel.add(new JLabel());
        formPanel.add(new JLabel());
        formPanel.add(new JLabel());
        formPanel.add(new JLabel());


        formPanel.add(reserveButton);
        formPanel.add(cancelButton);

        add(formPanel, BorderLayout.CENTER);
        add(new NorthPanel(), BorderLayout.NORTH);
        add(new SouthPanel(), BorderLayout.SOUTH);
        add(new WestPanel(), BorderLayout.WEST);
        add(new EastPanel(), BorderLayout.EAST);


        setTitle("Hotel Reservation Page");
        setSize(600, 800);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private Calendar getCurrentDate() {
        return Calendar.getInstance();
    }

    private Calendar getTomorrowDate() {
        Calendar tomorrow = Calendar.getInstance();
        tomorrow.add(Calendar.DATE, 1);
        return tomorrow;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if (command.equals("Reserve")) {
            hotelReservationDto.setCheckInDate(new Timestamp(checkInDatePicker.getDate().getTime()));
            hotelReservationDto.setCheckOutDate(new Timestamp(checkOutDatePicker.getDate().getTime()));
            hotelReservationDto.setPeopleCount((Integer) numGuestsSpinner.getValue());

            hotelReservationService.addHotelReservation(hotelReservationDto);
            this.dispose();

        } else if (command.equals("Cancel")) {
            this.dispose();
        }
    }
}
