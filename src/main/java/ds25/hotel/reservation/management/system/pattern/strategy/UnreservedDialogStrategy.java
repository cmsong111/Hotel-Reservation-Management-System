package ds25.hotel.reservation.management.system.pattern.strategy;

import ds25.hotel.reservation.management.system.dto.hotel.HotelRoomDto;

import javax.swing.*;

public class UnreservedDialogStrategy implements DialogStrategy {
    public void showDialog(JFrame frame, HotelRoomDto hotelRoomDto) {
        int result = JOptionPane.showConfirmDialog(frame, "해당 방을 예약하시겠습니까?");

    }
}
