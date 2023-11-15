/**
 * Created by tomasz_taw
 * Date: 12.11.2023
 * Time: 17:58
 * Project Name: wordGenerator
 * Description:
 */
package pl.taw.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import pl.taw.model.Word;
import pl.taw.ropository.WordRepository;

import java.util.List;
import java.util.Random;

@Service
public class WordService {

    private final WordRepository wordRepository;

    public WordService(WordRepository wordRepository) {
        this.wordRepository = wordRepository;
    }

    public List<Word> getAllWords() {
        return wordRepository.findAll();
    }

    public List<Word> getAllWordAlphabetical() {
        Sort sort = Sort.by(Sort.Direction.ASC, "word");
        return wordRepository.findAll(sort);
    }

    public Word getRandomWord() {
        Random random = new Random();
        long size = wordRepository.count();
        return wordRepository.findById(random.nextLong(size))
                .orElseThrow(() -> new RuntimeException("There is a problem with random word generator"));
    }

    public Page<Word> getPageOfWords(Pageable pageable) {
        return wordRepository.pageOfWords(pageable);
    }
}
