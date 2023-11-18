/**
 * Created by tomasz_taw
 * Date: 12.11.2023
 * Time: 17:58
 * Project Name: wordGenerator
 * Description:
 */
package pl.taw.service;

import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import pl.taw.controller.DemoRestController;
import pl.taw.model.Word;
import pl.taw.ropository.WordRepository;

import java.util.List;
import java.util.Random;

@Service
public class WordService {

    private final WordRepository wordRepository;
    private final DemoRestController demoRestController;

    public WordService(WordRepository wordRepository, DemoRestController demoRestController) {
        this.wordRepository = wordRepository;
        this.demoRestController = demoRestController;
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

    @Transactional
    public void processNextWord() {
        Word word = wordRepository.findFirstByUsedFalse();

        if (word != null) {
            demoRestController.fullDescriptionWordNew(word.getWord());

            word.setUsed(true);
            wordRepository.save(word);
        }
    }
}
