package org.siesta.controller;

import org.siesta.model.Document;
import org.siesta.service.OneDocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * This class
 */
@Controller
public class DocumentController {

    @Autowired
    private OneDocumentService documentService;

    @RequestMapping("/documents")
    public @ResponseBody List<Document> getAllDocuments(){
        return  documentService.getAllDocuments();
    }
}
