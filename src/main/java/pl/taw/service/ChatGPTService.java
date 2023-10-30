/**
 * Created by tomasz_taw
 * Date: 28.10.2023
 * Time: 09:51
 * Project Name: secretProperties
 * Description:
 */
package pl.taw.service;

import org.springframework.ai.client.AiClient;
import org.springframework.stereotype.Service;

@Service
public class ChatGPTService {

    private final AiClient aiClient;

    public ChatGPTService(AiClient aiClient) {
        this.aiClient = aiClient;
    }


    public String getAnswerString(String word) {
        String prompt = "Opisz słowo \"%s\".".formatted(word);

        return aiClient.generate(prompt);
    }




}
