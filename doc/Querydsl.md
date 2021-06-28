# Querydsl

## 정의

SQL 쿼리를 사용할 수 있는 라이브러리.

<br>

## 용도

복잡한 Query를 사용할 수 있음.
`JPA의 쿼리 메서드, @Query`를 통해서 DB 데이터 SQL 처리를 할 수 있지만, 복잡한 조합은 사용하기 어려움.

http://www.querydsl.com/

<br>

## gradle 설정

build.gradle

```gradle

plugins {
    id 'com.ewerk.gradle.plugins.querydsl' version '1.0.10'
}

...

dependencies {
    implementation 'com.querydsl:querydsl-jpa'
}

...

def querydslDir = "$buildDir/generated/querydsl"

querydsl {
    jpa = true
    querydslSourcesDir = querydslDir
}

sourceSets {
    main.java.srcDir querydslDir
}

configurations {
    querydsl.extendsFrom compileClasspath
}

compileQuerydsl {
    options.annotationProcessorPath = configurations.querydsl
}

```

VS CODE의 경우, 좌측 Gradle > GRADLE TASKS > other > compileQuerydsl 실행.
Project > build > generated > querydsl 폴더에 Entity class들의 java 파일이 생기며 파일이름 앞에 `Q`가 붙어있는 것을 확인.

<br>

만일 정상적으로 regdate와 moddate 컬럼에 값이 들어가지 않는 결과가 발생했다면 다음과 같은 항목을 체크

* BaseEntity의 내용 확인
* Guestbook 클래스의 상속 선언 부분
* @EnableJpaAuditing의 적용 여부
