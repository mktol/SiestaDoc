package com.repo.one.controller;

import com.repo.one.model.Document;
import com.repo.one.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController()
public class DocumentController {


    private  final String appName;
    @Autowired
    private DocumentService documentService;

    public DocumentController(@Value("${spring.application.name}") String appName) {
        this.appName = appName;
    }

    @RequestMapping(value = "/siesta/rest/documents/", method = RequestMethod.GET)
    public List<Document> documents() {
//        return Collections.singletonList("--test list val--");
        return documentService.getAll();
    }

    @RequestMapping(value = "/siesta/rest/documents/{id}", method = RequestMethod.GET)
    public List<Document> documents(@PathVariable(value = "id", required = false) String documentId) {
        if (documentId == null) {
            return documentService.getAll();
        }
        List<Document> doc = new ArrayList<>();
        doc.add(documentService.getDocumentByID(documentId));
        return doc;
    }

    /**
     * Save document in with concrete id
     *
     * @param documentId
     * @param document
     * @return
     */
    @RequestMapping(value = "/siesta/rest/documents/{id}", method = RequestMethod.PUT)
    public Document saveConcreateDocument(@PathVariable("id") String documentId, @RequestBody() Document document) {
        return documentService.saveDocument(documentId, document);
    }

    @RequestMapping(value = "/siesta/rest/documents", method = RequestMethod.POST, consumes = {"application/json"})
    public Document saveDocument(@RequestBody() Document document) {
        return documentService.setDocument(document);
    }

    /**
     * Update document
     *
     * @param document
     * @return
     */
    @RequestMapping(method = RequestMethod.PUT, consumes = {"application/json"})
    public boolean updateDocument(@RequestBody() Document document) {
        if (document != null) {
            documentService.update(document);
            return true;
        }
        return false;
    }

    @RequestMapping(value = "/siesta/rest/documents/{id}", method = RequestMethod.DELETE)
    public boolean deleteDocument(@PathVariable("id") String documentId) {
        return documentService.remove(documentId);
    }
}
