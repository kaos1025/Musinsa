**개요  
  이 프로젝트는 Spring Boot 프레임워크를 기반으로 개발되었으며, 아래와 같은 API들로 구성됩니다.
    상품 조회/등록/수정/삭제 API
    브랜드 조회/등록/수정/삭제 API
    카테고리 등록 API
    과제에서 요청한 조회 API

**기능
  브랜드 관리: 새로운 패션 브랜드를 등록하고, 기존 브랜드 정보를 수정 및 삭제할 수 있습니다.
  상품 관리: 다양한 패션 아이템을 등록하고, 수정 및 삭제할 수 있습니다.
  상품 추천: 사용자가 원하는 상품을 키워드로 검색하고, 필터링된 결과를 확인할 수 있습니다.

**기술 스택
Backend	: Java, Spring Boot, Spring MVC
Database :	H2 Database (개발 및 테스트 환경), MySQL 또는 PostgreSQL (프로덕션 환경)
ORM	: MyBatis
Build Tool : Gradle
API 문서화 : Swagger (Springdoc OpenAPI)
테스트 : JUnit, Mockito


**프로젝트 구조 
Musinsa/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── example/
│   │   │           └── demo/
│   │   │               ├── config/
│   │   │               ├── controller/
│   │   │               ├── dto/ # 데이터 전송 객체 (DTO)
│   │   │               ├── exception/
│   │   │               ├── mapper/
│   │   │               ├── service/
│   │   │               └── DemoApplication.java
│   │   └── resources/
│   │       ├── static/
|   |       ├── mappers/
│   │       ├── templates/
│   │       └── application.properties
│   └── test/
│       └── java/
│           └── com/
│               └── example/
│                   └── demo/
│                       ├── controller/
│                       ├── mapper/
│                       ├── service/
│                       ├── BaseIntegrationTest.java
│                       └── DemoApplicationTests.java
├── build.gradle
└── settings.gradle

controller/: HTTP 요청을 처리하고, 사용자에게 응답을 반환하는 컨트롤러 클래스들이 위치합니다.
service/: 비즈니스 로직을 담당하는 서비스 클래스들이 위치합니다.
dto/ : 데이터 전송 객체(DTO)
exception/ : 예외 처리 관련 클래스
static/: 정적 자원(이미지, CSS, JavaScript 파일 등)이 위치합니다.
templates/: Thymeleaf 템플릿 파일들이 위치합니다.
application.properties: 애플리케이션의 설정 파일입니다.


**API 기능 설명
Item Management API - 상품 관리 API
  상품등록 / 수정 / 삭제 API
  상품조회 API
    - itemNo로 조회
    - 카테고리코드로 해당 카테고리코드로 등록된 상품정보 조회
    - 카테고리명으로 해당 카테고리코드로 등록된 상품정보 조회
    - 브랜드코드로 해당 브랜드로 등록된 상품정보 조회
    - 브랜드명으로 해당 브랜드로 등록된 상품정보 조회

카테고리 Management API - 카테고리 관리 API
  카테고리 등록 API
  
Brand Management API - 브랜드 관리 API
  브랜드 등록 / 수정 / 삭제 / 조회 API

코디 추천 API
  과제에서 요청한 조회 API 
  getLowestPriceItem - 카테고리 별 최저가 브랜드, 상품가격, 총액 조회
  getLowestPriceBrand - 단일 브랜드로 모든 카테고리 상품을 구매할 때 최저가격에 판매하는 브랜드와 카테고리의 상품가격, 총액 조회
  getCategoryPrice - 카테고리 이름으로 최저, 최고 가격 브랜드와 상품 가격을 조회하는 API


**코드 빌드
프로젝트 클론 
--------------------------------------------------------
git clone https://github.com/kaos1025/Musinsa.git
cd Musinsa
--------------------------------------------------------
빌드
--------------------------------------------------------
./gradlew clean build
--------------------------------------------------------

**테스트 (Test)
JUnit과 Mockito를 사용하여 단위 테스트 및 통합 테스트 실행
--------------------------------------------------------
bash
복사편집
./gradlew test
--------------------------------------------------------

**애플리케이션 실행 (Run)
Spring Boot 애플리케이션 실행
--------------------------------------------------------
bash
복사편집
./gradlew bootRun
--------------------------------------------------------

**웹 브라우저에서 접속 : [http://localhost:8080](http://localhost:8080/)**

**API 문서 (Swagger) 확인 : http://localhost:8080/swagger-ui/index.html
Swagger UI에서 API 목록을 확인하고, 직접 테스트 가능**

**API 테스트 방법
Swagger를 통해 API를 직접 호출할 수 있으며, Postman이나 cURL을 사용할 수도 있음**


**개선점 
1) Swagger에 접속하면 Failed to load API definition.라는 문구와 함께 아래 화면이 뜨는 오류
원인은 spring Boot에서 글로벌 예외 처리를 위한 어노테이션(@ControllerAdvice)과 Swagger 사이에 일어난 충돌로 수정 필요

2) Integration Test 추가 작성 필요 





