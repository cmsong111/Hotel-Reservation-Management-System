//package ds25.hotel.reservation.management.system.configuration;
//
//import ds25.hotel.reservation.management.system.service.hotel.HotelReviewService;
//import ds25.hotel.reservation.management.system.service.hotel.HotelRoomService;
//import ds25.hotel.reservation.management.system.service.hotel.HotelService;
//import ds25.hotel.reservation.management.system.service.user.UserService;
//import jakarta.annotation.PostConstruct;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//@Component
//@Slf4j
//public class DataInit {
//    HotelService hotelService;
//    HotelReviewService hotelReviewService;
//    HotelRoomService hotelRoomService;
//    UserService userService;
//
//    @Autowired
//    public DataInit(HotelService hotelService, HotelReviewService hotelReviewService, HotelRoomService hotelRoomService, UserService userService) {
//        this.hotelService = hotelService;
//        this.hotelReviewService = hotelReviewService;
//        this.hotelRoomService = hotelRoomService;
//        this.userService = userService;
//    }
//
//    @PostConstruct
//    public void init() {
//        log.info("데이터 초기화 시작");
//        // 사용자 데이터 초기화
//        userService.initUserData();
//        // 호텔 데이터 초기화
//        hotelService.initHotelData();
//        // 호텔 방 데이터 초기화
////        hotelRoomService.initHotelRoomData();
//
//        // 호텔 리뷰 데이터 초기화
////        hotelReviewService.initHotelReviewData();
////
//
//
//    }
//}
