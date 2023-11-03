package pl.taw.service;

import org.junit.jupiter.api.Test;
import org.springframework.ai.client.AiClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class ChatGPTServiceTest {

    @MockBean
    @SuppressWarnings("unused")
    private AiClient aiClient;

    @Autowired
    @SuppressWarnings("unused")
    private ChatGPTService chatGPTService;

    @Test
    public void testGetAnswerString() {
        String word = "Eufemizm";

        String expectedResult = "Eufemizm to wyrażenie lub słowo, które jest stosowane w celu złagodzenia lub " +
                "zastąpienia bardziej surowego, nieprzyjemnego lub wulgarnego terminu lub wyrażenia.";

        when(aiClient.generate("Opisz słowo \"Eufemizm\".")).thenReturn(expectedResult);

        String result = chatGPTService.getAnswerString(word);

        assertEquals(expectedResult, result);
    }

}