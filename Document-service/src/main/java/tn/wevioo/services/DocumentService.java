package tn.wevioo.services;

import org.springframework.web.multipart.MultipartFile;
import tn.wevioo.models.Document;

import java.io.File;
import java.io.IOException;
import java.util.stream.Stream;

public interface DocumentService {
    public Document storeDocument(MultipartFile file ,String analyse,Double score) throws IOException;
    public String extractContent(final MultipartFile multipartFile)throws IOException;
    // public void uploadDocument(Document document) throws Exception;
    public Document getDocument(String id);
    public Document updateDocument(Document document);
    public Stream<Document> getAllDocuments();
    public void deleteDocument(String id);

}

/*
The File Storage Service will use DocumentRepository to provide following methods:

- storeDocument(file): receives MultipartFile object, transform to FileDB object and save it to Database
- getDocument(id): returns a document object by provided Id
- getAllDocuments(): returns all stored documents as list of code>document objects


*/
