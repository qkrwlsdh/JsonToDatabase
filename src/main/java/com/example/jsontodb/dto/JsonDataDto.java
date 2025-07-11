package com.example.jsontodb.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

/**
 * 새로운 JSON 구조에 맞는 DTO
 * output_sample_20270709.json 파일 구조를 지원
 */
public class JsonDataDto {
    
    @JsonProperty("Liscense")
    private String license;
    
    @JsonProperty("annotation")
    private List<AnnotationItemDto> annotationList;
    
    // 기본 생성자
    public JsonDataDto() {
    }
    
    // Getters and Setters
    public String getLicense() {
        return license;
    }
    
    public void setLicense(String license) {
        this.license = license;
    }
    
    public List<AnnotationItemDto> getAnnotationList() {
        return annotationList;
    }
    
    public void setAnnotationList(List<AnnotationItemDto> annotationList) {
        this.annotationList = annotationList;
    }
    
    /**
     * annotation 배열의 각 항목을 나타내는 클래스
     */
    public static class AnnotationItemDto {
        
        @JsonProperty("meta_data_info")
        private MetaDataInfoDto metaDataInfo;
        
        @JsonProperty("source_data_info")
        private SourceDataInfoDto sourceDataInfo;
        
        // Getters and Setters
        public MetaDataInfoDto getMetaDataInfo() {
            return metaDataInfo;
        }
        
        public void setMetaDataInfo(MetaDataInfoDto metaDataInfo) {
            this.metaDataInfo = metaDataInfo;
        }
        
        public SourceDataInfoDto getSourceDataInfo() {
            return sourceDataInfo;
        }
        
        public void setSourceDataInfo(SourceDataInfoDto sourceDataInfo) {
            this.sourceDataInfo = sourceDataInfo;
        }
    }
    
    /**
     * meta_data_info 클래스
     */
    public static class MetaDataInfoDto {
        private Integer id;
        
        @JsonProperty("doc_file_name")
        private String docFileName;
        
        @JsonProperty("product_id")
        private String productId;
        
        @JsonProperty("kc_date")
        private String kcDate;
        
        @JsonProperty("e_name")
        private String eName;
        
        @JsonProperty("be_class")
        private String beClass;
        
        @JsonProperty("re_class")
        private String reClass;
        
        @JsonProperty("add_class")
        private String addClass;
        
        private String use;
        
        @JsonProperty("product_country")
        private String productCountry;
        
        private String field;
        
        @JsonProperty("kc_type")
        private String kcType;
        
        private String test;
        private String description;
        
        // Getters and Setters
        public Integer getId() { return id; }
        public void setId(Integer id) { this.id = id; }
        
        public String getDocFileName() { return docFileName; }
        public void setDocFileName(String docFileName) { this.docFileName = docFileName; }
        
        public String getProductId() { return productId; }
        public void setProductId(String productId) { this.productId = productId; }
        
        public String getKcDate() { return kcDate; }
        public void setKcDate(String kcDate) { this.kcDate = kcDate; }
        
        public String geteName() { return eName; }
        public void seteName(String eName) { this.eName = eName; }
        
        public String getBeClass() { return beClass; }
        public void setBeClass(String beClass) { this.beClass = beClass; }
        
        public String getReClass() { return reClass; }
        public void setReClass(String reClass) { this.reClass = reClass; }
        
        public String getAddClass() { return addClass; }
        public void setAddClass(String addClass) { this.addClass = addClass; }
        
        public String getUse() { return use; }
        public void setUse(String use) { this.use = use; }
        
        public String getProductCountry() { return productCountry; }
        public void setProductCountry(String productCountry) { this.productCountry = productCountry; }
        
        public String getField() { return field; }
        public void setField(String field) { this.field = field; }
        
        public String getKcType() { return kcType; }
        public void setKcType(String kcType) { this.kcType = kcType; }
        
        public String getTest() { return test; }
        public void setTest(String test) { this.test = test; }
        
        public String getDescription() { return description; }
        public void setDescription(String description) { this.description = description; }
    }
    
    /**
     * source_data_info 클래스
     */
    public static class SourceDataInfoDto {
        
        @JsonProperty("annotation")
        private SourceAnnotationDto annotation;
        
        // Getters and Setters
        public SourceAnnotationDto getAnnotation() {
            return annotation;
        }
        
        public void setAnnotation(SourceAnnotationDto annotation) {
            this.annotation = annotation;
        }
    }
    
    /**
     * source_data_info 안의 annotation 클래스
     */
    public static class SourceAnnotationDto {
        private String question;
        private String answer;
        
        @JsonProperty("catetgory")  // JSON에서 오타로 되어있음
        private String category;
        
        @JsonProperty("Statutory provisions")
        private String statutoryProvisions;
        
        // Getters and Setters
        public String getQuestion() { return question; }
        public void setQuestion(String question) { this.question = question; }
        
        public String getAnswer() { return answer; }
        public void setAnswer(String answer) { this.answer = answer; }
        
        public String getCategory() { return category; }
        public void setCategory(String category) { this.category = category; }
        
        public String getStatutoryProvisions() { return statutoryProvisions; }
        public void setStatutoryProvisions(String statutoryProvisions) { this.statutoryProvisions = statutoryProvisions; }
    }
}
