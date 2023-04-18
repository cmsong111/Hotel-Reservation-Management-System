package ds25.hotel.reservation.management.system.pattern.strategy;

import ds25.hotel.reservation.management.system.dto.hotel.HotelRoomDto;

import javax.swing.*;

public interface DialogStrategy {
    public void showDialog(JFrame frame, HotelRoomDto hotelRoomDto);
}
