package pl.taw.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pl.taw.exception.BadRequestException;
import pl.taw.model.BasicResponse;
import pl.taw.service.ChatGPTService;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class GenerateWordRestControllerTest {

    private GenerateWordRestController generateWordRestController;

    @Mock
    private ChatGPTService chatGPTService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        generateWordRestController = new GenerateWordRestController(chatGPTService);
    }

    @Test
    public void testGetStringAnswer_WithValidWord() {
        // given
        String word = "ValidWord";
        when(chatGPTService.getAnswerString(word)).thenReturn("Answer");

        // when
        BasicResponse responseObject = generateWordRestController.getBasicJsonAnswer(word).getBody();

        // then
        assertNotNull(responseObject);
        assertEquals("ValidWord", responseObject.getWord());
        assertEquals("Answer", responseObject.getMessage());
    }

    @Test
    public void testGetStringAnswer_WithInvalidWord() {
        // given
        String word = "";

        // when, then
        assertThrows(BadRequestException.class, () -> generateWordRestController.getBasicJsonAnswer(word));
    }
}