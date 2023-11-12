/**
 * Created by tomasz_taw
 * Date: 12.11.2023
 * Time: 17:48
 * Project Name: wordGenerator
 * Description:
 */
package pl.taw.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.taw.model.Word;
import pl.taw.service.WordService;

import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/api/word")
public class WordController {

    private final WordService wordService;

    public WordController(WordService wordService) {
        this.wordService = wordService;
    }

    @GetMapping
    public ResponseEntity<List<Word>> getAllWords() {
        List<Word> result = wordService.getAllWords();
        return ResponseEntity.ok(result);
    }

    @GetMapping("/sort")
    public ResponseEntity<List<Word>> getAllWordAlphabetical() {
        List<Word> result = wordService.getAllWordAlphabetical();
        return ResponseEntity.ok(result);
    }

    @GetMapping("/random")
    public ResponseEntity<Word> getRandomWord() {
        int size = wordService.getAllWords().size();
        Random random = new Random();
        Word result = wordService.getAllWords().get(random.nextInt(size));
        return ResponseEntity.ok(result);
    }
}
