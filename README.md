# JSON to Database

[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)
[![Java](https://img.shields.io/badge/Java-17%2B-orange.svg)](https://www.oracle.com/java/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.x-brightgreen.svg)](https://spring.io/projects/spring-boot)
[![MariaDB](https://img.shields.io/badge/MariaDB-10.x-blue.svg)](https://mariadb.org/)

Spring Boot를 사용하여 JSON 파일을 읽어서 MariaDB에 데이터를 삽입하는 프로젝트입니다.

## 저작권 정보

**Copyright (c) 2025 qkrwlsdh**

이 프로젝트는 MIT 라이선스 하에 배포됩니다. 자세한 내용은 [LICENSE](LICENSE) 파일을 참조하세요.

## 기능

- JSON 파일을 읽어서 MariaDB의 product_info 테이블에 데이터 삽입
- 새로운 JSON 구조 지원 (Liscense 필드와 annotation 배열 포함)
- 다중 데이터 처리 가능 (annotation 배열의 각 항목을 개별 레코드로 저장)
- 명령줄 인자로 JSON 파일 경로 지정
- JAR 파일로 실행 가능

## 지원하는 JSON 구조

### 새로운 JSON 구조 (현재 지원)

```json
{
  "Liscense": "유클리드소프트",
  "annotation": [
    {
      "meta_data_info": {
        "id": 1,
        "doc_file_name": "방송통신기자재 등 적합성평가 데이터",
        "product_id": "1",
        "kc_date": "20010829",
        "e_name": "디지털선택 호출장치",
        "be_class": null,
        "re_class": "가.디지털선택호출 장치의 기기 > 1)선박국용",
        "add_class": "",
        "used": "선박국용",
        "product_country": "일본",
        "field": "무선",
        "kc_type": "적합인증",
        "test": "KS X 3123(무선)\\nKS X 3140(EMC)",
        "description": null
      },
      "source_data_info": {
        "annotation": {
          "question": "질문 내용",
          "answer": "답변 내용",
          "catetgory": "기본형",
          "Statutory provisions": "법적 조항"
        }
      }
    }
  ]
}
```

이 구조에서는 `annotation` 배열의 각 항목이 하나의 레코드로 데이터베이스에 저장됩니다.

## 사전 요구사항

1. **Java 17 이상**
2. **MariaDB 서버**
3. **Gradle** (gradlew.bat 포함되어 있음)

## 데이터베이스 설정

1. MariaDB에 데이터베이스 생성:

```sql
CREATE DATABASE aiq;
```

2. 테이블 생성:

```sql
-- 테이블생성.sql 파일 실행
mysql -u aodata -p aiq < 테이블생성.sql
```

또는 MariaDB 클라이언트에서 직접 실행:

```sql
USE aiq;
-- 테이블생성.sql 파일 내용 복사하여 실행
```

3. `src/main/resources/application.properties` 파일에서 데이터베이스 연결 정보 수정:

```properties
spring.datasource.url=jdbc:mariadb://localhost:23307/aiq
spring.datasource.username=aodata
spring.datasource.password=aodata0901
```

## 빌드 방법

### Gradle 사용 (권장)

```bash
# 빌드
gradlew.bat build

# 테스트 제외하고 빌드 (더 빠름)
gradlew.bat build -x test
```

### IDE에서 직접 실행 (개발 시 권장)

1. IntelliJ IDEA, Eclipse, VS Code 등의 IDE에서 프로젝트 열기
2. `JsonToDatabaseApplication.java` 파일 실행
3. Program arguments에 JSON 파일 경로 입력

## 실행 방법

### 1. JAR 파일 실행 (빌드 완료 후)

```bash
# Gradle 빌드 후
java -jar build\libs\JsonToDatabase-1.0.0.jar sample-new-structure.json
```

### 2. 배치 파일 실행 (권장)

```bash
run.bat sample-new-structure.json
```

### 3. Gradle을 통한 직접 실행

```bash
gradlew.bat bootRun --args="sample-new-structure.json"
```

### 4. IDE에서 직접 실행 (개발 시 권장)

1. IDE에서 `JsonToDatabaseApplication.java` 실행
2. Program arguments: `sample-new-structure.json`

## JSON 파일 형식

### 단일 객체 형태:

```json
{
  "meta_data_info": {
    "id": 1,
    "doc_file_name": "방송통신기자재 등 적합성평가 데이터",
    "product_id": "1",
    "kc_date": "20010829",
    "e_name": "디지털선택 호출장치",
    "be_class": null,
    "re_class": "가.디지털선택호출 장치의 기기 > 1)선박국용",
    "add_class": "",
    "use": "선박국용",
    "product_country": "일본",
    "field": "무선",
    "kc_type": "적합인증",
    "test": "KS X 3123(무선)\\nKS X 3140(EMC)",
    "description": null
  },
  "source_data_info": {
    "annotation": {
      "question": "선박국용로 설명되는 디지털선택 호출장치는 평가 유형이 뭐야?",
      "answer": "선박국용으로 설명되는 디지털선택 호출장치은 적합인증 대상이며, 전파법 제58조의2제2항, 전파법 시행령 제77조의2, 방송통신기자재등의 적합성평가에 관한 고시 제5조~제7조에 따라 적합성평가를 받아야 합니다.",
      "catetgory": "기본형",
      "Statutory provisions": "전파법 제58조의2제2항, 전파법 시행령 제77조의2, 방송통신기자재등의 적합성평가에 관한 고시 제5조~제7조"
    }
  }
}
```

### 배열 형태:

```json
[
    {
        "meta_data_info": { ... },
        "source_data_info": {
            "annotation": { ... }
        }
    },
    {
        "meta_data_info": { ... },
        "source_data_info": {
            "annotation": { ... }
        }
    }
]
```

## 예시 실행

1. 새로운 구조 JSON 파일 처리:

```bash
java -jar build\libs\JsonToDatabase-1.0.0.jar sample-new-structure.json
```

2. 대용량 JSON 파일 처리:

```bash
java -jar build\libs\JsonToDatabase-1.0.0.jar output_sample_20270709.json
```

## 🚀 배포 방법

### 배포 패키지 생성:

```bash
# 1. 프로젝트 빌드
gradlew.bat build

# 2. 배포 패키지 생성
create-deploy-package.bat
```

### 배포 패키지 구성:

```
JTD1.0/
├── JsonToDatabase-1.0.0.jar     # 실행 파일
├── run.bat                       # 실행 배치 파일
├── 테이블생성.sql                # DB 테이블 생성 스크립트
├── DEPLOY_README.md              # 배포 가이드
├── config/
│   ├── application.properties    # 설정 파일
│   └── application-prod.properties
└── samples/
    ├── sample-new-structure.json     # 테스트용 샘플
    └── output_sample_20270709.json   # 대용량 샘플
```

### 다른 PC에 배포 시 필요사항:

1. **Java 17 이상** 설치
2. **MariaDB 서버** 설치 및 실행
3. **JTD1.0 폴더 전체** 복사
4. **설정 파일 수정** (데이터베이스 연결 정보)
5. **테이블 생성** (`테이블생성.sql` 실행)

## 데이터베이스 테이블 구조

### product_info 테이블:

```sql
CREATE TABLE product_info (
    id INT PRIMARY KEY,
    doc_file_name VARCHAR(100),
    product_id BIGINT,
    kc_date DATE,
    e_name VARCHAR(100),
    be_code VARCHAR(50),
    be_class VARCHAR(50),
    re_code VARCHAR(50),
    re_class VARCHAR(50),
    description TEXT,
    type VARCHAR(50),
    use_info VARCHAR(100),
    product_country VARCHAR(50),
    field VARCHAR(50),
    source VARCHAR(100),
    test TEXT,
    question TEXT,
    answer TEXT,
    category VARCHAR(50),
    statutory_provisions TEXT
) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
```

## 주요 특징

- **유연한 JSON 처리**: 단일 객체와 배열 모두 자동 인식하여 처리
- **날짜 처리**: LocalDate 타입으로 날짜 자동 변환
- **단일 테이블 구조**: 모든 데이터를 product_info 테이블에 저장
- **상세한 로깅**: 처리 과정의 모든 단계를 로그로 기록

## 주의사항

- 데이터베이스 연결 설정을 실제 환경에 맞게 수정해야 합니다.
- `spring.jpa.hibernate.ddl-auto=none` 설정으로 인해 테이블이 자동 생성되지 않으므로 `schema.sql`을 사용하여 미리 테이블을 생성해야 합니다.
- JSON 파일의 경로는 절대 경로로 지정하는 것을 권장합니다.
- 테이블 생성 후 애플리케이션을 실행하세요.

## 로그 확인

애플리케이션 실행 중 발생하는 로그를 통해 처리 과정을 확인할 수 있습니다.

## 문제 해결

1. **데이터베이스 연결 오류**: MariaDB 서버가 실행 중인지 확인하고 연결 정보를 점검하세요.
2. **JSON 파일 오류**: JSON 파일 형식이 올바른지 확인하세요.
3. **파일 경로 오류**: 지정한 JSON 파일이 존재하는지 확인하세요.
4. **DDL 생성 오류**: `use`는 MariaDB 예약어이므로 `usage_description` 컬럼명으로 변경했습니다.
5. **JSON 파싱 오류**: JSON 형식이 올바른지 확인하세요. 특히 문자열 값에서 쉼표 사용에 주의하세요.

### JSON 파일 검증 방법:

```bash
# 온라인 도구 사용
# https://jsonlint.com/에서 JSON 파일 내용 검증

# 또는 실제 JSON 파일로 테스트
java -jar build\libs\JsonToDatabase-1.0.0.jar sample-new-structure.json
```

## 알려진 이슈

- **MariaDB 예약어 문제**: `use` 컬럼명을 `usage_description`으로 변경하여 해결
- **한글 인코딩**: 콘솔에서 한글이 깨질 수 있으나 기능상 문제없음

## 기여하기 (Contributing)

1. 이 저장소를 Fork 합니다
2. 새로운 기능 브랜치를 생성합니다 (`git checkout -b feature/새기능`)
3. 변경사항을 커밋합니다 (`git commit -am '새 기능 추가'`)
4. 브랜치에 Push 합니다 (`git push origin feature/새기능`)
5. Pull Request를 생성합니다

## 라이선스 (License)

이 프로젝트는 **MIT 라이선스** 하에 배포됩니다. 자세한 내용은 [LICENSE](LICENSE) 파일을 참조하세요.

## 개발자 정보 (Developer)

- **개발자**: qkrwlsdh
- **GitHub**: [https://github.com/qkrwlsdh](https://github.com/qkrwlsdh)
- **프로젝트 저장소**: [https://github.com/qkrwlsdh/JsonToDatabase](https://github.com/qkrwlsdh/JsonToDatabase)

## 지원 (Support)

문제가 발생하거나 질문이 있으시면 [GitHub Issues](https://github.com/qkrwlsdh/JsonToDatabase/issues)에 등록해주세요.
