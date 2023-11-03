package pl.taw.config;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class OpenAIConfigTest {

    @Autowired
    @SuppressWarnings("unused")
    private OpenAIConfig openAIConfig;

    @Test
    void testRestTemplate() {
        RestTemplate restTemplate = openAIConfig.restTemplate();
        assertNotNull(restTemplate);
    }
}