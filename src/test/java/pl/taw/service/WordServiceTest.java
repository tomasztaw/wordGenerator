package pl.taw.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import pl.taw.model.Word;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class WordServiceTest {

    @Autowired
    private WordService wordService;

    @Test
    public void testGetAllWords() {
        // given
        long expectedSize = wordService.getAllWords().size();

        // when
        List<Word> words = wordService.getAllWords();

        // then
        assertNotNull(words);
        assertFalse(words.isEmpty());
        assertEquals(expectedSize, words.size());
    }

    @Test
    public void testGetAllWordAlphabetical() {
        // given, when
        List<Word> words = wordService.getAllWordAlphabetical();

        // then
        assertNotNull(words);
        assertFalse(words.isEmpty());
        for (int i = 1; i < words.size(); i++) {
            assertTrue(words.get(i - 1).getWord().compareTo(words.get(i).getWord()) <= 0);
        }
    }

    @Test
    public void testGetRandomWord() {
        // given
        List<Word> words = wordService.getAllWordAlphabetical();

        // when
        Word randomWord = wordService.getRandomWord();

        // then
        assertNotNull(randomWord);
        assertTrue(words.contains(randomWord));
    }

}