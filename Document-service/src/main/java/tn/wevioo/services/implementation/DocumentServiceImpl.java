package tn.wevioo.services.implementation;

import lombok.extern.slf4j.Slf4j;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import tn.wevioo.models.Document;
import tn.wevioo.repository.DocumentRepository;
import tn.wevioo.services.DocumentService;

import java.io.File;
import java.io.IOException;
import java.util.stream.Stream;

@Service
@Slf4j
public class DocumentServiceImpl implements DocumentService {

    @Autowired
    private DocumentRepository documentRepository ;


    @Override
    public Document storeDocument(MultipartFile file , String analyse, Double score) throws IOException {
        String documentName = StringUtils.cleanPath(file.getOriginalFilename());
        Document document = new Document(documentName, file.getContentType(),analyse,score,file.getBytes());
        return documentRepository.save(document);    }

    @Override
    public String extractContent(MultipartFile multipartFile){
        String text;

        try (final PDDocument document = PDDocument.load(multipartFile.getInputStream())) {
            final PDFTextStripper pdfStripper = new PDFTextStripper();
            text = pdfStripper.getText(document);
        } catch (final Exception ex) {
            log.error("Error parsing PDF", ex);
            text = "Error parsing PDF";
        }
        return text;
    }


    @Override
    public Document getDocument(String id) {
        return documentRepository.findById(id).get();
    }

    @Override
    public Document updateDocument(Document document) {
        return this.documentRepository.save(document);
    }

    @Override
    public Stream<Document> getAllDocuments() {
        return documentRepository.findAll().stream();
    }

    @Override
    public void deleteDocument(String id) {
        this.documentRepository.delete(this.documentRepository.getById(id));
    }
}
