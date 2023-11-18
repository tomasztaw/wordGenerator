/**
 * Created by tomasz_taw
 * Date: 14.11.2023
 * Time: 21:16
 * Project Name: wordGenerator
 * Description:
 */
package pl.taw.model;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class WordHamcrestTest {

    @Test
    void shouldCreateNewWord() {
        // given, when
        Word word1 = new Word(1L, "Inklinacja", false);
        Word word2 = new Word(2L, "Inklinacja", false);

        // then
        assertThat(word1.getWord(), equalTo(word2.getWord()));
    }
}
