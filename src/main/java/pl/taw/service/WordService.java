/**
 * Created by tomasz_taw
 * Date: 12.11.2023
 * Time: 17:58
 * Project Name: wordGenerator
 * Description:
 */
package pl.taw.service;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import pl.taw.model.Word;
import pl.taw.ropository.WordRepository;

import java.util.List;

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
}
