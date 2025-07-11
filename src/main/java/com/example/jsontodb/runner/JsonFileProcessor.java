package com.example.jsontodb.runner;

import com.example.jsontodb.service.JsonProcessingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class JsonFileProcessor implements CommandLineRunner {
    
    private static final Logger logger = LoggerFactory.getLogger(JsonFileProcessor.class);
    
    @Autowired
    private JsonProcessingService jsonProcessingService;
    
    @Override
    public void run(String... args) throws Exception {
        logger.info("JsonFileProcessor 시작");
        
        if (args.length == 0) {
            logger.error("사용법: java -jar json-to-database.jar <json-file-path>");
            System.err.println("사용법: java -jar json-to-database.jar <json-file-path>");
            System.err.println("예시: java -jar json-to-database.jar C:\\data\\users.json");
            System.exit(1);
        }
        
        String jsonFilePath = args[0];
        logger.info("JSON 파일 경로: {}", jsonFilePath);
        
        try {
            int processedCount = jsonProcessingService.processJsonFile(jsonFilePath);
            logger.info("처리 완료: {} 개의 레코드가 데이터베이스에 저장되었습니다.", processedCount);
            System.out.println("처리 완료: " + processedCount + " 개의 레코드가 데이터베이스에 저장되었습니다.");
            
        } catch (Exception e) {
            logger.error("JSON 파일 처리 중 오류 발생: {}", e.getMessage(), e);
            System.err.println("오류 발생: " + e.getMessage());
            System.exit(1);
        }
        
        logger.info("JsonFileProcessor 종료");
    }
}
