/**
 * Created by tomasz_taw
 * Date: 30.10.2023
 * Time: 16:19
 * Project Name: wordGenerator
 * Description:
 */
package pl.taw.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.taw.exception.BadRequestException;
import pl.taw.model.BasicResponse;
import pl.taw.service.ChatGPTService;

@RestController
@RequestMapping("/api/v1")
@Tag(name = "DescriptionGenerator") // nadanie własnego Tagu do dokumentacji
public class GenerateWordRestController {

    private final ChatGPTService gptService;

    public GenerateWordRestController(ChatGPTService gptService) {
        this.gptService = gptService;
    }

    @Operation(
            description = "Get dla generatora",
            summary = "Zwraca podstawowy opis dla słowa",
            responses = { @ApiResponse(description = "Success", responseCode = "200") })
    @GetMapping("/basic/{word}")
    public ResponseEntity<BasicResponse> getBasicJsonAnswer(@PathVariable("word") String word) {
        if (word == null || word.isEmpty()) {
            throw new BadRequestException("Nieprawidłowa wartość 'word'.");
        }

        BasicResponse basicResponse = new BasicResponse();
        String answer = gptService.getAnswerString(word);
        basicResponse.setWord(word);
        basicResponse.setMessage(answer);

        return ResponseEntity.ok(basicResponse);
    }
}
