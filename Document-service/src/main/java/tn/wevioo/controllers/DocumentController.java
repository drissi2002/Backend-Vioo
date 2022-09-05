package tn.wevioo.controllers;

import com.sun.istack.NotNull;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tn.wevioo.dto.ContentDto;
import tn.wevioo.dto.DocumentDto;
import tn.wevioo.models.Document;
import tn.wevioo.models.messages.ResponseDocument;
import tn.wevioo.models.messages.ResponseMessage;
import tn.wevioo.services.DocumentService;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/api")
@CrossOrigin("http://localhost:3000")

public class DocumentController {

    @Autowired
    private DocumentService documentService;
    @Autowired
    private final ModelMapper mapper;

    public DocumentController(ModelMapper mapper) {
        this.mapper = mapper;
    }

    // upload document
    @PostMapping("/document/upload")
    public ResponseEntity<ResponseMessage> uploadDocument(@RequestBody MultipartFile file ,@RequestParam("analyse") String analyse ,@RequestParam("score") Double score) {
        String message = "";
        try {
            documentService.storeDocument(file,analyse,score);
            message = "Uploaded the file successfully: " + file.getOriginalFilename();
            return ResponseEntity.status(OK).body(new ResponseMessage(message));
        } catch (Exception e) {
            message = "Could not upload the file: " + file.getOriginalFilename() + "!";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
        }
    }

    // get documents
    @GetMapping("/documents")
    public ResponseEntity<List<ResponseDocument>> getListDocuments() {
        List<ResponseDocument> files = documentService.getAllDocuments().map(document -> {
            String fileDownloadUri = ServletUriComponentsBuilder
                    .fromCurrentContextPath()
                    .path("/api/document/")
                    .path(document.getId())
                    .toUriString();
            return new ResponseDocument(
                    document.getId(),
                    document.getName(),
                    fileDownloadUri,
                    document.getType(),
                    document.getAnalyse(),
                    document.getScore(),
                    document.getData().length);
        }).collect(Collectors.toList());
        return ResponseEntity.status(OK).body(files);
    }

    // get document text
    @PostMapping(value ="/document/extractor",consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(OK)
    public ResponseEntity<ContentDto> classify(@Valid @NotNull @RequestParam("file") final MultipartFile pdfFile) throws IOException {
        return ResponseEntity.ok().body(ContentDto.builder().content(this.documentService.extractContent(pdfFile)).build());
    }

    // get document by id
    @GetMapping("/document/{documentId}")
    public ResponseEntity<byte[]> getDocumentById(@PathVariable String documentId) {
        Document document = documentService.getDocument(documentId);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + document.getName() + "\"")
                .body(document.getData());
    }
    // update document
    @PutMapping("/document/update")
    public ResponseEntity<Document> updateDocument(@RequestBody DocumentDto documentDto){
        Document document = mapper.map(documentDto, Document.class);
        return ResponseEntity.ok(this.documentService.updateDocument(document));
    }

    //delete document
    @DeleteMapping("/document/{documentId}")
    public void deleteDocument (@PathVariable("documentId") String id) {
        this.documentService.deleteDocument(id);
    }

}

/*
– @CrossOrigin is for configuring allowed origins.
– @Controller annotation is used to define a controller.
– @GetMapping and @PostMapping annotation is for mapping HTTP GET & POST
  requests onto specific handler methods:

POST /upload: storeDocuments()
GET /documents: getListDocuments()
GET /documents/[id]: getDocument()
DELETE /{id} : deleteDocument()
– We use @Autowired to inject implementation of documentService bean to local variable.
*/
