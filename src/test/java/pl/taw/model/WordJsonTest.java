/**
 * Created by tomasz_taw
 * Date: 14.11.2023
 * Time: 21:30
 * Project Name: wordGenerator
 * Description:
 */
package pl.taw.model;

import com.jayway.jsonpath.JsonPath;
import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WordJsonTest {

    @Test
    void shouldCompareJson() throws JSONException {
        // given, when
        var data = getRestData();
        var expected = """
                {
                    "words" : [
                        {
                            "id": 1L,
                            "word": "Nowelizacja",
                            "used": false
                        },
                        {
                            "id": 2L,
                            "word": "Abdykacja",
                            "used": false
                        }
                    ]
                }
                """;

        // then
        JSONAssert.assertEquals(expected, data, false);
    }

    @Test
    void shouldCompareJsonPath() {
        // given, when
        var json = """
                {
                    "words" : [
                        {
                            "id": 1,
                            "word": "Alokacja",
                            "used": false
                        },
                        {
                            "id": 2,
                            "word": "Akredytacja",
                            "used": false
                        }
                    ]
                }
                """;
        // then
        Integer length = JsonPath.read(json, "$.words.length()");
        assertEquals(2, length);
    }

    private String getRestData() {
        return """
                {
                    "words" : [
                        {
                            "id": 1L,
                            "word": "Nowelizacja",
                            "used": false
                        },
                        {
                            "id": 2L,
                            "word": "Abdykacja",
                            "used": false
                        }
                    ]
                }
                """;
    }
}
