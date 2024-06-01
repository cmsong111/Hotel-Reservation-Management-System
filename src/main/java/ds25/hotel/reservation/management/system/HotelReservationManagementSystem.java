/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package ds25.hotel.reservation.management.system;

import ds25.hotel.reservation.management.system.config.AppConfig;
import ds25.hotel.reservation.management.system.di.DiContext;
import ds25.hotel.reservation.management.system.ui.auth.LoginPage;
import lombok.extern.slf4j.Slf4j;

/**
 * 메인 클래스
 *
 * @author 김남주
 */
@Slf4j
public class HotelReservationManagementSystem {

	public static void main(String[] args) {
		// 1. DI 컨테이너 초기화 및 종료 훅 등록
		final DiContext context = DiContext.getInstance();
		Runtime.getRuntime().addShutdownHook(new Thread(() -> {
			log.info("애플리케이션 종료 중...");
			context.close();
		}));

		AppConfig.getInstance();
		new LoginPage();
	}
}
