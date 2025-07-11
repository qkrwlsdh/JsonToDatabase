# JSON to Database - 배포 가이드

## 📦 배포 패키지 구성

다른 PC에 배포할 때 필요한 파일들:

### 필수 파일:

- `JsonToDatabase-1.0.0.jar` - 실행 파일
- `run.bat` - 실행 배치 파일
- `application.properties` - 설정 파일
- `테이블생성.sql` - 데이터베이스 테이블 생성 스크립트
- `DEPLOY_README.md` - 배포 가이드 (이 파일)

### 선택적 파일:

- `sample-new-structure.json` - 새로운 JSON 구조 테스트용 샘플 데이터
- `application-prod.properties` - 운영환경 설정 (필요시)

## 🔧 배포 전 준비사항

### 1. 프로젝트 빌드

```bash
gradlew.bat build
```

### 2. 배포 폴더 생성

```
JTD1.0/
├── JsonToDatabase-1.0.0.jar
├── run.bat
├── 테이블생성.sql
├── DEPLOY_README.md
├── config/
│   ├── application.properties
│   └── application-prod.properties
└── samples/
    └── sample-new-structure.json
```

## 🚀 배포 대상 PC 요구사항

### 필수 요구사항:

1. **Java 17 이상** 설치
2. **MariaDB 서버** 설치 및 실행
3. **Windows OS** (배치 파일 사용 시)

### Java 설치 확인:

```bash
java -version
```

## 📋 배포 후 설정 단계

### 1. 데이터베이스 설정

```sql
-- MariaDB에 접속하여 실행
CREATE DATABASE aiq;

-- 테이블 생성
mysql -u username -p aiq < 테이블생성.sql
```

### 2. 설정 파일 수정

`config/application.properties` 파일에서 데이터베이스 연결 정보 수정:

```properties
spring.datasource.url=jdbc:mariadb://localhost:3306/aiq
spring.datasource.username=your_username
spring.datasource.password=your_password
```

**중요**: Spring Boot는 자동으로 다음 위치에서 설정 파일을 찾습니다:

- `config/application.properties` (우선순위 높음)
- `application.properties` (현재 디렉토리)

**추가 설정**:

- `spring.main.banner-mode=off`: Spring Boot 시작 로고 비활성화
- `spring.jpa.open-in-view=false`: JPA 성능 최적화
- `spring.jpa.show-sql=false`: SQL 쿼리 로그 비활성화
- 로그 레벨: 애플리케이션 로그만 출력하도록 최적화

### 3. 실행 테스트

```bash
# 샘플 데이터로 테스트 (samples 폴더 내)
run.bat samples\sample-new-structure.json

# 또는 현재 폴더의 파일 사용
run.bat sample-new-structure.json

# 또는 직접 실행
java -jar JsonToDatabase-1.0.0.jar sample-new-structure.json
```

## 🔍 문제 해결

### Java 관련:

- Java 17 이상이 설치되어 있는지 확인
- JAVA_HOME 환경변수가 올바르게 설정되었는지 확인

### 데이터베이스 관련:

- MariaDB 서버가 실행 중인지 확인
- 데이터베이스 연결 정보가 올바른지 확인
- 방화벽 설정 확인

### 파일 관련:

- JSON 파일 경로가 올바른지 확인
- 파일 권한 확인

### 설정 파일 관련:

- `config/application.properties` 파일이 존재하는지 확인
- 설정 파일 내용이 올바른지 확인
- Spring Boot는 자동으로 config 폴더에서 설정 파일을 찾습니다

### 일반적인 오류:

- "파일이 존재하지 않습니다" 오류: JSON 파일 경로를 확인하세요
- "데이터베이스 연결 오류": MariaDB 서버 상태와 연결 정보를 확인하세요
- "테이블이 존재하지 않습니다": 테이블생성.sql을 사용하여 테이블을 생성하세요

### 로그 출력 최적화:

- 애플리케이션 로그(`logger.info`)만 출력됩니다
- Spring Boot, Hibernate 등 프레임워크 로그는 WARN 레벨 이상만 출력
- 콘솔 로그 형식: `HH:mm:ss LEVEL - 메시지`
- 파일 로그 형식: `yyyy-MM-dd HH:mm:ss [thread] LEVEL logger - 메시지`

## 📞 지원

문제가 발생하면 다음 정보와 함께 문의하세요:

- 운영체제 버전
- Java 버전
- MariaDB 버전
- 오류 메시지
- 실행 로그
