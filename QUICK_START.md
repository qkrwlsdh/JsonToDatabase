# 빠른 실행 가이드

## 🚀 가장 간단한 실행 방법 (IDE 사용)

### 1. IDE에서 프로젝트 열기

- **IntelliJ IDEA**: File → Open → JsonToDatabase 폴더 선택
- **Eclipse**: File → Import → Existing Maven Projects → JsonToDatabase 폴더 선택
- **VS Code**: File → Open Folder → JsonToDatabase 폴더 선택

### 2. IDE에서 실행

1. `src/main/java/com/example/jsontodb/JsonToDatabaseApplication.java` 파일 열기
2. 실행 설정에서 Program arguments에 JSON 파일 경로 입력:
   - `sample_1.json` (단일 객체)
   - `sample-array.json` (배열)
3. 실행 버튼 클릭

## 🔧 빌드 도구별 실행 방법

### Gradle 사용 (권장)

```bash
# 빌드
gradlew.bat build

# 실행
java -jar build/libs/json-to-database-1.0.0.jar sample-new-structure.json
```

## 🛠️ 개발 환경 설정

### 1. 데이터베이스 연결 설정

`src/main/resources/application.properties` 파일에서:

```properties
spring.datasource.url=jdbc:mariadb://localhost:3306/your_database_name
spring.datasource.username=your_username
spring.datasource.password=your_password
```

### 2. MariaDB 준비

```sql
CREATE DATABASE your_database_name;
-- 애플리케이션 실행 시 테이블은 자동 생성됩니다.
```

## 📁 샘플 파일

- `sample_1.json` - 단일 JSON 객체
- `sample-array.json` - JSON 배열
- `test-data.json` - 추가 테스트 데이터

## ⚡ 배치 파일 사용

```bash
# 간단한 실행
run.bat sample_1.json
```

## 🐛 문제 해결

### 빌드 도구가 없는 경우

1. **IDE 사용** (가장 간단)
2. **Gradle 설치**: https://gradle.org/install/

### 일반적인 오류

- **데이터베이스 연결 오류**: MariaDB 서버 상태 확인
- **JSON 파일 오류**: 파일 경로와 JSON 형식 확인
- **Java 버전**: Java 17 이상 필요
