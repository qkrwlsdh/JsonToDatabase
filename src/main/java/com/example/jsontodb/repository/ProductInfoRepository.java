package com.example.jsontodb.repository;

import com.example.jsontodb.entity.ProductInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductInfoRepository extends JpaRepository<ProductInfo, Integer> {
}
