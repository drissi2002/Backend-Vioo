package tn.wevioo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.wevioo.models.Document;

import java.util.Optional;

@Repository
public interface DocumentRepository extends JpaRepository<Document,String> {
    public Document findByName(String name);
    public Optional<Document> findByNameAndTypeAndId(String name, String type, String id);
    //public Optional<Document> findById(String id);

}
