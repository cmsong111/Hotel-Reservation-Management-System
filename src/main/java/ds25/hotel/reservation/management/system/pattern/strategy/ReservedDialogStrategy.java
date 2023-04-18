package ds25.hotel.reservation.management.system.pattern.strategy;

import ds25.hotel.reservation.management.system.dto.hotel.HotelRoomDto;

import javax.swing.*;

public class ReservedDialogStrategy implements DialogStrategy {
    public void showDialog(JFrame frame, HotelRoomDto hotelRoomDto) {
        JOptionPane.showMessageDialog(frame, "해당 방은 사용 중입니다.");
    }
}
