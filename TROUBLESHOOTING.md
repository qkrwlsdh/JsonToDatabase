# 트러블슈팅 가이드

## 🚨 자주 발생하는 문제들

### 1. SQL 예약어 충돌 오류

```
Error: 1054-42S22: Unknown column 'pi1_0.use' in 'field list'
```

**원인**: `use`가 MariaDB/MySQL 예약어이므로 컬럼명으로 사용 시 문제 발생

**해결**:

- 테이블 스키마: `used VARCHAR(500)` (예약어가 아닌 컬럼명 사용)
- JSON에서는 여전히 `"use": "값"` 형태로 입력
- 애플리케이션에서 자동 매핑 처리

### 2. 데이터베이스 연결 오류

```
Could not create connection to database server
```

**해결 방법**:

1. MariaDB 서버가 실행 중인지 확인
2. `config/application.properties` 연결 정보 확인
3. 데이터베이스 `aiq` 존재 여부 확인
4. 방화벽 설정 확인

### 3. JSON 파일 경로 오류

```
파일이 존재하지 않습니다
```

**해결 방법**:

1. 파일 경로를 절대 경로로 지정
2. 현재 디렉토리에서 상대 경로 사용
3. 파일명 정확히 입력 (`sample-new-structure.json` 등)

### 4. 테이블이 존재하지 않는 오류

```
Table 'aiq.product_info' doesn't exist
```

**해결 방법**:

1. `테이블생성.sql` 파일 실행:
   ```sql
   mysql -u [사용자명] -p [데이터베이스명] < 테이블생성.sql
   ```
2. 또는 MariaDB 클라이언트에서 직접 실행:
   ```sql
   USE [데이터베이스명];
   CREATE TABLE product_info (...);
   ```

### 5. 새로운 JSON 구조 파싱 오류

```
JSON 파일에 annotation 배열이 없거나 비어있습니다
```

**원인**: 새로운 JSON 구조가 올바르지 않음

**올바른 JSON 구조**:

```json
{
  "Liscense": "유클리드소프트",
  "annotation": [
    {
      "meta_data_info": {
        "id": 1,
        "doc_file_name": "파일명",
        "product_id": "1",
        "kc_date": "20010829",
        "e_name": "제품명",
        "be_class": null,
        "re_class": "분류",
        "add_class": "",
        "use": "용도",
        "product_country": "국가",
        "field": "분야",
        "kc_type": "타입",
        "test": "테스트정보",
        "description": null
      },
      "source_data_info": {
        "annotation": {
          "question": "질문",
          "answer": "답변",
          "catetgory": "기본형",
          "Statutory provisions": "법적조항"
        }
      }
    }
  ]
}
```

### 6. 한글 인코딩 문제

배치 파일에서 한글이 깨져 보이는 경우

**해결 방법**:

1. **UTF-8 인코딩 설정** (자동 적용됨):

   ```bash
   run.bat sample-new-structure.json
   ```

2. **CMD 인코딩 확인**:

   ```bash
   chcp 65001
   ```

3. **IDE에서 직접 실행** (권장):

   - IntelliJ IDEA, Eclipse, VS Code 등에서 실행

4. **PowerShell 사용**:
   ```powershell
   java -jar JsonToDatabase-1.0.0.jar sample-new-structure.json
   ```

### 7. ID 중복 시 UPDATE 동작

```
같은 ID로 데이터를 다시 삽입하면 기존 데이터가 업데이트됨
```

**현재 동작**: JPA의 `save()` 메서드 사용으로 인해

- 새로운 ID → INSERT
- 기존 ID → UPDATE (덮어쓰기)

**INSERT만 원하는 경우**:

1. JSON에서 `id` 필드 제거
2. 데이터베이스에서 AUTO_INCREMENT 설정

### 8. JSON 데이터 타입 변환 오류

```
product_id 변환 실패: "문자열값"
kc_date 변환 실패: "잘못된날짜"
```

**해결**: JSON 데이터 확인

- `product_id`: 숫자 문자열이어야 함 (`"1", "2"` 등)
- `kc_date`: 8자리 날짜 형식 (`"20010829"` 등)

## 🔧 디버깅 방법

### 1. 로그 레벨 조정

`config/application.properties`에서:

```properties
logging.level.org.hibernate.SQL=DEBUG
logging.level.org.hibernate.type=TRACE
logging.level.com.example.jsontodb=DEBUG
```

### 2. 테이블 생성 확인

```sql
-- MariaDB에서 직접 확인
USE aiq;
SHOW TABLES;
DESCRIBE product_info;
```

### 3. 데이터 확인

```sql
-- 삽입된 데이터 확인
SELECT * FROM product_info ORDER BY id;
SELECT COUNT(*) as total_records FROM product_info;
```

### 4. 새로운 JSON 구조 검증

JSON 파일이 올바른 구조인지 확인:

```bash
# PowerShell에서 JSON 구조 확인
Get-Content sample-new-structure.json | ConvertFrom-Json
```

## 🎯 성공적인 실행 예시

```bash
# 1. 데이터베이스 준비
mysql -u [사용자명] -p
CREATE DATABASE IF NOT EXISTS [데이터베이스명] CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE [데이터베이스명];

# 2. 테이블 생성
mysql -u [사용자명] -p [데이터베이스명] < 테이블생성.sql

# 3. 애플리케이션 빌드
gradlew.bat clean build -x test

# 4. 실행
run.bat sample-new-structure.json
```

## 📋 성공 시 예상 로그

```
============================================
JSON to Database - Spring Boot Application
============================================
JSON 파일: sample-new-structure.json
처리 시작...

14:XX:XX INFO  - JSON 파일 처리 시작: sample-new-structure.json
14:XX:XX INFO  - 새로운 JSON 구조 처리 중: 2 개의 annotation 항목
14:XX:XX INFO  - ProductInfo 저장됨: ID=1
14:XX:XX INFO  - ProductInfo 저장됨: ID=2
14:XX:XX INFO  - 새로운 JSON 구조 처리 완료: 2 개의 레코드 처리됨

처리 완료
```

## 📞 지원 정보

문제 발생 시 다음 정보와 함께 문의:

- Java 버전 (`java -version`)
- MariaDB 버전
- 실행한 명령어
- 전체 오류 로그
- 사용한 JSON 파일 구조
