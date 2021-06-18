# practice-spring
스프링부트 파악해보기

<br>

# 내 것으로 만들어야 할 것
* 스프링부트 프로젝트 만드는 방법
* Spring Data JPA
* 스프링 MVC
* Thymeleaf
* N:1, M:N 관계형 데이터베이스 다루기
* Spring Security

<br>

# 포스팅 전까지 기록

## 스프링부트를 사용하는 이유?
스프링 프레임워크의 모든 기능을 활용하여 빠르게 개발하기 위함

<br>

## 프로젝트 환경 설정
IDE
* VS CODE

초기 설정에 사용하는 plugin
* Spring Initializr Java Support

### Plugin list
* Spring Boot Tools
  * 편집 창에서 프로그램을 빠르게 실행시킬 수 있음
  * application.properties 자동 완성

### Spring Data JPA 설치 후 서버 실행 시 에러
콘솔 로그 내용
```
Error starting ApplicationContext. To display the conditions report re-run your application with 'debug' enabled.
2021-06-15 21:17:17.575 ERROR 1232 --- [  restartedMain] o.s.b.d.LoggingFailureAnalysisReporter   :

***************************
APPLICATION FAILED TO START
***************************

Description:

Failed to configure a DataSource: 'url' attribute is not specified and no embedded datasource could be configured.

Reason: Failed to determine a suitable driver class


Action:

Consider the following:
        If you want an embedded database (H2, HSQL or Derby), please put it on the classpath.
        If you have database settings to be loaded from a particular profile you may need to activate it (no profiles are currently active).
```
To do
* DB(이 프로젝트에선 MariaDB) 연결을 위한 JDBC 드라이버 설치 필요
* 프로젝트 내 DB 설정

https://mvnrepository.com/artifact/org.mariadb.jdbc/mariadb-java-client/2.7.0
* 구글에서 maven mariadb 검색, 버전은 개발 버전 외 최신 버전 선택
탭 중(Maven, Gradle, SBT, ...) Gradle 선택하여 설정 값 복사
```
// https://mvnrepository.com/artifact/org.mariadb.jdbc/mariadb-java-client
implementation group: 'org.mariadb.jdbc', name: 'mariadb-java-client', version: '2.7.0'
```
build.gradle > dependencies에 추가

<br>

application.properties에 추가
```
spring.datasource.driver-class-name=org.mariadb.jdbc.Driver # 드라이버 이름
spring.datasource.url=jdbc:mariadb://localhost:3306/movieex # 호스트 + 포트 + DB 이름
spring.datasource.username=XXX # DB에서 설정한 사용자 이름
spring.datasource.password=XXX # DB에서 설정한 사용자 암호
```

다시 서버 실행 후 아래처럼 에러없이 정상적으로 실행되었는지 확인
```
Started ReviewApplication in 1.684 seconds (JVM running for 1.966)
```

<br>

```
[  restartedMain] com.zaxxer.hikari.HikariDataSource       : HikariPool-1 - Starting...
[  restartedMain] com.zaxxer.hikari.HikariDataSource       : HikariPool-1 - Start completed.
```
HikariPool은 스프링 부트가 기본적으로 이용하는 커넥션 풀(Connection Pool)이 HikariCP 라이브러리를 자동으로 설정함
* VS Code > 좌측 Explorer > 하단 JAVA PROJECTS 탭 > Project and External Dependencies에서 자동으로 설정된 라이브러리 확인 가능

<br>

### 프로젝트 실행과 동시에 DB 테이블 생성
application.properties
```
# 프로젝트 실행 시 자동으로 DDL 생성 (ddl-auto type = create, update, create-drop, validate)
# update: 변경이 필요한 경우 alter, 테이블이 없는 경우 create 동작
spring.jpa.hibernate.ddl-auto=update

# 실행되는 SQL을 포맷을 갖춘 상태로 출력
spring.jpa.properties.hibernate.format_sql=true

# JPA 처리할 때 SQL 출력 여부
spring.jpa.show-sql=true
```

`@SpringBootApplication`로 설정된 main class에 Entity class가 모인 기본 패키지 경로 설정하기
@EntityScan(basePackages = {"com.yahma.entity"}
이 설정이 되어 있지 않으면 DB 테이블 생성 동작을 하지 않음

<br>

# 라이브러리
build.gradle에 수동으로 추가 가능

<br>

Spring Data JPA - implementation 'org.springframework.boot:spring-boot-starter-data-jpa'

<br>

## 기타

### JSON
JSON 타입의 데이터를 사용하기 위해선 Jackson-databind 같은 라이브러리가 필요한데 Spring Web 의존성을 추가하면 자동으로 추가되므로 별도의 설치가 필요없음

<br>

# 참고 문서
https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/

<br>
<br>

# 키워드

<br>

## ORM(Object Relational Mapping)
객체지향 프로그래밍 언어에서 관계형 데이터베이스의 데이터를 객체처럼 사용 할 수 있도록 매핑하여 이용 할 수 있는 기술

비슷한 개념으로 매칭되는 대상
* 클래스 : 테이블
* 객체간의 관계 : 테이블간의 관계
* 인스턴스 : 레코드(튜플)

<br>

## JPA(Java Persistence API)
Java로 ORM을 사용 할 수 있는 스펙
가장 유명한 프레임워크는 `Hibernate`, 스프링부트가 사용하고 있음.
`Spring Data JPA`는 `Hibernate`를 쉽게 사용 할 수 있는 추가 API를 제공함.
`Spring Data JPA <-> Hibernate <-> JDBC <-> DB`.

<br>
<br>

# Anotaion

<br>

## Spring

<br>

### @Autowired
### @SpringBootTest
### @Test

<br>

## RestAPI

<br>

### @RestController

<br>
<br>

## DB 관련 (persistence)

<br>

### @Entity
JPA로 관리되는 앤티티 객체이며, 엔티티를 위한 클래스라고 설정

<br>

### @Table(name = "테이블 이름")
테이터베이스상에서 해당 클래스를 어떤 테이블로 생성할 것인지 설정

<br>

### @ToString
### @Id
기본키(Primary Key)가 되는 필드로 설정

<br>

### @GeneratedValue(strategy = GenerationType.IDENTITY)
번호가 자동으로 생성되도록 설정.
strategy: 키 생성 전략
GenerationType
* AUTO(default): JPA 구현체(Hibernate)가 생성 방식 결정
* IDENTITY: 데이터베이스가 키 생성을 결정.  MySQL/MariaDB는 `auto increment`기능을 사용
* SEQUENCE: 데이터베이스의 sequence 기능 사용. @SequnceGenerator와 같이 사용
* TABLE: 키 생성 전용 테이블을 생성하여 키를 생성. @TableGenerator와 같이 사용

<br>

### @Column
일반 컬럼으로 사용할 필드로 설정

<br>
<br>

## Lombok

<br>

### @ToString
### @Getter
### @Builder
### @AllArgsConstructor
### @NoArgsConstructor
### Transient

<br>
<br>

# VS Code 사용 중 문제가 발생한 경우
## 캐시 비우기
Ctrl + Shift + P - `Java: Clean Java Language Server Workspace` 실행
