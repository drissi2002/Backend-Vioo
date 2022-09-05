package tn.wevioo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.wevioo.models.Document;
import tn.wevioo.models.Word;

import java.util.Set;

@Repository
public interface WordRepository extends JpaRepository<Word, Long> {
    public Set<Word> findByDocument(Document document);
    public Set<Word> findByContenuContaining(String contenu);
}
