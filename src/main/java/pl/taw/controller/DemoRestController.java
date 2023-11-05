/**
 * Created by tomasz_taw
 * Date: 05.11.2023
 * Time: 11:46
 * Project Name: wordGenerator
 * Description:
 */
package pl.taw.controller;

import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.ai.client.AiClient;
import org.springframework.ai.client.AiResponse;
import org.springframework.ai.prompt.PromptTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class DemoRestController {

    private final AiClient aiClient;
//    @Value("${openai.api-key}")
//    private String apiKey;

    public DemoRestController(AiClient aiClient) {
        this.aiClient = aiClient;
    }

    @Hidden
    @GetMapping("/joke")
    public String getJoke(@RequestParam(name = "topic") String topic) {
        PromptTemplate promptTemplate = new PromptTemplate("""
                I'm bored with hello world apps. How about you give me a joke about {topic}? to get started?
                Include some programming terms in your joke to make it more fun.
                """);
        promptTemplate.add("topic", topic);
        return this.aiClient.generate(promptTemplate.create()).getGeneration().getText();
    }

    @Hidden
    @GetMapping("/joke/pl")
    public String getJokePl(@RequestParam(name = "temat") String topic) {
        PromptTemplate promptTemplate = new PromptTemplate("""
                Opowiedz mi kawał na temat {temat}.
                Dodaj jakieś zwroty programistyczne dla większej zabawy.
                """);
        promptTemplate.add("temat", topic);
        return this.aiClient.generate(promptTemplate.create()).getGeneration().getText();
    }

    @Hidden
    @GetMapping("/bestMovie")
    public String getBestMovie(@RequestParam(name = "genre") String genre, @RequestParam(name = "year") String year) {
        PromptTemplate promptTemplate = new PromptTemplate("""
                I'm bored with hello world apps. How about you give me a movie about {genre} in {year} to get started?
                But pick the best movie you can think of. I'm a movie critic, after all. IMDB ratings are a good place to start.
                And which actor or actress stars in it? And who directed it? And who wrote it? Can you give me a short plot summary and also it's name?
                But don't give me too much information. I want to be surprised.
                And please give me these details in the following JSON format: genre, year, movieName, actor, director, writer, plot.
                """);
        Map.of("genre", genre, "year", year).forEach(promptTemplate::add);
        AiResponse generate = this.aiClient.generate(promptTemplate.create());
        return generate.getGeneration().getText();
    }

    @GetMapping("/fullDesc")
    public String fullDescriptionWord(@RequestParam(name = "word") String word) {
        PromptTemplate promptTemplate = new PromptTemplate("""
                Opisz mi proszę słowo {word}.
                Podaj pochodzenie tego słowa.
                Przykłady z życia.
                Odmiany tego słowa.
                Oraz synonimy.
                Odpowiedź ma być w formacie JSON: opis, pochodzenie, przykłady, odmiany, synonimy.
                """);
        promptTemplate.add("word", word);
        AiResponse generate = this.aiClient.generate(promptTemplate.create());
        return generate.getGeneration().getText();
    }


}
