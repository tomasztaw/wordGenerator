package pl.taw.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WordJunitTest {

    @Test
    void shouldCreateNewWord() {
        // given, when
        Word test = new Word(1L, "Alegoria", false);

        // then
        assertEquals("Alegoria", test.getWord(), "Something go'es wrong with create the word");
        assertFalse(test.isUsed());
    }

}