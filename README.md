# CRUD Memo Back (`src`)

Spring Boot 기반의 간단한 **메모(게시글) CRUD 백엔드**입니다.  
기본 엔드포인트는 `BoardRestController`의 `/api/board` 입니다.

## 기술 스택

- **Java**: 17
- **Spring Boot**: 3.4.4
- **Build**: Gradle
- **DB**: MySQL (JDBC Driver: `com.mysql.cj.jdbc.Driver`)
- **ORM/Mapper**
  - **JPA**: 저장/수정/삭제(`BoardRepository`)
  - **MyBatis**: 조회(`BoardMapper` + `resources/mapper/BoardMapper.xml`)
- **Swagger UI**: springdoc-openapi
- **환경변수 로딩**: `spring-dotenv` (DB 계정정보를 환경변수로 주입)

## 프로젝트 구조 (핵심)

- `src/main/java/com/example/crudmemoback/controller/BoardRestController.java`
  - REST API 라우팅(`/api/board`)
- `src/main/java/com/example/crudmemoback/service/BoardService.java`
- `src/main/java/com/example/crudmemoback/service/impl/BoardServiceImpl.java`
  - Create/Update/Delete는 JPA(`BoardRepository`)
  - Read는 MyBatis(`BoardMapper`)
- `src/main/java/com/example/crudmemoback/entity/Board.java`
  - `@EnableJpaAuditing` + `@CreatedDate`로 `createdAt` 자동 생성
- `src/main/java/com/example/crudmemoback/mapper/BoardMapper.java`
- `src/main/resources/mapper/BoardMapper.xml`
  - `getBoard(boardID)`, `getBoardList()`
- `src/main/resources/application.yaml`
  - DB 연결 및 JPA 설정

## 실행 준비

### 1) DB 준비

`application.yaml` 기준으로 다음 DB를 사용합니다.

- **DB URL**: `jdbc:mysql://localhost:3306/testcrud_db?characterEncoding=UTF-8&serverTimezone=Asia/Seoul`
- **계정/비밀번호**: `DB_USERNAME`, `DB_PASSWORD` 환경변수로 주입
- **DDL**: `spring.jpa.hibernate.ddl-auto=update`
  - 앱 실행 시 `Board` 엔티티 기준으로 테이블이 자동 반영될 수 있습니다.

### 2) 환경변수 설정

이 프로젝트는 `application.yaml`에서 다음 값을 참조합니다.

- `DB_USERNAME`
- `DB_PASSWORD`

개발 편의를 위해 프로젝트 루트(예: `CRUD-Memo-Back/`)에 `.env` 파일을 두고 아래처럼 설정해도 됩니다( `spring-dotenv` 사용).

```bash
DB_USERNAME=your_db_user
DB_PASSWORD=your_db_password
```

## 실행 방법

프로젝트 루트에서 실행합니다.

```bash
./gradlew bootRun
```

또는 빌드 후 실행합니다.

```bash
./gradlew build
java -jar build/libs/*.jar
```

## Swagger UI

의존성에 Swagger UI가 포함되어 있습니다.

- 확인: `http://localhost:8080/swagger-ui/index.html`

## API

Base URL: `http://localhost:8080`

### 1) 게시글 생성

- **POST** `/api/board`
- **Request Body** (`BoardDto.CreateReqBoard`)
  - `userName` (string)
  - `title` (string)
  - `contents` (string)
- **Response** (`BoardDto.ResBoard`)
  - `boardID` (string)

예시:

```bash
curl -X POST "http://localhost:8080/api/board" \
  -H "Content-Type: application/json" \
  -d '{"userName":"lyh","title":"hello","contents":"first memo"}'
```

### 2) 게시글 전체 조회

- **GET** `/api/board`
- **Response**: `List<BoardDto.DetailResBoard>`
  - `boardID`, `userName`, `title`, `contents`, `createdAt`

예시:

```bash
curl "http://localhost:8080/api/board"
```

> 참고: 현재 구현은 MyBatis `getBoardList()`에서 **ID 목록만 조회**한 뒤, 각 ID에 대해 `getBoard(id)`를 다시 호출해 상세를 조합합니다(구현 단순화 목적). 데이터가 많아지면 N+1 형태가 될 수 있습니다.

### 3) 게시글 단건 조회

- **GET** `/api/board/{id}`
- **Response** (`BoardDto.DetailResBoard`)
  - `boardID`, `userName`, `title`, `contents`, `createdAt`

예시:

```bash
curl "http://localhost:8080/api/board/1"
```

### 4) 게시글 수정

- **PATCH** `/api/board/{id}`
- **Request Body** (`BoardDto.UpdateReqBoard`)
  - `userName` (string, optional)
  - `title` (string, optional)
  - `contents` (string, optional)
- **Response**: 없음(HTTP 200)

예시:

```bash
curl -X PATCH "http://localhost:8080/api/board/1" \
  -H "Content-Type: application/json" \
  -d '{"title":"updated title","contents":"updated contents"}'
```

### 5) 게시글 삭제

- **DELETE** `/api/board/{id}`
- **Response**: `"Deleted Successfully"`

예시:

```bash
curl -X DELETE "http://localhost:8080/api/board/1"
```

