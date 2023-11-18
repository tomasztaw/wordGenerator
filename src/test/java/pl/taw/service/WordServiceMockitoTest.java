/**
 * Created by tomasz_taw
 * Date: 13.11.2023
 * Time: 21:28
 * Project Name: wordGenerator
 * Description:
 */
package pl.taw.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.taw.controller.DemoRestController;
import pl.taw.model.Word;
import pl.taw.ropository.WordRepository;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class WordServiceMockitoTest {

    @Mock
    private WordRepository wordRepository;

    @Mock
    private DemoRestController demoRestController;

//    @Mock
//    private Word word;

    @InjectMocks
    private WordService wordService;

    @Test
    void shouldReturnAllWordsList() {
        // given
        List<Word> expectedList = new ArrayList<>();
        expectedList.add(new Word(1L, "Paralaks", false));
        expectedList.add(new Word(2L, "Prokrastynacja", false));
        when(wordRepository.findAll()).thenReturn(expectedList);

        // when
        List<Word> result = wordService.getAllWords();

        // then
        assertFalse(result.isEmpty());
        assertEquals(expectedList, result);
//        Mockito.verify(wordRepository.findAll(), Mockito.times(1));
    }

    @Test
    public void shouldProcessNextWord() {
        // given
        Word word = new Word(1L, "Paralaks", false);

        when(wordRepository.findFirstByUsedFalse()).thenReturn(word);

        // Symuluj zachowanie metody demoRestController.fullDescriptionWordNew
        when(demoRestController.fullDescriptionWordNew(anyString())).thenReturn("dummy");

        // when
        wordService.processNextWord();

        // then
        assertTrue(word.isUsed());
        verify(wordRepository, times(1)).findFirstByUsedFalse();
        verify(wordRepository, times(1)).save(word);
    }
}
