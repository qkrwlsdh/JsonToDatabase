package com.example.jsontodb.service;

import com.example.jsontodb.dto.JsonDataDto;
import com.example.jsontodb.entity.ProductInfo;
import com.example.jsontodb.repository.ProductInfoRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
public class JsonProcessingService {
    
    private static final Logger logger = LoggerFactory.getLogger(JsonProcessingService.class);
    
    @Autowired
    private ProductInfoRepository productInfoRepository;
    
    private final ObjectMapper objectMapper;
    
    public JsonProcessingService() {
        this.objectMapper = new ObjectMapper();
        this.objectMapper.registerModule(new JavaTimeModule());
    }
    
    /**
     * JSON 파일을 읽어서 데이터베이스에 삽입
     * @param filePath JSON 파일 경로
     * @return 처리된 레코드 수
     */
    public int processJsonFile(String filePath) {
        try {
            logger.info("JSON 파일 처리 시작: {}", filePath);
            
            // 기존 데이터 삭제
            deleteAllData();
            
            // 파일 존재 확인
            File jsonFile = new File(filePath);
            if (!jsonFile.exists()) {
                logger.error("파일이 존재하지 않습니다: {}", filePath);
                throw new RuntimeException("파일이 존재하지 않습니다: " + filePath);
            }
            
            // 새로운 JSON 구조 처리 - 단일 객체로 읽기
            JsonDataDto jsonData = objectMapper.readValue(jsonFile, JsonDataDto.class);
            
            if (jsonData.getAnnotationList() != null && !jsonData.getAnnotationList().isEmpty()) {
                return processNewJsonStructure(jsonData);
            } else {
                logger.warn("JSON 파일에 annotation 배열이 없거나 비어있습니다.");
                return 0;
            }
            
        } catch (IOException e) {
            logger.error("JSON 파일 처리 중 오류 발생: {}", e.getMessage(), e);
            throw new RuntimeException("JSON 파일 처리 중 오류가 발생했습니다: " + e.getMessage(), e);
        }
    }
    
    /**
     * 새로운 JSON 구조 처리 (annotation 배열 포함)
     */
    private int processNewJsonStructure(JsonDataDto jsonData) {
        logger.info("새로운 JSON 구조 처리 중: {} 개의 annotation 항목", jsonData.getAnnotationList().size());
        
        int processedCount = 0;
        
        for (JsonDataDto.AnnotationItemDto annotationItem : jsonData.getAnnotationList()) {
            ProductInfo productInfo = convertNewStructureToProductInfo(annotationItem);
            
            // 데이터베이스에 저장
            ProductInfo savedProduct = productInfoRepository.save(productInfo);
            logger.info("ProductInfo 저장됨: ID={}", savedProduct.getId());
            processedCount++;
        }
        
        logger.info("새로운 JSON 구조 처리 완료: {} 개의 레코드 처리됨", processedCount);
        return processedCount;
    }
    
    /**
     * 새로운 구조의 AnnotationItemDto를 ProductInfo 엔티티로 변환
     */
    private ProductInfo convertNewStructureToProductInfo(JsonDataDto.AnnotationItemDto annotationItem) {
        ProductInfo productInfo = new ProductInfo();
        
        // meta_data_info 데이터 설정
        if (annotationItem.getMetaDataInfo() != null) {
            JsonDataDto.MetaDataInfoDto metaData = annotationItem.getMetaDataInfo();
            productInfo.setId(metaData.getId());
            productInfo.setDocFileName(metaData.getDocFileName());
            
            // productId 변환 (String → Long)
            if (metaData.getProductId() != null) {
                try {
                    productInfo.setProductId(Long.parseLong(metaData.getProductId()));
                } catch (NumberFormatException e) {
                    logger.warn("product_id 변환 실패: {}", metaData.getProductId());
                    productInfo.setProductId(null);
                }
            }
            
            // kcDate 변환 (String → Long)
            if (metaData.getKcDate() != null) {
                try {
                    // "20010829" 형식을 Long으로 변환
                    productInfo.setKcDate(Long.parseLong(metaData.getKcDate()));
                } catch (NumberFormatException e) {
                    logger.warn("kc_date 변환 실패: {}", metaData.getKcDate());
                    productInfo.setKcDate(null);
                }
            }
            
            productInfo.seteName(metaData.geteName());
            productInfo.setBeClass(metaData.getBeClass());
            productInfo.setReClass(metaData.getReClass()); 
            productInfo.setAddClass(metaData.getAddClass());
            productInfo.setUsed(metaData.getUse());
            productInfo.setProductCountry(metaData.getProductCountry());
            productInfo.setField(metaData.getField());
            productInfo.setKcType(metaData.getKcType());
            productInfo.setTest(metaData.getTest());
            productInfo.setDescription(metaData.getDescription());
        }
        
        // source_data_info의 annotation 데이터 설정
        if (annotationItem.getSourceDataInfo() != null && 
            annotationItem.getSourceDataInfo().getAnnotation() != null) {
            JsonDataDto.SourceAnnotationDto annotation = annotationItem.getSourceDataInfo().getAnnotation();
            productInfo.setQuestion(annotation.getQuestion());
            productInfo.setAnswer(annotation.getAnswer());
            productInfo.setCategory(annotation.getCategory());
            productInfo.setStatutoryProvisions(annotation.getStatutoryProvisions());
        }
        
        return productInfo;
    }
    
    /**
     * 모든 제품 정보 조회
     */
    public List<ProductInfo> getAllProducts() {
        return productInfoRepository.findAll();
    }
    
    /**
     * 데이터베이스의 모든 데이터 삭제
     */
    public void deleteAllData() {
        long count = productInfoRepository.count();
        if (count > 0) {
            productInfoRepository.deleteAll();
            logger.info("기존 데이터 {} 개 레코드를 삭제했습니다.", count);
        } else {
            logger.info("삭제할 기존 데이터가 없습니다.");
        }
    }
}
