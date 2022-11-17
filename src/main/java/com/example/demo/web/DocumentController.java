package com.example.demo.web;

import com.example.demo.entity.Document;
import com.example.demo.entity.DocumentEntity;
import com.example.demo.service.DocumentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/document")
@RequiredArgsConstructor
public class DocumentController {

    private final DocumentService documentService;

    @GetMapping
    public List<Document> getDocuments() {
        return documentService.getDocuments();
    }

    @GetMapping("/{id}")
    public Document getDocumentById(@PathVariable Long id) {
        return documentService.getDocumentById(id);
    }


    @GetMapping("/change")
    public void changeDocuments() {
        ArrayList<String> names = new ArrayList<>();
        names.add("john");
        names.add("secretOwner");
//        List<String> names = List.of("john", "secretOwner");

        documentService.changeDocumentByOwnerNames(names);
    }

    @GetMapping("/secret")
    public List<Document> getSecretDocuments() {
        return documentService.getSecretDocuments();
    }

    @GetMapping("/change/{name}")
    public void changeDocumentsNyName(@PathVariable String name) {
        documentService.changeDocumentByOwnerName(name);
    }

    @GetMapping("/my")
    public List<DocumentEntity> getMyDocuments() {
        return documentService.getMyDocuments();
    }
}
