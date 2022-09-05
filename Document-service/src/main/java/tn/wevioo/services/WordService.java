package tn.wevioo.services;

import tn.wevioo.models.Document;
import tn.wevioo.models.Word;

import java.util.Optional;
import java.util.Set;

public interface WordService {
    public Word addWord(Word word);
    public Word updateWord(Word word);
    public Set<Word> getWords();
    public Word getSingleWord(Long WordId);
    public Optional<Word> getWord(Long WordId);
    public Set<Word> getWordOfDocument(Document document);
    public void deleteWord(Long WordId);
    public void deleteWords();
}
