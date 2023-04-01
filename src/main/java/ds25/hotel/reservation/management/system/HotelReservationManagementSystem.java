/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package ds25.hotel.reservation.management.system;

import ds25.hotel.reservation.management.system.configuration.Singleton;
import ds25.hotel.reservation.management.system.screens.LoginPage;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

/**
 * 메인 클래스
 *
 * @author 김남주
 */
@Slf4j
public class HotelReservationManagementSystem {

    public static void main(String[] args) throws IOException {
        log.info("인스턴스 초기화");
        Singleton.getInstance().init();

        new LoginPage();
    }
}
