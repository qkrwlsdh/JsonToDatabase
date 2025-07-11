# 한글 인코딩 문제 해결 가이드

## 🚨 문제: CMD에서 한글이 깨짐

Windows CMD에서 배치 파일을 실행할 때 한글이 깨지는 문제가 발생합니다.

## ✅ 해결 방법

### 방법 1: 배치 파일 사용 (권장)
```bash
run.bat sample_1.json
```

### 방법 2: CMD 인코딩 변경 후 실행
```bash
chcp 65001
run.bat sample_1.json
```

### 방법 3: PowerShell 사용
```powershell
java -Dfile.encoding=UTF-8 -Dconsole.encoding=UTF-8 -jar build\libs\JsonToDatabase-1.0.0.jar sample_1.json
```

### 방법 4: 직접 JAR 실행 (UTF-8 옵션 포함)
```bash
java -Dfile.encoding=UTF-8 -Dconsole.encoding=UTF-8 -jar build\libs\JsonToDatabase-1.0.0.jar sample_1.json
```

### 방법 5: IDE에서 실행 (개발 시 권장)
1. IntelliJ IDEA, Eclipse, VS Code 등에서 프로젝트 열기
2. `JsonToDatabaseApplication.java` 실행
3. Program arguments: `sample_1.json`

## 📋 배치 파일

- `run.bat` - UTF-8 인코딩 옵션 포함 ✅

## 🔧 Spring Boot 설정

### application.properties에 추가된 한글 인코딩 설정:
```properties
# Korean character encoding support
server.servlet.encoding.charset=UTF-8
server.servlet.encoding.enabled=true
server.servlet.encoding.force=true
spring.http.encoding.charset=UTF-8
spring.http.encoding.enabled=true
spring.http.encoding.force=true

# Logging encoding
logging.charset.console=UTF-8
logging.charset.file=UTF-8
logging.file.encoding=UTF-8
spring.output.ansi.enabled=never
```

### logback-spring.xml 설정:
- 콘솔 및 파일 출력 모두 UTF-8 인코딩 강제 설정
- 로그 파일 자동 롤링 및 보관 정책 설정

## 🎯 결론

가장 안정적인 방법은 `run.bat`를 사용하는 것입니다.
배치 파일에 `-Dfile.encoding=UTF-8 -Dconsole.encoding=UTF-8` 옵션이 포함되어 있어 한글이 정상적으로 출력됩니다.
