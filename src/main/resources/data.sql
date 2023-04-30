-- User 데이터 입력
insert into users (id, password, name, phone, email, role, grade)
values ('user', 'user', 'user', '010-1234-5678', 'user@example.com', 1, 1),
       ('user1', 'user1', 'user1', '010-1234-5678', 'user1@example.com', 1, 1),
       ('user2', 'user2', 'user2', '010-1234-5678', 'user2@example.com', 1, 1),
       ('user3', 'user3', 'user3', '010-1234-5678', 'user3@example.com', 1, 1),
       ('user4', 'user4', 'user4', '010-1234-5678', 'user4@example.com', 1, 1),
       ('admin', 'admin', '관리자', '010-0000-0000', 'admin@example.com', 0, 3);


-- Hotel 데이터 입력
INSERT INTO HOTEL (ADDRESS, DESCRIPTION, EMAIL, NAME, PHONE, SERVICE)
values ('서울특별시 송파구 올림픽로 300', '	롯데월드타워 76층~101층에서 즐기는 파노라믹한 스카이라인
미쉐린 스타 셰프 야닉 알레노의 다이닝과
세계 최고층에 위치한 웨딩&컨벤션
Live beyond expectations, SIGNIEL SEOUL', 'rsv.signiel.seoul@lotte.net', '시그니엘 서울', '+82-2-3213-1000', null);

INSERT INTO HOTEL (ADDRESS, DESCRIPTION, EMAIL, NAME, PHONE, SERVICE)
values ('부산광역시 해운대구 달맞이길 30', ' 국내 럭셔리 호텔을 대표하는 시그니엘 서울에 이은 시그니엘의 두번째 프로퍼티, 
엘시티(LCT)타워 3~19층에서 즐기는 인피니티 풀, 럭셔리 스파, 투숙객 전용 가든 테라스', 
'rsv.signiel.busan@lotte.net', '시그니엘 부산', '+82-51-922-1000', null);

INSERT INTO HOTEL (ADDRESS, DESCRIPTION, EMAIL, NAME, PHONE, SERVICE)
values ('제주특별자치도 서귀포시 중문관광로72번길 35', '푸른 제주 바다와 하늘이 맞닿은 곳에 위치한 롯데호텔 제주, 
천국 같은 휴식과 여행의 즐거움을 동시에 누릴 수 있는 대한민국 대표 호텔', 
'rsv.hotel.jeju@lotte.net', '롯데호텔 제주', '+82-64-731-1000', null);

insert into HOTELIMAGE (HOTEL_IDX, IMAGE)
values (1, 'https://www.lottehotel.com/content/dam/lotte-hotel/signiel/seoul/main/230119-01-2000-mai-LTSG.jpg.thumb.1920.1920.jpg');

insert into HOTELIMAGE (HOTEL_IDX, IMAGE)
values (2, 'https://www.lottehotel.com/content/dam/lotte-hotel/signiel/busan/promotion/packages/4722-01-1440-pkg-LTSB.jpg.thumb.1920.1920.jpg');

insert into HOTELIMAGE (HOTEL_IDX, IMAGE)
values (3, 'https://www.lottehotel.com/content/dam/lotte-hotel/lotte/jeju/main/4427-01-560-mai-LTJE.jpg');

-- Hotel Room Type 데이터 입력
insert into HOTELROOMTYPE (HOTEL_IDX, NAME, DESCRIPTION, PRICE, DISCOUNT, PEOPLECOUNT, BEDSIZE, ROOMSIZE)
values (1, '그랜드 디럭스 룸',
        '우아한 인테리어와 현대적 세련미가 조화롭게 어우러진 시그니엘 서울의 그랜드 디럭스 룸은 초고층 객실에서 바라보는 서울 도심의 파노라믹뷰와 최상의 휴식을 제공합니다.'
           , 600000, 0, 2, 2, 45);
insert into HotelRoomTypeImage(roomType_IDX, image)
values (1, 'https://www.lottehotel.com/content/dam/lotte-hotel/signiel/seoul/accommodation/deluxe/2838-01-2000-roo-LTSG.jpg.thumb.1920.1920.jpg'),
       (1, 'https://www.lottehotel.com/content/dam/lotte-hotel/signiel/seoul/accommodation/deluxe/2838-02-2000-roo-LTSG.jpg.thumb.1920.1920.jpg');

insert into HOTELROOMTYPE (HOTEL_IDX, NAME, DESCRIPTION, PRICE, DISCOUNT, PEOPLECOUNT, BEDSIZE, ROOMSIZE)
values (1, '프리미어 룸',
        '시그니엘 서울의 프리미어 룸은 세련미와 우아함을 더한 고급스러운 인테리어로 대형 창문을 통해 서울의 화려한 장관을 감상할 수 있습니다.
        또한 이탈리아 북부 알프스 천연 대리석으로 제작된 욕실에서 고품격 휴식을 즐기기에 충분합니다.'
           , 800000, 0, 2, 2, 55);
insert into HotelRoomTypeImage(roomType_IDX, image)
values (2,
        'https://www.lottehotel.com/content/dam/lotte-hotel/signiel/seoul/accommodation/premier/180708-30-2000-acc-seoul-signiel.jpg.thumb.1920.1920.jpg'),
       (2, 'https://www.lottehotel.com/content/dam/lotte-hotel/signiel/seoul/accommodation/premier/2849-1-2000-roo-LTSG.jpg.thumb.1920.1920.jpg'),
       (2, 'https://www.lottehotel.com/content/dam/lotte-hotel/signiel/seoul/accommodation/premier/2849-1-2000-roo-LTSG.jpg.thumb.1920.1920.jpg');

insert into HOTELROOMTYPE (HOTEL_IDX, NAME, DESCRIPTION, PRICE, DISCOUNT, PEOPLECOUNT, BEDSIZE, ROOMSIZE)
values (1, '프리미어 스위트 룸',
        '시그니엘 서울의 프리미어 스위트 룸의 넓고 고급스러운 인테리어는 고객의 안락함을 최우선으로 고려하여 설계되었으며 최고의 투숙 경험을 선사합니다.'
           , 960000, 0, 2, 2, 80);
insert into HotelRoomTypeImage(roomType_IDX, image)
values (3,
        'https://www.lottehotel.com/content/dam/lotte-hotel/signiel/seoul/accommodation/deluxe/180708-1-2000-acc-seoul-signiel.jpg.thumb.1920.1920.jpg'),
       (3,
        'https://www.lottehotel.com/content/dam/lotte-hotel/signiel/seoul/accommodation/deluxe/180708-3-2000-acc-seoul-signiel.jpg.thumb.1920.1920.jpg'),
       (3,
        'https://www.lottehotel.com/content/dam/lotte-hotel/signiel/seoul/accommodation/premier/180708-29-2000-acc-seoul-signiel.jpg.thumb.1920.1920.jpg');

insert into HOTELROOMTYPE (HOTEL_IDX, NAME, DESCRIPTION, PRICE, DISCOUNT, PEOPLECOUNT, BEDSIZE, ROOMSIZE)
values (1, '디럭스 스위트 룸',
        '시그니엘 서울의 디럭스 스위트 룸은 일반 객실보다 더욱 넓고 쾌적한 공간을 제공하는 객실입니다.\n침실과 응접실이 분리되어 있어 편안함과 안정된 휴식을 누리실 수 있습니다.'
           , 520000, 0, 2, 2, 80);
insert into HotelRoomTypeImage(roomType_IDX, image)
values (4,
        'https://www.lottehotel.com/content/dam/lotte-hotel/signiel/seoul/accommodation/suite/180708-55-2000-acc-seoul-signiel.jpg.thumb.1920.1920.jpg'),
       (4,
        'https://www.lottehotel.com/content/dam/lotte-hotel/signiel/seoul/accommodation/suite/180708-54-2000-acc-seoul-signiel.jpg.thumb.1920.1920.jpg'),
       (4,
        'https://www.lottehotel.com/content/dam/lotte-hotel/signiel/seoul/accommodation/suite/180708-53-2000-acc-seoul-signiel.jpg.thumb.1920.1920.jpg'),
       (4,
        'https://www.lottehotel.com/content/dam/lotte-hotel/signiel/seoul/accommodation/suite/180708-52-2000-acc-seoul-signiel.jpg.thumb.1920.1920.jpg');

insert into HOTELROOMTYPE (HOTEL_IDX, NAME, DESCRIPTION, PRICE, DISCOUNT, PEOPLECOUNT, BEDSIZE, ROOMSIZE)
values (1, '프리미어 스위트 룸',
        '시그니엘 서울의 프리미어 스위트 룸의 넓고 고급스러운 인테리어는 고객의 안락함을 최우선으로 고려하여 설계되었으며 최고의 투숙 경험을 선사합니다.'
           , 520000, 0, 2, 2, 80);
insert into HotelRoomTypeImage(roomType_IDX, image)
values (5,
        'https://www.lottehotel.com/content/dam/lotte-hotel/signiel/seoul/accommodation/suite/180708-79-2000-acc-seoul-signiel.jpg.thumb.1920.1920.jpg'),
       (5,
        'https://www.lottehotel.com/content/dam/lotte-hotel/signiel/seoul/accommodation/suite/180708-51-2000-acc-seoul-signiel.jpg.thumb.1920.1920.jpg'),
       (5,
        'https://www.lottehotel.com/content/dam/lotte-hotel/signiel/seoul/accommodation/suite/180708-78-2000-acc-seoul-signiel.jpg.thumb.1920.1920.jpg'),
       (5,
        'https://www.lottehotel.com/content/dam/lotte-hotel/signiel/seoul/accommodation/suite/180708-77-2000-acc-seoul-signiel.jpg.thumb.1920.1920.jpg');

insert into HOTELROOMTYPE (HOTEL_IDX, NAME, DESCRIPTION, PRICE, DISCOUNT, PEOPLECOUNT, BEDSIZE, ROOMSIZE)
values (1, '코리안 프리미어 룸',
        '시그니엘 서울의 코리안 프리미어 룸은 92층에 위치하여 최고의 전망을 자랑하는 객실입니다.\n한국 전통미와 현대적인 시설의 조화가 이루어진 최고급 온돌로 구성되어 있으며 한국의 궁을 테마로 한 온돌, 객실 곳곳에 배치된 전통가구와 편백나무로 구성된 욕실은 한국적인 멋을 더욱 돋보이게 합니다.'
           , 1000000, 0, 2, 2, 40);
insert into HotelRoomTypeImage(roomType_IDX, image)
values (6, 'https://www.lottehotel.com/content/dam/lotte-hotel/signiel/seoul/accommodation/premier/2849-4-2000-roo-LTSG.jpg.thumb.1920.1920.jpg'),
       (6, 'https://www.lottehotel.com/content/dam/lotte-hotel/signiel/seoul/accommodation/premier/2849-10-2000-roo-LTSG.jpg.thumb.1920.1920.jpg');

insert into HOTELROOMTYPE (HOTEL_IDX, NAME, DESCRIPTION, PRICE, DISCOUNT, PEOPLECOUNT, BEDSIZE, ROOMSIZE)
values (1, '코리안 시그니엘 프리미어 룸',
        '시그니엘 서울의 코리안 시그니엘 프리미어 룸은 92층에 위치하여 최고의 전망을 자랑하는 객실입니다.\n또한, 한국 전통미와 현대적인 시설의 조화가 이루어진 최고급 온돌로 구성되어 있으며 한국의 궁을 테마로 한 온돌, 객실 곳곳에 배치된 전통가구와 편백나무로 구성된 욕실은 한국적인 멋을 더욱 돋보이게 합니다.'
           , 1000000, 0, 2, 2, 40);
insert into HotelRoomTypeImage(roomType_IDX, image)
values (7, 'https://www.lottehotel.com/content/dam/lotte-hotel/signiel/seoul/accommodation/premier/2849-4-2000-roo-LTSG.jpg.thumb.1920.1920.jpg'),
       (7, 'https://www.lottehotel.com/content/dam/lotte-hotel/signiel/seoul/accommodation/premier/2849-10-2000-roo-LTSG.jpg.thumb.1920.1920.jpg');

insert into HOTELROOMTYPE (HOTEL_IDX, NAME, DESCRIPTION, PRICE, DISCOUNT, PEOPLECOUNT, BEDSIZE, ROOMSIZE)
values (1, '코리안 스위트 룸',
        '시그니엘 서울의 코리안 스위트 룸은 92층에 위치하여 최고의 전망을 자랑하는 객실입니다.\n또한, 한국 전통미와 현대적인 시설의 조화가 이루어진 최고급 온돌로 구성되어 있으며 한국의 궁을 테마로 한 온돌, 객실 곳곳에 배치된 전통가구와 편백나무로 구성된 욕실은 한국적인 멋을 더욱 돋보이게 합니다.'
           , 600000, 0, 2, 2, 84);
insert into HotelRoomTypeImage(roomType_IDX, image)
values (8, 'https://www.lottehotel.com/content/dam/lotte-hotel/signiel/seoul/accommodation/suite/3293-1-2000-roo-LTSG.jpg.thumb.1920.1920.jpg'),
       (8, 'https://www.lottehotel.com/content/dam/lotte-hotel/signiel/seoul/accommodation/suite/3293-4-2000-roo-LTSG.jpg.thumb.1920.1920.jpg'),
       (8, 'https://www.lottehotel.com/content/dam/lotte-hotel/signiel/seoul/accommodation/suite/3293-6-2000-roo-LTSG.jpg.thumb.1920.1920.jpg'),
       (8, 'https://www.lottehotel.com/content/dam/lotte-hotel/signiel/seoul/accommodation/suite/3293-5-2000-roo-LTSG.jpg.thumb.1920.1920.jpg'),
       (8,
        'https://www.lottehotel.com/content/dam/lotte-hotel/signiel/seoul/accommodation/suite/180708-66-2000-acc-seoul-signiel.jpg.thumb.1920.1920.jpg');

insert into HOTELROOMTYPE (HOTEL_IDX, NAME, DESCRIPTION, PRICE, DISCOUNT, PEOPLECOUNT, BEDSIZE, ROOMSIZE)
values (1, '프레지덴셜 스위트 룸',
        '시그니엘 서울의 프레지덴셜 스위트 룸은 넓은 공간과 세련된 디자인의 응접실 및 다이닝룸을 갖춘 객실입니다.\n대형 창문을 통해 펼쳐지는 아름다운 서울의 전망과 함께 시그니엘 서울만의 세심한 서비스를 느껴보세요.'
           , 1000000, 0, 2, 2, 143);
insert into HotelRoomTypeImage(roomType_IDX, image)
values (9, 'https://www.lottehotel.com/content/dam/lotte-hotel/signiel/seoul/accommodation/suite/3292-5-2000-roo-LTSG.jpg.thumb.1920.1920.jpg'),
       (9, 'https://www.lottehotel.com/content/dam/lotte-hotel/signiel/seoul/accommodation/suite/3292-4-2000-roo-LTSG.jpg.thumb.1920.1920.jpg'),
       (9, 'https://www.lottehotel.com/content/dam/lotte-hotel/signiel/seoul/accommodation/suite/3292-8-2000-roo-LTSG.jpg.thumb.1920.1920.jpg'),
       (9,
        'https://www.lottehotel.com/content/dam/lotte-hotel/signiel/seoul/accommodation/suite/180708-71-2000-acc-seoul-signiel.jpg.thumb.1920.1920.jpg'),
       (9, 'https://www.lottehotel.com/content/dam/lotte-hotel/signiel/seoul/accommodation/suite/3292-2-2000-roo-LTSG.jpg.thumb.1920.1920.jpg');

insert into HOTELROOMTYPE (HOTEL_IDX, NAME, DESCRIPTION, PRICE, DISCOUNT, PEOPLECOUNT, BEDSIZE, ROOMSIZE)
values (1, '로얄 스위트 룸',
        '시그니엘 서울의 100층에 위치한 로얄스위트 룸은 세계 각국의 국빈과 국내외 VVIP를 위한 시그니엘 서울의 단 하나뿐인 최고급 객실입니다.\n럭셔리한 응접실과 회의실은 물론 비서관 전용 객실, 자쿠지 등 최신 시설과 보안 시스템을 갖추고 있으며 시그니엘 서울만의 세심하고 격조 높은 서비스는 잊지 못할 감동을 선사합니다.'
           , 2500000, 0, 2, 2, 353);
insert into HotelRoomTypeImage(roomType_IDX, image)
values (10, 'https://www.lottehotel.com/content/dam/lotte-hotel/signiel/seoul/accommodation/suite/3291-2-2000-roo-LTSG.jpg.thumb.1920.1920.jpg'),
       (10, 'https://www.lottehotel.com/content/dam/lotte-hotel/signiel/seoul/accommodation/suite/3291-9-2000-roo-LTSG.jpg.thumb.1920.1920.jpg'),
       (10, 'https://www.lottehotel.com/content/dam/lotte-hotel/signiel/seoul/accommodation/suite/3291-4-2000-roo-LTSG.jpg.thumb.1920.1920.jpg'),
       (10, 'https://www.lottehotel.com/content/dam/lotte-hotel/signiel/seoul/accommodation/suite/3291-8-2000-roo-LTSG.jpg.thumb.1920.1920.jpg'),
       (10, 'https://www.lottehotel.com/content/dam/lotte-hotel/signiel/seoul/accommodation/suite/3291-6-2000-roo-LTSG.jpg.thumb.1920.1920.jpg');

insert into HOTELROOMTYPE (HOTEL_IDX, NAME, DESCRIPTION, PRICE, DISCOUNT, PEOPLECOUNT, BEDSIZE, ROOMSIZE)
values (2, '그랜드 디럭스 미포 하버뷰 룸',
        '우아한 인테리어와 현대적 세련미가 조화롭게 어우러진  시그니엘 부산의 그랜드 디럭스 미포 하버뷰 객실로, 비즈니스 여행객과 레저 여행객 모두에게 편안한 숙박을 제공합니다.'
           , 300000, 0, 2, 2, 37);
insert into HotelRoomTypeImage(roomType_IDX, image)
values (11, 'https://www.lottehotel.com/content/dam/lotte-hotel/signiel/busan/rooms/grand/2738-02-2000-roo-LTSB.jpg.thumb.1920.1920.jpg'),
       (11, 'https://www.lottehotel.com/content/dam/lotte-hotel/signiel/busan/rooms/grand/2738-03-2000-roo-LTSB.jpg.thumb.1920.1920.jpg'),
       (11, 'https://www.lottehotel.com/content/dam/lotte-hotel/signiel/busan/rooms/grand/200306-1-2000-roo-LTSB.jpg.thumb.1920.1920.jpg'),
       (11, 'https://www.lottehotel.com/content/dam/lotte-hotel/signiel/busan/rooms/grand/200306-2-2000-roo-LTSB.jpg.thumb.1920.1920.jpg'),
       (11, 'https://www.lottehotel.com/content/dam/lotte-hotel/signiel/busan/rooms/grand/2745-2000-roo-LTSB.jpg.thumb.1920.1920.jpg');

insert into HOTELROOMTYPE (HOTEL_IDX, NAME, DESCRIPTION, PRICE, DISCOUNT, PEOPLECOUNT, BEDSIZE, ROOMSIZE)
values (2, '그랜드 디럭스 미포 하버뷰 룸',
        '우아한 인테리어와 현대적 세련미가 조화롭게 어우러진  시그니엘 부산의 그랜드 디럭스 미포 하버뷰 객실로, 비즈니스 여행객과 레저 여행객 모두에게 편안한 숙박을 제공합니다.'
           , 300000, 0, 2, 2, 37);
insert into HotelRoomTypeImage(roomType_IDX, image)
values (11, 'https://www.lottehotel.com/content/dam/lotte-hotel/signiel/busan/rooms/grand/2738-02-2000-roo-LTSB.jpg.thumb.1920.1920.jpg'),
       (11, 'https://www.lottehotel.com/content/dam/lotte-hotel/signiel/busan/rooms/grand/2738-03-2000-roo-LTSB.jpg.thumb.1920.1920.jpg'),
       (11, 'https://www.lottehotel.com/content/dam/lotte-hotel/signiel/busan/rooms/grand/200306-1-2000-roo-LTSB.jpg.thumb.1920.1920.jpg'),
       (11, 'https://www.lottehotel.com/content/dam/lotte-hotel/signiel/busan/rooms/grand/200306-2-2000-roo-LTSB.jpg.thumb.1920.1920.jpg'),
       (11, 'https://www.lottehotel.com/content/dam/lotte-hotel/signiel/busan/rooms/grand/2745-2000-roo-LTSB.jpg.thumb.1920.1920.jpg');

insert into HOTELROOMTYPE (HOTEL_IDX, NAME, DESCRIPTION, PRICE, DISCOUNT, PEOPLECOUNT, BEDSIZE, ROOMSIZE)
values (2, '프리미어 더블 룸',
        '시그니엘 부산의 프리미어 더블 룸은 세련미와 우아함을 더한 고급스러운 인테리어로 구성되어 있으며, 욕조에서 바라보는 도심 야경은 최상의 휴식을 제공합니다.'
           , 300000, 0, 2, 2, 48);
insert into HotelRoomTypeImage(roomType_IDX, image)
values (12, 'https://www.lottehotel.com/content/dam/lotte-hotel/signiel/busan/rooms/premier/20210122-1-2000-roo-LTSB.jpg.thumb.1920.1920.jpg');

insert into HOTELROOMTYPE (HOTEL_IDX, NAME, DESCRIPTION, PRICE, DISCOUNT, PEOPLECOUNT, BEDSIZE, ROOMSIZE)
values (2, '프리미어 더블 오션뷰 룸',
        '시그니엘 부산의 프리미엄 더블 오션뷰 룸은 세련미와 우아함을 더한 고급스러운 인테리어로 구성되어 있으며, 대형 발코니 창을 통해 야외 온수풀과 아름다운 해운대 바다를 감상할 수 있는 객실 입니다.'
           , 370000, 0, 2, 2, 52);
insert into HotelRoomTypeImage(roomType_IDX, image)
values (13, 'https://www.lottehotel.com/content/dam/lotte-hotel/signiel/busan/rooms/premier/20210122-4-2000-roo-LTSB.jpg.thumb.1920.1920.jpg'),
       (13, 'https://www.lottehotel.com/content/dam/lotte-hotel/signiel/busan/rooms/premier/20210122-3-2000-roo-LTSB.jpg.thumb.1920.1920.jpg');

insert into HOTELROOMTYPE (HOTEL_IDX, NAME, DESCRIPTION, PRICE, DISCOUNT, PEOPLECOUNT, BEDSIZE, ROOMSIZE)
values (2, '프리미어 트윈 미포 하버뷰 룸',
        '시그니엘 부산의 프리미어 트윈 미포 하버뷰 룸은 세련미와 우아함을 더한 고급스러운 인테리어로 구성되어 있으며, 달맞이길과 측면의 바다 전망을 감상 할 수 있는 고품격 객실 입니다.'
           , 340000, 0, 2, 3, 50);
insert into HotelRoomTypeImage(roomType_IDX, image)
values (14, 'https://www.lottehotel.com/content/dam/lotte-hotel/signiel/busan/rooms/premier/20210122-5-2000-roo-LTSB.jpg.thumb.1920.1920.jpg');

insert into HOTELROOMTYPE (HOTEL_IDX, NAME, DESCRIPTION, PRICE, DISCOUNT, PEOPLECOUNT, BEDSIZE, ROOMSIZE)
values (2, '프리미어 패밀리 트윈 룸',
        '시그니엘 부산의 프리미어 패밀리 트윈 룸은 세련미와 우아함을 더한 고급스러운 인테리어로 구성되어 있으며, 달맞이길의 아름다운 야경을 감상 할 수 있는 고품격 객실 입니다.'
           , 370000, 0, 3, 3, 44);
insert into HotelRoomTypeImage(roomType_IDX, image)
values (15, 'https://www.lottehotel.com/content/dam/lotte-hotel/signiel/busan/rooms/premier/2743-2000-roo-LTSB.jpg.thumb.1920.1920.jpg'),
(15, 'https://www.lottehotel.com/content/dam/lotte-hotel/signiel/busan/rooms/premier/2738-04-2000-roo-LTSB.jpg.thumb.1920.1920.jpg'),
(15, 'https://www.lottehotel.com/content/dam/lotte-hotel/signiel/busan/rooms/premier/2738-05-2000-roo-LTSB.jpg.thumb.1920.1920.jpg');

insert into HOTELROOMTYPE (HOTEL_IDX, NAME, DESCRIPTION, PRICE, DISCOUNT, PEOPLECOUNT, BEDSIZE, ROOMSIZE)
values (2, '시그니엘 프리미어 더블 오션뷰 룸',
        '시그니엘 부산의 시그니엘 프리미어 더블 오션뷰 룸은 침실과 함께 두 개의 발코니와 소파가 구비된 여유로운 객실로, 해운대 바다와 도심의 야경을 모두 감상할 수 있는 고급 비즈니스 레저 객실 입니다.'
           , 440000, 0, 2, 2, 62);
insert into HotelRoomTypeImage(roomType_IDX, image)
values (16, 'https://www.lottehotel.com/content/dam/lotte-hotel/signiel/busan/rooms/premier/2740-2000-roo-LTSB.jpg.thumb.1920.1920.jpg'),
(16, 'https://www.lottehotel.com/content/dam/lotte-hotel/signiel/busan/rooms/premier/2742-2000-roo-LTSB.jpg.thumb.1920.1920.jpg');

insert into HOTELROOMTYPE (HOTEL_IDX, NAME, DESCRIPTION, PRICE, DISCOUNT, PEOPLECOUNT, BEDSIZE, ROOMSIZE)
values (2, '시그니엘 프리미어 더블 미포 하버뷰 룸',
        '시그니엘 부산의 시그니엘 프리미어 더블 미포 하버뷰 룸은 침실과 함께 업무 공간이 구비되어 성공적인 비즈니스와 여유로운 휴식을 즐길 수 있는 고급 비즈니스 레저 객실 입니다.'
           , 390000, 0, 2, 2, 60);
insert into HotelRoomTypeImage(roomType_IDX, image)
values (17, 'https://www.lottehotel.com/content/dam/lotte-hotel/signiel/busan/rooms/signiel-premier/20200711-5-2000-roo-LTSB.jpg.thumb.1920.1920.jpg'),
(17, 'https://www.lottehotel.com/content/dam/lotte-hotel/signiel/busan/rooms/signiel-premier/20200711-4-2000-roo-LTSB.jpg.thumb.1920.1920.jpg'),
(17, 'https://www.lottehotel.com/content/dam/lotte-hotel/signiel/busan/rooms/signiel-premier/20200711-3-2000-roo-LTSB.jpg.thumb.1920.1920.jpg');

insert into HOTELROOMTYPE (HOTEL_IDX, NAME, DESCRIPTION, PRICE, DISCOUNT, PEOPLECOUNT, BEDSIZE, ROOMSIZE)
values (2, '프레지덴셜 스위트 오션뷰 룸',
        '시그니엘 부산의 프레지덴셜 스위트 오션뷰 룸은 넓은 공간과 쾌적한 거실, 럭셔리한 디자인의 응접실, 최고급 레스토랑을 연상시키는 다이닝룸 등의 공간 구성으로 고급스러운 분위기를 연출하는 객실 입니다.\n사회적으로 존경받는 각계각층 VIP의 안락한 휴식을 위해 설계된 프레지덴셜 스위트 룸은 대형 발코니 창을 통해 펼쳐지는 아름다운 해운대의 야경과 함께 시그니엘 부산만의 세심한 서비스와 안전을 제공합니다.'
           , 4800000, 0, 2, 2, 122);
insert into HotelRoomTypeImage(roomType_IDX, image)
values (18, 'https://www.lottehotel.com/content/dam/lotte-hotel/signiel/busan/rooms/suite/presidential-suite/20200716-3-2000-roo-LTSB.jpg.thumb.1920.1920.jpg'),
(18, 'https://www.lottehotel.com/content/dam/lotte-hotel/signiel/busan/rooms/suite/presidential-suite/20200716-1-2000-roo-LTSB.jpg.thumb.1920.1920.jpg'),
(18, 'https://www.lottehotel.com/content/dam/lotte-hotel/signiel/busan/rooms/suite/presidential-suite/20200716-2-2000-roo-LTSB.jpg.thumb.1920.1920.jpg');

insert into HOTELROOMTYPE (HOTEL_IDX, NAME, DESCRIPTION, PRICE, DISCOUNT, PEOPLECOUNT, BEDSIZE, ROOMSIZE)
values (2, '로얄 스위트 오션뷰 룸',
        '시그니엘 부산의 로얄 스위트 오션뷰 룸은 세계 각국의 국빈과 국내외 VVIP를 위한 단 하나뿐인 최고급 객실입니다.\n럭셔리한 응접실과 회의실은 물론 비서고나 전용 객실, 용식 미러TV 등 최신 시설과 보안 시스템을 갖추고 있으며 해운대 바다의 파노라마 오션뷰를 즐기면서 시그니엘 부산만의 세심하고 격조 높은 서비스는 잊지 못할 감동을 선사합니다.'
           , 7200000, 0, 2, 2, 189);
insert into HotelRoomTypeImage(roomType_IDX, image)
values (19, 'https://www.lottehotel.com/content/dam/lotte-hotel/signiel/busan/rooms/suite/royal-suite/20210121-2-2000-roo-LTSB.jpg.thumb.1920.1920.jpg'),
(19, 'https://www.lottehotel.com/content/dam/lotte-hotel/signiel/busan/rooms/suite/royal-suite/20210121-3-2000-roo-LTSB.jpg.thumb.1920.1920.jpg'),
(19, 'https://www.lottehotel.com/content/dam/lotte-hotel/signiel/busan/rooms/suite/royal-suite/20210121-1-2000-roo-LTSB.jpg.thumb.1920.1920.jpg');    

insert into HOTELROOMTYPE (HOTEL_IDX, NAME, DESCRIPTION, PRICE, DISCOUNT, PEOPLECOUNT, BEDSIZE, ROOMSIZE)
values (3, '디럭스 룸',
        '롯데호텔 제주 디럭스 마운틴 뷰 룸은 본관 9층~11층 사이에 위치하여 아름다운 한라산 전망을 감상하실 수 있는 객실입니다.'
           , 293700, 0, 3, 3, 40);
insert into HotelRoomTypeImage(roomType_IDX, image)
values (20, 'https://www.lottehotel.com/content/dam/lotte-hotel/lotte/jeju/accommodation/standard/superior/180804-1-2000-acc-jeju-hotel.jpg.thumb.1920.1920.jpg'),
       (20, 'https://www.lottehotel.com/content/dam/lotte-hotel/lotte/jeju/accommodation/standard/superior/180804-7-2000-acc-jeju-hotel.jpg.thumb.1920.1920.jpg'),
       (20, 'https://www.lottehotel.com/content/dam/lotte-hotel/lotte/jeju/accommodation/deluxe/ocean/4155-02-2000-roo-LTJE.jpg.thumb.1920.1920.jpg'),
       (20, 'https://www.lottehotel.com/content/dam/lotte-hotel/lotte/jeju/accommodation/deluxe/ocean/4156-01-2000-roo-LTJE.jpg.thumb.1920.1920.jpg'),
       (20, 'https://www.lottehotel.com/content/dam/lotte-hotel/lotte/jeju/accommodation/standard/superior/4484-2000-acc-LTJE.jpg.thumb.1920.1920.jpg');

insert into HOTELROOMTYPE (HOTEL_IDX, NAME, DESCRIPTION, PRICE, DISCOUNT, PEOPLECOUNT, BEDSIZE, ROOMSIZE)
values (3, '샤롯데 룸',
        '롯데호텔 제주의 샤롯데 룸은 서유럽풍의 우아하면서도 세련된 스타일 객실입니다.\n새롭게 디자인한 실내 공간은 특별한 여유로움을 느끼실 수 있게 합니다.'
           , 378000, 0, 3, 3, 40);
insert into HotelRoomTypeImage(roomType_IDX, image)
values (21, 'https://www.lottehotel.com/content/dam/lotte-hotel/lotte/jeju/accommodation/deluxe/charlotte-deluxe-room-(terrace-ocean-view)/3549-01-2000-acc-LTJE.jpg.thumb.1920.1920.jpg'), 
(21, 'https://www.lottehotel.com/content/dam/lotte-hotel/lotte/jeju/accommodation/deluxe/charlotte-deluxe-room-(terrace-ocean-view)/3549-02-2000-acc-LTJE.jpg.thumb.1920.1920.jpg');

insert into HOTELROOMTYPE (HOTEL_IDX, NAME, DESCRIPTION, PRICE, DISCOUNT, PEOPLECOUNT, BEDSIZE, ROOMSIZE)
values (3, '코너 스위트 룸',
        '롯데호텔 제주의 코너 스위트 룸은 차분하면서도 품격 있는 서유럽풍의 세련된 스타일의 객실입니다.\n새롭게 디자인한 실내 공간은 여유로운 제주에서의 특별한 휴식을 선사합니다.', 
441000, 0, 4, 2, 50);
insert into HotelRoomTypeImage(roomType_IDX, image)
values (22, 'https://www.lottehotel.com/content/dam/lotte-hotel/lotte/jeju/accommodation/deluxe/charlotte-deluxe-room-(terrace-ocean-view)/2829-12-2000-acc-LTJE.jpg.thumb.1920.1920.jpg'), 
(22, 'https://www.lottehotel.com/content/dam/lotte-hotel/lotte/jeju/accommodation/deluxe/charlotte-deluxe-room-(terrace-ocean-view)/2829-13-2000-acc-LTJE.jpg.thumb.1920.1920.jpg'), 
(22, 'https://www.lottehotel.com/content/dam/lotte-hotel/lotte/jeju/accommodation/deluxe/charlotte-deluxe-room-(terrace-ocean-view)/2829-15-2000-acc-LTJE.jpg.thumb.1920.1920.jpg'), 
(22, 'https://www.lottehotel.com/content/dam/lotte-hotel/lotte/jeju/accommodation/deluxe/charlotte-deluxe-room-(terrace-ocean-view)/2829-14-2000-acc-LTJE.jpg.thumb.1920.1920.jpg');

insert into HOTELROOMTYPE (HOTEL_IDX, NAME, DESCRIPTION, PRICE, DISCOUNT, PEOPLECOUNT, BEDSIZE, ROOMSIZE)
values (3, '프리미어 온돌 룸',
        '롯데호텔 제주의 프리미어 온돌 룸은 한국 전통의 멋이 느껴지는 한국식 온돌 룸으로 온돌에 침대가 비치되어 있습니다.\n침대가 익숙한 외국인은 전통 한국의 멋과 편안함을 함께 즐길 수 있으며 가족 단위 여행에 적합한 객실입니다.'
           , 372000, 0, 3, 3, 47);
insert into HotelRoomTypeImage(roomType_IDX, image)
values (23, 'https://www.lottehotel.com/content/dam/lotte-hotel/lotte/jeju/accommodation/deluxe/ondol/1773-5-2000-acc-LTJE.jpg.thumb.1920.1920.jpg');

insert into HOTELROOMTYPE (HOTEL_IDX, NAME, DESCRIPTION, PRICE, DISCOUNT, PEOPLECOUNT, BEDSIZE, ROOMSIZE)
values (3, '프리미어 룸',
        '롯데호텔 제주의 프리미어 가든 룸은 발코니에서 야외 수영장과 풍차를 비롯한 아름다운 야외 정원 전망을 즐길 수 있는 여행을 위한 최상의 객실입니다. 색다른 이국의 낭만을 느낄 수 있는 객실입니다.'
           , 399900, 0, 4, 2, 47);
insert into HotelRoomTypeImage(roomType_IDX, image)
values (24, 'https://www.lottehotel.com/content/dam/lotte-hotel/lotte/jeju/accommodation/premier/premier/1020-2000-roo-LTJE.jpg.thumb.1920.1920.jpg');

insert into HOTELROOMTYPE (HOTEL_IDX, NAME, DESCRIPTION, PRICE, DISCOUNT, PEOPLECOUNT, BEDSIZE, ROOMSIZE)
values (3, '헬로키티 키즈 룸',
        '글로벌 캐릭터 브랜드 산리오코리아와의 협업으로 본관 4층에 선보이는 헬로키티 캐릭터 룸은 영원한 모두의 친구 ‘헬로키티’를 테마로 꾸며진 객실입니다.\n복도는 물론 모든 객실의 침대와 소파, 욕실까지 헬로키티로 꾸며져 가족 모두를 순수한 동심의 세계로 안내할 것입니다.'
           , 372000, 0, 3, 3, 47);
insert into HotelRoomTypeImage(roomType_IDX, image)
values (25, 'https://www.lottehotel.com/content/dam/lotte-hotel/lotte/jeju/accommodation/character/kids/921-1-2000-acc-LTJE.jpg.thumb.1920.1920.jpg'),
(25, 'https://www.lottehotel.com/content/dam/lotte-hotel/lotte/jeju/accommodation/character/kids/921-02-2000-acc-LTJE.jpg.thumb.1920.1920.jpg'),
(25, 'https://www.lottehotel.com/content/dam/lotte-hotel/lotte/jeju/accommodation/character/kids/921-3-2000-acc-LTJE.jpg.thumb.1920.1920.jpg'),
(25, 'https://www.lottehotel.com/content/dam/lotte-hotel/lotte/jeju/accommodation/character/kids/921-04-2000-acc-LTJE.jpg.thumb.1920.1920.jpg'),
(25, 'https://www.lottehotel.com/content/dam/lotte-hotel/lotte/jeju/accommodation/character/kids/922-05-2000-acc-LTJE.jpg.thumb.1920.1920.jpg');

insert into HOTELROOMTYPE (HOTEL_IDX, NAME, DESCRIPTION, PRICE, DISCOUNT, PEOPLECOUNT, BEDSIZE, ROOMSIZE)
values (3, '풀빌라 스위트 룸',
        '자연의 숨결을 담은 또 하나의 제주 ‘풀빌라 스위트 가든 룸’ 화산섬 제주의 향기를 그대로 느낄 수 있도록 현무암과 억새로 제주 전통 가옥을 재현한 방갈로 형 풀빌라입니다.\n내외부가 서로 소통할 수 있는 열린 구조로 자유롭게 바람이 드나들어 빌라와 자연이 하나 된 느낌을 선사하며 자연의 숨결을 담은 제주 풀빌라 스위트입니다.'
           , 1281000, 0, 3, 3, 86);
insert into HotelRoomTypeImage(roomType_IDX, image)
values (26, 'https://www.lottehotel.com/content/dam/lotte-hotel/lotte/jeju/accommodation/suite/pool/180804-67-2000-acc-jeju-hotel.jpg.thumb.1920.1920.jpg'),
(26, 'https://www.lottehotel.com/content/dam/lotte-hotel/lotte/jeju/accommodation/suite/pool/180804-68-2000-acc-jeju-hotel.jpg.thumb.1920.1920.jpg'),
(26, 'https://www.lottehotel.com/content/dam/lotte-hotel/lotte/jeju/accommodation/suite/pool/180804-62-2000-acc-jeju-hotel.jpg.thumb.1920.1920.jpg'),
(26, 'https://www.lottehotel.com/content/dam/lotte-hotel/lotte/jeju/accommodation/suite/pool/1773-1-2000-acc-LTJE.jpg.thumb.1920.1920.jpg');

insert into HOTELROOMTYPE (HOTEL_IDX, NAME, DESCRIPTION, PRICE, DISCOUNT, PEOPLECOUNT, BEDSIZE, ROOMSIZE)
values (3, '프레지덴셜 스위트 룸',
        '롯데호텔 제주의 프레지덴셜 스위트 오션 룸은 대형 침실과 6인이 회의를 할 수 있는 미팅 룸 겸 다이닝 공간, 키친 등이 구비된 VIP 스위트 룸입니다.\n제주의 아름다운 전망, 일상을 탈피한 여유로움, 고급스러운 자쿠지가 마련되어 있는 스위트 룸에서 품격 있는 휴식을 즐기십시오.'
           , 1981000, 0, 3, 3, 204);
insert into HotelRoomTypeImage(roomType_IDX, image)
values (27, 'https://www.lottehotel.com/content/dam/lotte-hotel/lotte/jeju/accommodation/suite/presidential/4179-04-2000-acc-LTJE.jpg.thumb.1920.1920.jpg'),
(27, 'https://www.lottehotel.com/content/dam/lotte-hotel/lotte/jeju/accommodation/suite/presidential/4179-03-2000-acc-LTJE.jpg.thumb.1920.1920.jpg'),
(27, 'https://www.lottehotel.com/content/dam/lotte-hotel/lotte/jeju/accommodation/suite/presidential/4450-02-2000-roo-LTJE.jpg.thumb.1920.1920.jpg'),
(27, 'https://www.lottehotel.com/content/dam/lotte-hotel/lotte/jeju/accommodation/suite/presidential/180804-8-2000-acc-jeju-hotel.jpg.thumb.1920.1920.jpg'),
(27, 'https://www.lottehotel.com/content/dam/lotte-hotel/lotte/jeju/accommodation/suite/presidential/4450-01-2000-roo-LTJE.jpg.thumb.1920.1920.jpg');

insert into HOTELROOMTYPE (HOTEL_IDX, NAME, DESCRIPTION, PRICE, DISCOUNT, PEOPLECOUNT, BEDSIZE, ROOMSIZE)
values (3, '로얄 스위트 룸',
        '롯데호텔 제주의 로얄 스위트 룸은 제주의 에메랄드 빛 바다가 한 눈에 내려다 보이는 최고의 전망과 낭만을 지닌 롯데호텔 제주 로얄 스위트 오션 룸은 쾌적하고 넓은 공간이 특징입니다.\n2개의 침실과 10인이 식사할 수 있는 미팅 룸 겸 다이닝 공간, 바다가 보이는 대형 자쿠지 등으로 구성되었으며 세계 최정상 VIP를 위한 제주 최고의 스위트 룸입니다.'
           , 11000000, 0, 2, 5, 283);
insert into HotelRoomTypeImage(roomType_IDX, image)
values (28, 'https://www.lottehotel.com/content/dam/lotte-hotel/lotte/jeju/accommodation/suite/royal/4179-01-2000-acc-LTJE.jpg.thumb.1920.1920.jpg'),
(28, 'https://www.lottehotel.com/content/dam/lotte-hotel/lotte/jeju/accommodation/suite/royal/4179-02-2000-acc-LTJE.jpg.thumb.1920.1920.jpg'),
(28, 'https://www.lottehotel.com/content/dam/lotte-hotel/lotte/jeju/accommodation/suite/royal/180804-4-2000-acc-jeju-hotel.jpg.thumb.1920.1920.jpg'),
(28, 'https://www.lottehotel.com/content/dam/lotte-hotel/lotte/jeju/accommodation/suite/royal/180804-13-2000-acc-jeju-hotel.jpg.thumb.1920.1920.jpg'),
(28, 'https://www.lottehotel.com/content/dam/lotte-hotel/lotte/jeju/accommodation/suite/royal/180804-11-2000-acc-jeju-hotel.jpg.thumb.1920.1920.jpg');

-- Hotel Room 데이터 입력
insert into HOTELROOM (ROOMTYPE_IDX, ROOMNUMBER)
values (1, 101),
       (1, 102),
       (1, 103),
       (1, 104),
       (1, 105),
       (1, 106),
       (1, 107),
       (1, 108),
       (1, 109),
       (2, 201),
       (2, 202),
       (2, 203),
       (2, 204),
       (2, 205),
       (2, 206),
       (2, 207),
       (2, 208),
       (2, 209),
       (3, 301),
       (3, 302),
       (3, 303),
       (3, 304),
       (3, 305),
       (3, 306),
       (3, 307),
       (3, 308),
       (4, 401),
       (4, 402),
       (4, 403),
       (4, 404),
       (4, 405),
       (4, 406),
       (4, 407),
       (4, 408),
       (5, 501),
       (5, 502),
       (5, 503),
       (5, 504),
       (5, 505),
       (5, 506),
       (5, 507),
       (6, 601),
       (6, 602),
       (6, 603),
       (6, 604),
       (6, 605),
       (6, 606),
       (6, 607),
       (7, 701),
       (7, 702),
       (7, 703),
       (7, 704),
       (7, 705),
       (8, 801),
       (8, 802),
       (8, 803),
       (8, 804),
       (8, 805),
       (9, 901),
       (9, 902),
       (9, 903),
       (9, 904),
       (9, 905),
       (10, 1001);

-- Hotel 예약 데이터 입력
insert into HOTELRESERVATION (USER_ID, HOTELROOM_IDX, CHECKINDATE, CHECKOUTDATE, PEOPLECOUNT, TOTALPRICE, CREATEDAT,
                              UPDATEDAT, PAYEDMONEY)
values ('user', 1, '2023-04-07 15:00:00', '2023-04-08 11:00:00', 2, 700000, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP,
        700000),
       ('user', 2, '2023-04-07 15:00:00', '2023-04-08 11:00:00', 2, 700000, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP,
        700000);

-- Hotel 리뷰 데이터 입력

