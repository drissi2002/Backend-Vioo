package tn.wevioo.services.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.wevioo.models.Document;
import tn.wevioo.models.Word;
import tn.wevioo.repository.WordRepository;
import tn.wevioo.services.WordService;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class WordServiceImpl implements WordService {

    @Autowired
    WordRepository wordRepository ;

    @Override
    public Word addWord(Word word) {
        return this.wordRepository.save(word);
    }

    @Override
    public Word updateWord(Word word) {
        return this.wordRepository.save(word);
    }

    @Override
    public Set<Word> getWords() {
        return new HashSet<>(this.wordRepository.findAll());
    }

    @Override
    public Word getSingleWord(Long WordId) {
        return this.wordRepository.getById(WordId);
    }

    @Override
    public Optional<Word> getWord(Long WordId) {
        return this.wordRepository.findById(WordId);
    }

    @Override
    public Set<Word> getWordOfDocument(Document document) {
        return this.wordRepository.findByDocument(document);
    }

    @Override
    public void deleteWord(Long WordId) {
        this.wordRepository.delete(this.wordRepository.getById(WordId));
    }

    @Override
    public void deleteWords(){ this.wordRepository.deleteAll(); }
}
