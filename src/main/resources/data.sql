-- User 데이터 입력
insert into users (id, password, name, phone, email, role, grade)
values ('user', 'user', 'user', '010-1234-5678', 'user@example.com', 1, 1),
       ('user1', 'user1', 'user1', '010-1234-5678', 'user1@example.com', 1, 1),
       ('user2', 'user2', 'user2', '010-1234-5678', 'user2@example.com', 1, 1),
       ('user3', 'user3', 'user3', '010-1234-5678', 'user3@example.com', 1, 1),
       ('user4', 'user4', 'user4', '010-1234-5678', 'user4@example.com', 1, 1),
       ('admin', 'admin', '관리자', '010-0000-0000', 'admin@example.com', 0, 3);


-- Image 데이터 입력
insert into HOTELIMAGE (IMAGE)
values ('https://www.lottehotel.com/content/dam/lotte-hotel/signiel/seoul/main/230119-01-2000-mai-LTSG.jpg.thumb.1920.1920.jpg');


-- Hotel 데이터 입력

-- Hotel Room Type 데이터 입력
insert into HOTELROOMTYPE (HOTEL_IDX, NAME, DESCRIPTION, PRICE, DISCOUNT, PEOPLECOUNT, BEDSIZE, ROOMSIZE)
values (1, '로얄 스위트 룸',
        '시그니엘 서울의 100층에 위치한 로얄스위트 룸은 세계 각국의 국빈과 국내외 VVIP를 위한 시그니엘 서울의 단 하나뿐인 최고급 객실입니다.
        럭셔리한 응접실과 회의실은 물론 비서관 전용 객실, 자쿠지 등 최신 시설과 보안 시스템을 갖추고 있으며 시그니엘 서울만의 세심하고 격조 높은 서비스는 잊지 못할 감동을 선사합니다.'
           , 2500000, 0, 2, 2, 353);



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
insert into HOTELRESERVATION (USER_ID, HOTELROOM_IDX, CHECKINDATE, CHECKOUTDATE, PEOPLECOUNT, TOTALPRICE, CREATEDAT, UPDATEDAT, PAYEDMONEY)
values ('user', 1, '2023-04-07 15:00:00', '2023-04-08 11:00:00', 2, 700000, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 700000),
       ('user', 2, '2023-04-07 15:00:00', '2023-04-08 11:00:00', 2, 700000, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 700000);

-- Hotel 리뷰 데이터 입력

