@echo off
chcp 65001 >nul 2>&1
setlocal enabledelayedexpansion

echo ============================================
echo JSON to Database - Spring Boot Application
echo ============================================

REM 사용법 체크
if "%~1"=="" (
    echo 사용법: run.bat ^<json-file-path^>
    echo 예시: run.bat sample-new-structure.json
    echo       run.bat output_sample_20270709.json
    echo       run.bat C:\full\path\to\your\data.json
    echo.
    echo 사용 가능한 샘플 파일:
    if exist "sample-new-structure.json" echo   - sample-new-structure.json
    if exist "output_sample_*.json" echo   - output_sample_*.json
    pause
    exit /b 1
)

REM 파일 존재 확인
if not exist "%1" (
    echo 오류: 파일 '%1'이 존재하지 않습니다.
    echo 현재 디렉토리: %CD%
    echo.
    echo 사용 가능한 JSON 파일:
    dir *.json /b 2>nul
    pause
    exit /b 1
)

echo JSON 파일: %1
echo 처리 시작...
echo.

REM Java 클래스패스 설정
set CLASSPATH=.;src\main\java;src\main\resources

REM 필요한 JAR 파일 경로 확인
if exist "build\libs\JsonToDatabase-1.0.0.jar" (
    echo JAR 파일을 찾았습니다. JAR 파일로 실행합니다.
    java -Dfile.encoding=UTF-8 -Dconsole.encoding=UTF-8 -Dsun.stdout.encoding=UTF-8 -Dsun.stderr.encoding=UTF-8 -Duser.language=ko -Duser.country=KR -jar build\libs\JsonToDatabase-1.0.0.jar "%1"
    goto :end
)

echo.
echo JAR 파일이 없습니다. 빌드가 필요합니다.
echo.
echo 빌드 명령: gradlew.bat build
echo IDE에서 직접 실행하거나 위 명령으로 빌드하세요.
echo.

:end
echo.
echo 처리 완료
pause
