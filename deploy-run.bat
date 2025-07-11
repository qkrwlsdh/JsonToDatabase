@echo off
chcp 65001 >nul 2>&1
setlocal enabledelayedexpansion

echo ============================================
echo JSON to Database - Spring Boot Application
echo ============================================

REM 사용법 체크
if "%~1"=="" (
    echo 사용법: run.bat ^<json-file-path^>
    echo 예시: run.bat samples\sample-new-structure.json
    echo       run.bat C:\full\path\to\your\data.json
    echo.
    echo 사용 가능한 샘플 파일:
    if exist "samples\sample-new-structure.json" echo   - samples\sample-new-structure.json
    if exist "samples\output_sample_*.json" echo   - samples\output_sample_*.json
    echo.
    echo 설정 파일 확인:
    if exist "config\application.properties" (
        echo   ✅ 설정 파일 발견: config\application.properties
    ) else (
        echo   ❌ 설정 파일 없음: config\application.properties
    )
    pause
    exit /b 1
)

REM 파일 존재 확인
if not exist "%1" (
    echo 오류: 파일 '%1'이 존재하지 않습니다.
    echo 현재 디렉토리: %CD%
    echo.
    echo 사용 가능한 JSON 파일:
    if exist "samples\*.json" (
        echo [samples 폴더]
        dir samples\*.json /b 2>nul
    )
    echo [현재 폴더]
    dir *.json /b 2>nul
    pause
    exit /b 1
)

echo JSON 파일: %1
echo 처리 시작...
echo.

REM 설정 파일 확인 (정보 표시용)
if exist "config\application.properties" (
    echo 설정 파일 사용: config\application.properties
) else (
    echo 경고: 설정 파일이 없습니다. 기본 설정을 사용합니다.
)

REM 필요한 JAR 파일 경로 확인
if exist "JsonToDatabase-1.0.0.jar" (
    echo JAR 파일을 찾았습니다. JAR 파일로 실행합니다.
    java -Dfile.encoding=UTF-8 -Dconsole.encoding=UTF-8 -Dsun.stdout.encoding=UTF-8 -Dsun.stderr.encoding=UTF-8 -Duser.language=ko -Duser.country=KR -jar JsonToDatabase-1.0.0.jar "%1"
    goto :end
)

echo.
echo JAR 파일이 없습니다. 다음을 확인하세요:
echo 1. JsonToDatabase-1.0.0.jar 파일이 현재 폴더에 있는지 확인
echo 2. 올바른 배포 패키지를 사용하고 있는지 확인
echo.

:end
echo.
echo 처리 완료
pause
