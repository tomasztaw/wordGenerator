/**
 * Created by tomasz_taw
 * Date: 30.10.2023
 * Time: 16:19
 * Project Name: wordGenerator
 * Description:
 */
package pl.taw.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import pl.taw.model.MyResponseObject;
import pl.taw.service.ChatGPTService;

@RestController
public class GenerateWordRestController {

    private final ChatGPTService gptService;

    public GenerateWordRestController(ChatGPTService gptService) {
        this.gptService = gptService;
    }

    @GetMapping("/{word}")
    public MyResponseObject getStringAnswer(@PathVariable("word") String word) {
        MyResponseObject responseObject = new MyResponseObject();
        String answer = gptService.getAnswerString(word);
        responseObject.setWord(word);
        responseObject.setMessage(answer);

        return responseObject;
    }
}
