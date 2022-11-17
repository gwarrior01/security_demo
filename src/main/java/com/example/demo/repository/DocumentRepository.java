package com.example.demo.repository;

import com.example.demo.entity.DocumentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DocumentRepository extends JpaRepository<DocumentEntity, Long> {

    @Query("""
    select d from DocumentEntity d where d.ownerName = ?#{authentication.name}
    """)
    List<DocumentEntity> findMyDocuments();

}
