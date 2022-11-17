package com.example.demo.service;

import com.example.demo.entity.Document;
import com.example.demo.entity.DocumentEntity;
import com.example.demo.repository.DocumentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.access.prepost.PreFilter;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DocumentService {

    private final DocumentRepository documentRepository;

    @PreAuthorize("hasRole('ADMIN')")
    public List<Document> getDocuments() {
        return List.of(
                new Document("First", "john"),
                new Document("Second", "bill")
        );
    }

    @PostAuthorize("returnObject.ownerName == authentication.name")
    public Document getDocumentById(long id) {
        return new Document("First", "john");
    }

    @PreFilter("filterObject != 'secretOwner'")
    public void changeDocumentByOwnerNames(List<String> ownerNames) {
        ownerNames.forEach(System.out::println);
    }

    @PostFilter("filterObject.ownerName != 'secretOwner'")
    public List<Document> getSecretDocuments() {

        List<Document> documents = new ArrayList<>();
        documents.add(new Document("First", "john"));
        documents.add(  new Document("Second", "secretOwner"));

        return documents;
//        return List.of(
//                new Document("First", "john"),
//                new Document("Second", "bill")
//        );
    }

    @PreAuthorize("@documentPermissionEvaluator.check(#ownerName)")
    public void changeDocumentByOwnerName(String ownerName) {
        System.out.println("Success");
    }

    public List<DocumentEntity> getMyDocuments() {
        return documentRepository.findMyDocuments();
    }

}
