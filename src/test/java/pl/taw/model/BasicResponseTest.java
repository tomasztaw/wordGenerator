package pl.taw.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BasicResponseTest {

    @Test
    public void testMyResponseObject() {
        // given
        String word = "TestWord";
        String message = "TestMessage";

        // when
        BasicResponse basicResponse = new BasicResponse();
        basicResponse.setWord(word);
        basicResponse.setMessage(message);

        // then
        assertEquals(word, basicResponse.getWord());
        assertEquals(message, basicResponse.getMessage());
    }

}