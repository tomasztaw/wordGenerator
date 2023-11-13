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
import pl.taw.model.Word;
import pl.taw.ropository.WordRepository;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class WordServiceMockitoTest {

    @Mock
    private WordRepository wordRepository;

    @InjectMocks
    private WordService wordService;

    @Test
    void shouldReturnAllWordsList() {
        // given
        List<Word> expectedList = new ArrayList<>();
        expectedList.add(new Word(1L, "Paralaks", false));
        expectedList.add(new Word(2L, "Prokrastynacja", false));
        Mockito.when(wordRepository.findAll()).thenReturn(expectedList);

        // when
        List<Word> result = wordService.getAllWords();

        // then
        assertFalse(result.isEmpty());
        assertEquals(expectedList, result);
//        Mockito.verify(wordRepository.findAll(), Mockito.times(1));
    }
}
