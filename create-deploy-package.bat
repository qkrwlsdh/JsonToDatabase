@echo off
chcp 65001 >nul 2>&1
setlocal enabledelayedexpansion

echo ============================================
echo JSON to Database - 배포 패키지 생성
echo ============================================

REM 배포 폴더 생성
if not exist "JTD1.0" mkdir JTD1.0
if not exist "JTD1.0\config" mkdir JTD1.0\config
if not exist "JTD1.0\samples" mkdir JTD1.0\samples

REM 기존 파일 정리
if exist "JTD1.0\*" del /q "JTD1.0\*"
if exist "JTD1.0\config\*" del /q "JTD1.0\config\*"
if exist "JTD1.0\samples\*" del /q "JTD1.0\samples\*"

echo 배포 패키지를 생성하고 있습니다...

REM JAR 파일 복사
if exist "build\libs\JsonToDatabase-1.0.0.jar" (
    copy "build\libs\JsonToDatabase-1.0.0.jar" "JTD1.0\" >nul
    echo ✅ JAR 파일 복사 완료
) else (
    echo ❌ JAR 파일이 없습니다. 먼저 빌드하세요: gradlew.bat build
    pause
    exit /b 1
)

REM 실행 파일 복사
copy "deploy-run.bat" "JTD1.0\run.bat" >nul
echo ✅ 실행 파일 복사 완료

REM 설정 파일 복사
copy "src\main\resources\application.properties" "JTD1.0\config\" >nul
if exist "src\main\resources\application-prod.properties" (
    copy "src\main\resources\application-prod.properties" "JTD1.0\config\" >nul
)
echo ✅ 설정 파일 복사 완료

REM 데이터베이스 스크립트 복사
copy "테이블생성.sql" "JTD1.0\" >nul
echo ✅ 데이터베이스 스크립트 복사 완료

REM 샘플 파일 복사
if exist "sample-new-structure.json" copy "sample-new-structure.json" "JTD1.0\samples\" >nul
if exist "output_sample_*.json" copy "output_sample_*.json" "JTD1.0\samples\" >nul
echo ✅ 샘플 파일 복사 완료

REM 배포 가이드 복사
copy "DEPLOY_README.md" "JTD1.0\" >nul
echo ✅ 배포 가이드 복사 완료

REM 라이센스 파일 복사
copy "LICENSE" "JTD1.0\" >nul
echo ✅ 라이센스 파일 복사 완료

echo.
echo ============================================
echo 배포 패키지 생성 완료!
echo ============================================
echo.
echo 배포 폴더: JTD1.0\
echo.
echo 폴더 구조:
echo JTD1.0\
echo ├── JsonToDatabase-1.0.0.jar
echo ├── run.bat
echo ├── 테이블생성.sql
echo ├── DEPLOY_README.md
echo ├── LICENSE
echo ├── config\
echo │   ├── application.properties
echo │   └── application-prod.properties
echo └── samples\
echo     └── sample-new-structure.json
echo.
echo 🚀 이제 JTD1.0 폴더를 다른 PC에 복사하여 사용하세요!
echo.
pause
