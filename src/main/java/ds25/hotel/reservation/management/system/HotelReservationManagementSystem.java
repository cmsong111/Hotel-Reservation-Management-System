/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package ds25.hotel.reservation.management.system;

import ds25.hotel.reservation.management.system.configuration.Singleton;
import ds25.hotel.reservation.management.system.screens.auth.LoginPage;
import lombok.extern.slf4j.Slf4j;

/**
 * 메인 클래스
 *
 * @author 김남주
 */
@Slf4j
public class HotelReservationManagementSystem {

    public static void main(String[] args) {
        Singleton.getInstance();
        new LoginPage();
    }
}
