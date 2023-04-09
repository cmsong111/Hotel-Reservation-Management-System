package ds25.hotel.reservation.management.system.configuration;

import ds25.hotel.reservation.management.system.entity.hotel.*;
import ds25.hotel.reservation.management.system.entity.user.User;
import ds25.hotel.reservation.management.system.entity.user.UserGrade;
import ds25.hotel.reservation.management.system.entity.user.UserRole;
import ds25.hotel.reservation.management.system.service.hotel.HotelRoomService;
import ds25.hotel.reservation.management.system.service.hotel.*;
import ds25.hotel.reservation.management.system.service.user.UserService;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
@Slf4j
public class DataInit {
    HotelService hotelService;
    HotelReviewService hotelReviewService;
    HotelRoomTypeService hotelRoomTypeService;
    HotelReservationService hotelReservationService;
    HotelImageService hotelImageService;
    HotelRoomService hotelRoomService;
    UserService userService;


    @Autowired
    public DataInit(HotelImageService hotelImageService, HotelReservationService hotelReservationService, HotelService hotelService, HotelReviewService hotelReviewService, HotelRoomTypeService hotelRoomTypeService, UserService userService, HotelRoomService hotelRoomService) {
        this.hotelService = hotelService;
        this.hotelReviewService = hotelReviewService;
        this.hotelRoomTypeService = hotelRoomTypeService;
        this.userService = userService;
        this.hotelReservationService = hotelReservationService;
        this.hotelImageService = hotelImageService;
        this.hotelRoomService = hotelRoomService;
    }

    @PostConstruct
    public void init() {
        log.info("데이터 초기화 시작");
        // 사용자 데이터 초기화
        userService.initUserData(users);
        // 호텔 이미지 데이터 초기화
        hotelImageService.initHotelImageData(hotelImages);
        // 호텔 데이터 초기화
        hotelService.initHotelData(hotels);
        // 호텔 방 데이터 초기화
        hotelRoomTypeService.initHotelRoomData(hotelRoomTypes);
        // 호텔 방 객실 데이터 초기화
        hotelRoomService.initHotelRoomData(getHotelRooms());
        // 호텔 예약 데이터 초기화
        hotelReservationService.initHotelReservationData(hotelReservations);
        // 호텔 리뷰 데이터 초기화
        hotelReviewService.initHotelReviewData(hotelReviews);
    }

    List<User> users = new ArrayList<>(Arrays.asList(
            User.builder()
                    .id("admin")
                    .password("admin")
                    .name("관리자")
                    .phone("010-0000-0000")
                    .email("admin@hotel.com")
                    .grade(UserGrade.GOLD)
                    .role(UserRole.ADMIN)
                    .build(),
            User.builder()
                    .id("user")
                    .password("user")
                    .name("일반회원")
                    .phone("010-0000-0000")
                    .email("user@hotel.com")
                    .grade(UserGrade.SILVER)
                    .role(UserRole.USER)
                    .build()
    ));

    List<Hotel> hotels = new ArrayList<>(Arrays.asList(
            Hotel.builder()
                    .name("시그니엘 서울")
                    .address("서울특별시 송파구 올림픽로 300")
                    .phone("+82-2-3213-1000")
                    .email("rsv.signiel.seoul@lotte.net")
                    .description("롯데월드타워 76층~101층에서 즐기는 파노라믹한 스카이라인\n미쉐린 스타 셰프 야닉 알레노의 다이닝과\n세계 최고층에 위치한 웨딩&컨벤션\nLive beyond expectations, SIGNIEL SEOUL")
                    .images(new ArrayList<>(Arrays.asList(
                            HotelImage.builder()
                                    .image("https://www.lottehotel.com/content/dam/lotte-hotel/signiel/seoul/main/230119-01-2000-mai-LTSG.jpg.thumb.1920.1920.jpg")
                                    .build())))
                    .build())
    );
    List<HotelRoomType> hotelRoomTypes = new ArrayList<>(Arrays.asList(
            HotelRoomType.builder()
                    .hotel(Hotel.builder().idx(1L).build())
                    .name("그랜드 디럭스 룸")
                    .description("우아한 인테리어와 현대적 세련미가 조화롭게 어우러진 시그니엘 서울의 그랜드 디럭스 룸은 초고층 객실에서 바라보는 서울 도심의 파노라믹뷰와 최상의 휴식을 제공합니다.")
                    .price(600000)
                    .discount(0)
                    .peopleCount(2)
                    .bedSize(BedSize.DOUBLE)
                    .roomSize(45)
                    .images(new ArrayList<>(Arrays.asList(
                            HotelImage.builder()
                                    .image("https://www.lottehotel.com/content/dam/lotte-hotel/signiel/seoul/accommodation/deluxe/2838-01-2000-roo-LTSG.jpg.thumb.1920.1920.jpg")
                                    .build(),
                            HotelImage.builder()
                                    .image("https://www.lottehotel.com/content/dam/lotte-hotel/signiel/seoul/accommodation/deluxe/2838-02-2000-roo-LTSG.jpg.thumb.1920.1920.jpg")
                                    .build()
                    )))
                    .build(),
            HotelRoomType.builder()
                    .hotel(Hotel.builder().idx(1L).build())
                    .name("프리미어 룸")
                    .description("시그니엘 서울의 프리미어 룸은 세련미와 우아함을 더한 고급스러운 인테리어로 대형 창문을 통해 서울의 화려한 장관을 감상할 수 있습니다.\n또한 이탈리아 북부 알프스 천연 대리석으로 제작된 욕실에서 고품격 휴식을 즐기기에 충분합니다.")
                    .price(800000)
                    .discount(0)
                    .peopleCount(2)
                    .bedSize(BedSize.DOUBLE)
                    .roomSize(55)
                    .images(new ArrayList<>(Arrays.asList(
                            HotelImage.builder()
                                    .image("https://www.lottehotel.com/content/dam/lotte-hotel/signiel/seoul/accommodation/premier/180708-30-2000-acc-seoul-signiel.jpg.thumb.1920.1920.jpg")
                                    .build(),
                            HotelImage.builder()
                                    .image("https://www.lottehotel.com/content/dam/lotte-hotel/signiel/seoul/accommodation/premier/2849-1-2000-roo-LTSG.jpg.thumb.1920.1920.jpg")
                                    .build(),
                            HotelImage.builder()
                                    .image("https://www.lottehotel.com/content/dam/lotte-hotel/signiel/seoul/accommodation/premier/2849-1-2000-roo-LTSG.jpg.thumb.1920.1920.jpg")
                                    .build()
                    )))
                    .build(),
            HotelRoomType.builder()
                    .hotel(Hotel.builder().idx(1L).build())
                    .name("프리미어 스위트 룸")
                    .description("시그니엘 서울의 프리미어 스위트 룸의 넓고 고급스러운 인테리어는 고객의 안락함을 최우선으로 고려하여 설계되었으며 최고의 투숙 경험을 선사합니다.")
                    .price(960000)
                    .discount(0)
                    .peopleCount(2)
                    .bedSize(BedSize.DOUBLE)
                    .roomSize(80)
                    .images(new ArrayList<>(Arrays.asList(
                            HotelImage.builder()
                                    .image("https://www.lottehotel.com/content/dam/lotte-hotel/signiel/seoul/accommodation/deluxe/180708-1-2000-acc-seoul-signiel.jpg.thumb.1920.1920.jpg")
                                    .build(),
                            HotelImage.builder()
                                    .image("https://www.lottehotel.com/content/dam/lotte-hotel/signiel/seoul/accommodation/deluxe/180708-3-2000-acc-seoul-signiel.jpg.thumb.1920.1920.jpg")
                                    .build(),
                            HotelImage.builder()
                                    .image("https://www.lottehotel.com/content/dam/lotte-hotel/signiel/seoul/accommodation/premier/180708-29-2000-acc-seoul-signiel.jpg.thumb.1920.1920.jpg")
                                    .build()
                    )))
                    .build(),
            HotelRoomType.builder()
                    .hotel(Hotel.builder().idx(1L).build())
                    .name("디럭스 스위트 룸")
                    .description("시그니엘 서울의 디럭스 스위트 룸은 일반 객실보다 더욱 넓고 쾌적한 공간을 제공하는 객실입니다.\n침실과 응접실이 분리되어 있어 편안함과 안정된 휴식을 누리실 수 있습니다.")
                    .price(520000)
                    .discount(0)
                    .peopleCount(2)
                    .bedSize(BedSize.DOUBLE)
                    .roomSize(80)
                    .images(new ArrayList<>(Arrays.asList(
                            HotelImage.builder()
                                    .image("https://www.lottehotel.com/content/dam/lotte-hotel/signiel/seoul/accommodation/suite/180708-55-2000-acc-seoul-signiel.jpg.thumb.1920.1920.jpg")
                                    .build(),
                            HotelImage.builder()
                                    .image("https://www.lottehotel.com/content/dam/lotte-hotel/signiel/seoul/accommodation/suite/180708-54-2000-acc-seoul-signiel.jpg.thumb.1920.1920.jpg")
                                    .build(),
                            HotelImage.builder()
                                    .image("https://www.lottehotel.com/content/dam/lotte-hotel/signiel/seoul/accommodation/suite/180708-53-2000-acc-seoul-signiel.jpg.thumb.1920.1920.jpg")
                                    .build(),
                            HotelImage.builder()
                                    .image("https://www.lottehotel.com/content/dam/lotte-hotel/signiel/seoul/accommodation/suite/180708-52-2000-acc-seoul-signiel.jpg.thumb.1920.1920.jpg")
                                    .build()
                    )))
                    .build(),
            HotelRoomType.builder()
                    .hotel(Hotel.builder().idx(1L).build())
                    .name("프리미어 스위트 룸")
                    .description("시그니엘 서울의 프리미어 스위트 룸의 넓고 고급스러운 인테리어는 고객의 안락함을 최우선으로 고려하여 설계되었으며 최고의 투숙 경험을 선사합니다.")
                    .price(520000)
                    .discount(0)
                    .peopleCount(2)
                    .bedSize(BedSize.DOUBLE)
                    .roomSize(80)
                    .images(new ArrayList<>(Arrays.asList(
                            HotelImage.builder()
                                    .image("https://www.lottehotel.com/content/dam/lotte-hotel/signiel/seoul/accommodation/suite/180708-79-2000-acc-seoul-signiel.jpg.thumb.1920.1920.jpg")
                                    .build(),
                            HotelImage.builder()
                                    .image("https://www.lottehotel.com/content/dam/lotte-hotel/signiel/seoul/accommodation/suite/180708-51-2000-acc-seoul-signiel.jpg.thumb.1920.1920.jpg")
                                    .build(),
                            HotelImage.builder()
                                    .image("https://www.lottehotel.com/content/dam/lotte-hotel/signiel/seoul/accommodation/suite/180708-78-2000-acc-seoul-signiel.jpg.thumb.1920.1920.jpg")
                                    .build(),
                            HotelImage.builder()
                                    .image("https://www.lottehotel.com/content/dam/lotte-hotel/signiel/seoul/accommodation/suite/180708-77-2000-acc-seoul-signiel.jpg.thumb.1920.1920.jpg")
                                    .build()
                    )))
                    .build(),
            HotelRoomType.builder()
                    .hotel(Hotel.builder().idx(1L).build())
                    .name("코리안 프리미어 룸")
                    .description("시그니엘 서울의 코리안 프리미어 룸은 92층에 위치하여 최고의 전망을 자랑하는 객실입니다.\n한국 전통미와 현대적인 시설의 조화가 이루어진 최고급 온돌로 구성되어 있으며 한국의 궁을 테마로 한 온돌, 객실 곳곳에 배치된 전통가구와 편백나무로 구성된 욕실은 한국적인 멋을 더욱 돋보이게 합니다.")
                    .price(1000000)
                    .discount(0)
                    .peopleCount(2)
                    .bedSize(BedSize.DOUBLE)
                    .roomSize(40)
                    .images(new ArrayList<>(Arrays.asList(
                            HotelImage.builder()
                                    .image("https://www.lottehotel.com/content/dam/lotte-hotel/signiel/seoul/accommodation/premier/2849-4-2000-roo-LTSG.jpg.thumb.1920.1920.jpg")
                                    .build(),
                            HotelImage.builder()
                                    .image("https://www.lottehotel.com/content/dam/lotte-hotel/signiel/seoul/accommodation/premier/2849-10-2000-roo-LTSG.jpg.thumb.1920.1920.jpg")
                                    .build()
                    )))
                    .build(),
            HotelRoomType.builder()
                    .hotel(Hotel.builder().idx(1L).build())
                    .name("코리안 시그니엘 프리미어 룸")
                    .description("시그니엘 서울의 코리안 시그니엘 프리미어 룸은 92층에 위치하여 최고의 전망을 자랑하는 객실입니다.\n또한, 한국 전통미와 현대적인 시설의 조화가 이루어진 최고급 온돌로 구성되어 있으며 한국의 궁을 테마로 한 온돌, 객실 곳곳에 배치된 전통가구와 편백나무로 구성된 욕실은 한국적인 멋을 더욱 돋보이게 합니다.")
                    .price(600000)
                    .discount(0)
                    .peopleCount(2)
                    .bedSize(BedSize.DOUBLE)
                    .roomSize(40)
                    .images(new ArrayList<>(Arrays.asList(
                            HotelImage.builder()
                                    .image("https://www.lottehotel.com/content/dam/lotte-hotel/signiel/seoul/accommodation/premier/2849-4-2000-roo-LTSG.jpg.thumb.1920.1920.jpg")
                                    .build(),
                            HotelImage.builder()
                                    .image("https://www.lottehotel.com/content/dam/lotte-hotel/signiel/seoul/accommodation/premier/2849-10-2000-roo-LTSG.jpg.thumb.1920.1920.jpg")
                                    .build()
                    )))
                    .build(),
            HotelRoomType.builder()
                    .hotel(Hotel.builder().idx(1L).build())
                    .name("코리안 스위트 룸")
                    .description("시그니엘 서울의 코리안 스위트 룸은 92층에 위치하여 최고의 전망을 자랑하는 객실입니다.\n또한, 한국 전통미와 현대적인 시설의 조화가 이루어진 최고급 온돌로 구성되어 있으며 한국의 궁을 테마로 한 온돌, 객실 곳곳에 배치된 전통가구와 편백나무로 구성된 욕실은 한국적인 멋을 더욱 돋보이게 합니다.")
                    .price(600000)
                    .discount(0)
                    .peopleCount(2)
                    .bedSize(BedSize.DOUBLE)
                    .roomSize(84)
                    .images(new ArrayList<>(Arrays.asList(
                            HotelImage.builder()
                                    .image("https://www.lottehotel.com/content/dam/lotte-hotel/signiel/seoul/accommodation/suite/3293-1-2000-roo-LTSG.jpg.thumb.1920.1920.jpg")
                                    .build(),
                            HotelImage.builder()
                                    .image("https://www.lottehotel.com/content/dam/lotte-hotel/signiel/seoul/accommodation/suite/3293-4-2000-roo-LTSG.jpg.thumb.1920.1920.jpg")
                                    .build(),
                            HotelImage.builder()
                                    .image("https://www.lottehotel.com/content/dam/lotte-hotel/signiel/seoul/accommodation/suite/3293-6-2000-roo-LTSG.jpg.thumb.1920.1920.jpg")
                                    .build(),
                            HotelImage.builder()
                                    .image("https://www.lottehotel.com/content/dam/lotte-hotel/signiel/seoul/accommodation/suite/3293-5-2000-roo-LTSG.jpg.thumb.1920.1920.jpg")
                                    .build(),
                            HotelImage.builder()
                                    .image("https://www.lottehotel.com/content/dam/lotte-hotel/signiel/seoul/accommodation/suite/180708-66-2000-acc-seoul-signiel.jpg.thumb.1920.1920.jpg")
                                    .build()

                    )))
                    .build(),
            HotelRoomType.builder()
                    .hotel(Hotel.builder().idx(1L).build())
                    .name("프레지덴셜 스위트 룸")
                    .description("시그니엘 서울의 프레지덴셜 스위트 룸은 넓은 공간과 세련된 디자인의 응접실 및 다이닝룸을 갖춘 객실입니다.\n대형 창문을 통해 펼쳐지는 아름다운 서울의 전망과 함께 시그니엘 서울만의 세심한 서비스를 느껴보세요.")
                    .price(1000000)
                    .discount(0)
                    .peopleCount(2)
                    .bedSize(BedSize.DOUBLE)
                    .roomSize(143)
                    .images(new ArrayList<>(Arrays.asList(
                            HotelImage.builder()
                                    .image("https://www.lottehotel.com/content/dam/lotte-hotel/signiel/seoul/accommodation/suite/3292-5-2000-roo-LTSG.jpg.thumb.1920.1920.jpg")
                                    .build(),
                            HotelImage.builder()
                                    .image("https://www.lottehotel.com/content/dam/lotte-hotel/signiel/seoul/accommodation/suite/3292-4-2000-roo-LTSG.jpg.thumb.1920.1920.jpg")
                                    .build(),
                            HotelImage.builder()
                                    .image("https://www.lottehotel.com/content/dam/lotte-hotel/signiel/seoul/accommodation/suite/3292-8-2000-roo-LTSG.jpg.thumb.1920.1920.jpg")
                                    .build(),
                            HotelImage.builder()
                                    .image("https://www.lottehotel.com/content/dam/lotte-hotel/signiel/seoul/accommodation/suite/180708-71-2000-acc-seoul-signiel.jpg.thumb.1920.1920.jpg")
                                    .build(),
                            HotelImage.builder()
                                    .image("https://www.lottehotel.com/content/dam/lotte-hotel/signiel/seoul/accommodation/suite/3292-2-2000-roo-LTSG.jpg.thumb.1920.1920.jpg")
                                    .build()
                    )))
                    .build(),
            HotelRoomType.builder()
                    .hotel(Hotel.builder().idx(1L).build())
                    .name("로얄 스위트 룸")
                    .description("시그니엘 서울의 100층에 위치한 로얄스위트 룸은 세계 각국의 국빈과 국내외 VVIP를 위한 시그니엘 서울의 단 하나뿐인 최고급 객실입니다.\n럭셔리한 응접실과 회의실은 물론 비서관 전용 객실, 자쿠지 등 최신 시설과 보안 시스템을 갖추고 있으며 시그니엘 서울만의 세심하고 격조 높은 서비스는 잊지 못할 감동을 선사합니다.")
                    .price(2500000)
                    .discount(0)
                    .peopleCount(2)
                    .bedSize(BedSize.DOUBLE)
                    .roomSize(353)
                    .images(new ArrayList<>(Arrays.asList(
                            HotelImage.builder()
                                    .image("https://www.lottehotel.com/content/dam/lotte-hotel/signiel/seoul/accommodation/suite/3291-2-2000-roo-LTSG.jpg.thumb.1920.1920.jpg")
                                    .build(),
                            HotelImage.builder()
                                    .image("https://www.lottehotel.com/content/dam/lotte-hotel/signiel/seoul/accommodation/suite/3291-9-2000-roo-LTSG.jpg.thumb.1920.1920.jpg")
                                    .build(),
                            HotelImage.builder()
                                    .image("https://www.lottehotel.com/content/dam/lotte-hotel/signiel/seoul/accommodation/suite/3291-4-2000-roo-LTSG.jpg.thumb.1920.1920.jpg")
                                    .build(),
                            HotelImage.builder()
                                    .image("https://www.lottehotel.com/content/dam/lotte-hotel/signiel/seoul/accommodation/suite/3291-8-2000-roo-LTSG.jpg.thumb.1920.1920.jpg")
                                    .build(),
                            HotelImage.builder()
                                    .image("https://www.lottehotel.com/content/dam/lotte-hotel/signiel/seoul/accommodation/suite/3291-6-2000-roo-LTSG.jpg.thumb.1920.1920.jpg")
                                    .build()
                    )))
                    .build()
    ));
    List<HotelReview> hotelReviews = new ArrayList<>(Arrays.asList());
    List<HotelReservation> hotelReservations = new ArrayList<>(Arrays.asList(
            HotelReservation.builder()
                    .user(User.builder().id("user").build())
                    .hotelRoom(HotelRoom.builder().idx(1L).build())
                    .checkInDate(Timestamp.valueOf(LocalDateTime.of(2023, 4, 7, 0, 0)))
                    .checkOutDate(Timestamp.valueOf(LocalDateTime.of(2023, 4, 8, 0, 0)))
                    .peopleCount(2)
                    .totalPrice(200000)
                    .createdAt(Timestamp.valueOf(LocalDateTime.now()))
                    .updatedAt(Timestamp.valueOf(LocalDateTime.now()))
                    .build(),
            HotelReservation.builder()
                    .user(User.builder().id("user").build())
                    .hotelRoom(HotelRoom.builder().idx(2L).build())
                    .checkInDate(Timestamp.valueOf(LocalDateTime.of(2023, 4, 15, 0, 0)))
                    .checkOutDate(Timestamp.valueOf(LocalDateTime.of(2023, 4, 11, 0, 0)))
                    .peopleCount(2)
                    .totalPrice(200000)
                    .createdAt(Timestamp.valueOf(LocalDateTime.now()))
                    .updatedAt(Timestamp.valueOf(LocalDateTime.now()))
                    .build()


    ));

    List<HotelImage> hotelImages = new ArrayList<>(Arrays.asList());


    public List<HotelRoom> getHotelRooms() {
        List<HotelRoom> hotelRooms = new ArrayList<>();
        for (int roomType = 1; roomType <= 9; roomType++) {
            for (int roomNumber = roomType * 100 + 1; roomNumber <= roomType * 100 + 9; roomNumber++) {
                HotelRoomType hotelRoomType = HotelRoomType.builder().idx((long) roomType).build();
                HotelRoom hotelRoom = HotelRoom.builder()
                        .roomNumber((long) roomNumber)
                        .roomType(hotelRoomType)
                        .build();
                hotelRooms.add(hotelRoom);
            }
        }
        return hotelRooms;
    }


}
