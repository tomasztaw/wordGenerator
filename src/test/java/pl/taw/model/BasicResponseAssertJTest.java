package pl.taw.model;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BasicResponseAssertJTest {

    @Test
    void shouldCreateBasicResponse() {
        // given, when
        BasicResponse test = new BasicResponse("Alegoria", "Alegoria jest to słowo...");

        // then
        Assertions.assertThat(test.getWord())
                .isEqualTo("Alegoria");
        Assertions.assertThat(test.getMessage())
                .contains(test.getWord());
    }

}