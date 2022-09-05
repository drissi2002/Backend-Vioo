package tn.wevioo.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.wevioo.dto.WordDto;
import tn.wevioo.models.Word;
import tn.wevioo.services.DocumentService;
import tn.wevioo.services.WordService;

import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/api")
@CrossOrigin("http://localhost:3000")

public class WordController {

    @Autowired
    private WordService wordService;
    @Autowired
    private DocumentService documentService;
    @Autowired
    private final ModelMapper mapper;

    public WordController(ModelMapper mapper) {
        this.mapper = mapper;
    }


    //add word
    @PostMapping("/word/upload")
    public ResponseEntity<Word> uploadWord(@RequestBody WordDto wordDto){
        Word word = mapper.map(wordDto, Word.class);
        return ResponseEntity.ok(this.wordService.addWord(word));
    }

    //get words
    @GetMapping("/words")
    public ResponseEntity<?> getListWords(){
        return ResponseEntity.ok(this.wordService.getWords());

    }

    //get word by id
    @GetMapping("/word/{wordId}")
    public Optional<Word> getWordById (@PathVariable("wordId") Long wordId){
        return this.wordService.getWord(wordId);

    }

    //update word

    @PutMapping("/word/update")
    public ResponseEntity<Word> updateWord(@RequestBody WordDto wordDto){
        Word word = mapper.map(wordDto, Word.class);
        return ResponseEntity.ok(this.wordService.updateWord(word));
    }

    //delete all words
    @DeleteMapping("/words")
    public void deleteAllWords() {
        this.wordService.deleteWords();
    }

    //delete word
    @DeleteMapping("/word/{wordId}")
    public void deleteWord (@PathVariable("wordId") Long wordId ) {
        this.wordService.deleteWord(wordId);
    }



    // get document words by document id
    @GetMapping("/word/document/{documentId}")
    public Set<Word> getWordsOfDocument(@PathVariable("documentId") String documentId){
        return this.wordService.getWordOfDocument(this.documentService.getDocument(documentId));
    }

}
