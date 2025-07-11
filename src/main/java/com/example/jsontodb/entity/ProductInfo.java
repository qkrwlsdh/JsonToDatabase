package com.example.jsontodb.entity;

import jakarta.persistence.*;

/**
 * 제품 정보 엔터티
 * 테이블생성.sql 파일과 일치하는 구조
 */
@Entity
@Table(name = "product_info")
public class ProductInfo {
    
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;
    
    @Column(name = "doc_file_name", length = 100)
    private String docFileName;
    
    @Column(name = "product_id")
    private Long productId;
    
    @Column(name = "kc_date")
    private Long kcDate;
    
    @Column(name = "e_name", length = 300)
    private String eName;
    
    @Column(name = "be_class", length = 300)
    private String beClass;
    
    @Column(name = "re_class", length = 300)
    private String reClass;
    
    @Column(name = "add_class", length = 1000)
    private String addClass;
    
    @Column(name = "used", length = 500)
    private String used;
    
    @Column(name = "product_country", length = 200)
    private String productCountry;
    
    @Column(name = "field", length = 50)
    private String field;
    
    @Column(name = "kc_type", length = 50)
    private String kcType;
    
    @Column(name = "test", length = 100)
    private String test;
    
    @Column(name = "description", length = 1000)
    private String description;
    
    @Column(name = "question", length = 2000)
    private String question;
    
    @Column(name = "answer", length = 3000)
    private String answer;
    
    @Column(name = "category", length = 20)
    private String category;
    
    @Column(name = "statutory_provisions", length = 300)
    private String statutoryProvisions;
    
    // 기본 생성자
    public ProductInfo() {
    }
    
    // Getters and Setters
    public Integer getId() {
        return id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    public String getDocFileName() {
        return docFileName;
    }
    
    public void setDocFileName(String docFileName) {
        this.docFileName = docFileName;
    }
    
    public Long getProductId() {
        return productId;
    }
    
    public void setProductId(Long productId) {
        this.productId = productId;
    }
    
    public Long getKcDate() {
        return kcDate;
    }
    
    public void setKcDate(Long kcDate) {
        this.kcDate = kcDate;
    }
    
    public String geteName() {
        return eName;
    }
    
    public void seteName(String eName) {
        this.eName = eName;
    }
    
    public String getBeClass() {
        return beClass;
    }
    
    public void setBeClass(String beClass) {
        this.beClass = beClass;
    }
    
    public String getReClass() {
        return reClass;
    }
    
    public void setReClass(String reClass) {
        this.reClass = reClass;
    }
    
    public String getAddClass() {
        return addClass;
    }
    
    public void setAddClass(String addClass) {
        this.addClass = addClass;
    }
    
    public String getUsed() {
        return used;
    }
    
    public void setUsed(String used) {
        this.used = used;
    }
    
    public String getProductCountry() {
        return productCountry;
    }
    
    public void setProductCountry(String productCountry) {
        this.productCountry = productCountry;
    }
    
    public String getField() {
        return field;
    }
    
    public void setField(String field) {
        this.field = field;
    }
    
    public String getKcType() {
        return kcType;
    }
    
    public void setKcType(String kcType) {
        this.kcType = kcType;
    }
    
    public String getTest() {
        return test;
    }
    
    public void setTest(String test) {
        this.test = test;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public String getQuestion() {
        return question;
    }
    
    public void setQuestion(String question) {
        this.question = question;
    }
    
    public String getAnswer() {
        return answer;
    }
    
    public void setAnswer(String answer) {
        this.answer = answer;
    }
    
    public String getCategory() {
        return category;
    }
    
    public void setCategory(String category) {
        this.category = category;
    }
    
    public String getStatutoryProvisions() {
        return statutoryProvisions;
    }
    
    public void setStatutoryProvisions(String statutoryProvisions) {
        this.statutoryProvisions = statutoryProvisions;
    }
    
    @Override
    public String toString() {
        return "ProductInfo{" +
                "id=" + id +
                ", docFileName='" + docFileName + '\'' +
                ", productId=" + productId +
                ", kcDate=" + kcDate +
                ", eName='" + eName + '\'' +
                ", category='" + category + '\'' +
                '}';
    }
}
