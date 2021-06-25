# 이 저장소를 만든 이유

최신 백엔드 트렌드 살펴보기.  
팀에서 프론트엔드 역할을 맡고 있다보니 백엔드 업무를 할 일은 없었지만, 백엔드에서 하는 일을 살펴보면서 웹 지식의 폭을 넓혀본다.

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

# 기능
## Page, Pageable, Sort
조회 결과를 특정 범위로 제한하고, 특정 조건으로 정렬하여 데이터 가져오기

<br>
<br>

## Query Method
class method 이름을 인터페이스에 선언하는 것만으로도 SQL 처리를 할 수 있는 기능

<br>

### 종류
https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#appendix.query.method.subject

<br>
<br>

# Thymeleaf
기본적으로 프로젝트 생성 시에 추가되는 templates 폴더를 기본 위치로 사용
* main/resources/...

<br>

## Format
### 숫자 범위 지정하기
```
<ul>
    <li th:each="dto : ${list}">
        [[${#numbers.formatInteger(dto.sno, 5)}]]
    </li>
</ul>
```

### 날짜 포맷
gradle에 `compile group 'org.thymeleaf.extras', name: 'thymeleaf-extras-java8time'` 추가 필요
```
<ul>
    <li th:each="dto : ${list}">
        [[${dto.sno}]] --- [[${#temporals.format(dto.regTime, 'yyyy/MM/dd')}]]
    </li>
</ul>
```

<br>

## Layout
### Fragment
```
<!--
replace: 현재 element와 교체
insert: 현재 element 자식에 추가
'~{/fragments/fragment1 :: part1}': ~(시작 문법) + templates 폴더 하위에 준비 해놓은 html 경로 + '::'(부분만 가져오고 싶은 경우 사용) + key 이름
-->
<div th:replace="~{/fragments/fragment1 :: part1}"></div>
<div th:insert="~{/fragments/fragment1 :: part2}"></div>
<th:block th:replace="~{/fragments/fragment1 :: part3}"></th:block>
```
```
<!--
fragment.html
fragment 키워드를 사용하여 외부에서 사용할 key 이름 지정
-->
<div th:fragment="part1">
    <h2>Part 1</h2>
</div>
<div th:fragment="part2">
    <h2>Part 2</h2>
</div>
<div th:fragment="part3">
    <h2>Part 3</h2>
</div>
```

<br>
<br>

# VS Code 사용 중 문제가 발생한 경우
## 캐시 비우기
Ctrl + Shift + P - `Java: Clean Java Language Server Workspace` 실행

## Lombok 기능을 인식하지 못할 때
Lombok Annotations Support for VS Code 설치
* https://marketplace.visualstudio.com/items?itemName=GabrielBB.vscode-lombok

## Querydsl 라이브러리 이용중 테스트코드 실패
plugin > Java Test Runner
`java.lang.ClassNotFoundException: xx.xxxx.xxxxxx.entity.Qxxx(gradle compileQuerydsl로 생성한 Q가 붙은 Entity class`
위와 같은 오류 발생 시, 버전 교체(일반적으론 다운그레이드)
0.29.0까지 정상 확인
* https://pjs21s.github.io/querydsl-vscode/
