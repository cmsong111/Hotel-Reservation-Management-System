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

insert into HOTELIMAGE (HOTEL_IDX, IMAGE)
values (1, 'https://www.lottehotel.com/content/dam/lotte-hotel/signiel/seoul/main/230119-01-2000-mai-LTSG.jpg.thumb.1920.1920.jpg');


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

