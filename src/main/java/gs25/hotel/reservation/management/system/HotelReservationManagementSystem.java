/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package gs25.hotel.reservation.management.system;

import gs25.hotel.reservation.management.system.configuration.Singleton;

import java.io.IOException;

/**
 * 메인 클래스
 * @author 김남주
 */
public class HotelReservationManagementSystem {

    public static void main(String[] args) throws IOException {


        Singleton.getInstance().init();



        System.out.println("Hello World!");

    }
}
