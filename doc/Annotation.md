# Annotation

## Spring

<br>

### @Autowired

### @SpringBootTest

### @Test

### @CreatedDate

### @LastModifiedDate

### @Service

<br>

## RestAPI

<br>

### @Controller

### @RestController

### @RequestMapping

### @GetMapping

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

### @Id

기본키(Primary Key)가 되는 필드로 설정

<br>

### @GeneratedValue(strategy = GenerationType.IDENTITY)

번호가 자동으로 생성되도록 설정.
strategy: 키 생성 전략
GenerationType

- AUTO(default): JPA 구현체(Hibernate)가 생성 방식 결정
- IDENTITY: 데이터베이스가 키 생성을 결정. MySQL/MariaDB는 `auto increment`기능을 사용
- SEQUENCE: 데이터베이스의 sequence 기능 사용. @SequenceGenerator와 같이 사용
- TABLE: 키 생성 전용 테이블을 생성하여 키를 생성. @TableGenerator와 같이 사용

<br>

### @Column

일반 컬럼으로 사용할 필드로 설정

<br>

### Query

필요한 데이터만 추출
순수 SQL(Native SQL) 사용 가능
Select, Insert, Update, Delete 가능 (@Modifying 사용)
@Entity 클래스의 멤버 변수 이용 가능

<br>

### @MappedSuperclass

테이블로 생성되지 않음

### @EntityListeners

#### AuditingEntityListener

```SpringBoot
ex)
@EntityListeners(value = { AuditingEntityListener.class })
```

JPA 내부에서 엔티티 객체가 생성/변경되는 것을 감지하는 역할.
활성화시키기 위해 `@EnableJpaAuditing` 설정을 Application class에 추가해야 함.

### @EnableJpaAuditing

@EntityListeners를 사용하기 위해 Application class에 설정.

### @ManyToOne

N:1 관계 설정. 작성 중인 class가 N, 대상이 되는 멤버가 1.

```Java
ex)
public class Board {
    @ManyToOne
    private Member member;
}
```

### @Transactional

해당 메서드를 하나의 `트랜잭션`으로 처리  
`FetchType.LAZY`으로 설정한 멤버가 pk로 있는 테이블 조인이 필요할 때 사용해야 함.

<br>
<br>

## Lombok

<br>

### @ToString

### @Getter

### @Data

### @Builder

### @AllArgsConstructor

### @NoArgsConstructor

### @RequiredArgsConstructor

### Transient
