package pl.taw.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import pl.taw.model.Word;
import pl.taw.service.WordService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@WebMvcTest(WordController.class)
class WordControllerWebMvcTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    WordService wordService;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    void shouldFindAllWords() throws Exception {
        // given
        List<Word> words = List.of(new Word(1L, "Paralaks", false), new Word(2L, "Konfabulacja", false));
        Mockito.when(wordService.getAllWords()).thenReturn(words);

        // when
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/api/word")).andReturn();
//        ObjectMapper objectMapper = new ObjectMapper();
        List<Word> responseWords = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), new TypeReference<>() {});

        // then
        assertEquals(200, mvcResult.getResponse().getStatus());
        assertTrue(responseWords.containsAll(words));
    }


    @Test
    void shouldFindAllWordsNext() throws Exception {
        // given
        List<Word> words = List.of(new Word(1L, "Paralaks", false), new Word(2L, "Konfabulacja", false));
        Mockito.when(wordService.getAllWords()).thenReturn(words);

        // when, then
        mockMvc.perform(MockMvcRequestBuilders.get("/api/word"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(words)));
    }


    @Test
    void shouldFindAllWordsExpectedAll() throws Exception {
        // given
        List<Word> words = List.of(new Word(1L, "Paralaks", false), new Word(2L, "Konfabulacja", false));
        Mockito.when(wordService.getAllWords()).thenReturn(words);

        // when
        mockMvc.perform(MockMvcRequestBuilders.get("/api/word"))
                .andExpectAll(
                        MockMvcResultMatchers.status().isOk(),
                        MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(words))
                );
    }

    @Test
    void shouldGetPageOfWords() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/word/page")
                        .param("page", "0")
                        .param("size", "10")
                        .param("sort", "word,asc"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
    }
}