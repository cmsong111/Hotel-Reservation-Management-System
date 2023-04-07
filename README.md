# 호텔 예약 관리 시스템
> Hotel Reservation Management System

## 프로젝트 소개

동의대학교 컴퓨터소프트웨어공학 소프트웨어 설계공학 과목에서 진행한 프로젝트입니다.
디자인 패턴을 적용하여 개발한 호텔 예약 관리 시스템입니다.

## 개발 환경

- IDE: NetBeans 17
- Java: oracle JDK 17
- Database: H2 embedded database 
- JPA: Hibernate 
- Build tool: Maven

## How to build

- Clone this repository
  ``` bash
  git clone https://github.com/cmsong111/Hotel-Reservation-Management-System.git
  ```
- Build the project
  ``` bash
  mvnw package
  ```
- Run the project
  ``` bash
  java -jar target/maven-unit-test-jar-with-dependencies.jar
  ```

## Used Design Patterns

- Singleton
- Builder
- Observer
- ~~Strategy~~
- Iterator
- ~~Decorator~~
