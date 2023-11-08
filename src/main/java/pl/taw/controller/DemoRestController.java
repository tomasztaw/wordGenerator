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
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import pl.taw.model.GeneratedImage;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;

@RestController
public class DemoRestController {

    private final AiClient aiClient;

    @Value("${spring.ai.openai.api-key}")
    String openaiApiKey;

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

    @GetMapping("/fullDescOld")
    public String fullDescriptionWordOld(@RequestParam(name = "word") String word) {
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

    @GetMapping("/fullDesc")
    public String fullDescriptionWordNew(@RequestParam(name = "word") String word) {
        PromptTemplate promptTemplate = new PromptTemplate("""
                Proszę opisać słowo {word} pod kątem jego znaczenia i zastosowania.
                Czy mogę dowiedzieć się, skąd wywodzi się słowo {word}? Jakie jest jego etymologiczne pochodzenie?
                Czy istnieją synonimy i antonimy dla słowa {word}? Proszę podać kilka przykładów.
                Jakie są typowe sytuacje lub konteksty, w których pojęcie {word} jest często używane?
                Czy można przedstawić historię i ewolucję tego słowa w kontekście kultury lub wydarzeń historycznych?
                Czy można podać kilka przykładów zdań, w których słowo {word} jest używane w praktyce?
                Czy to słowo ma negatywne lub pozytywne konotacje w różnych kontekstach społecznych? Jakie są emocjonalne reakcje związane z tym słowem?
                Czy jest coś jeszcze, co warto wiedzieć na temat słowa {word}?
                Odpowiedź ma być w formacie JSON: opis, pochodzenie, synonimy, antonimy, kontekst, znaczenie, przykłady, ciekawostki.
                Jeżeli nie jesteś pewien czy znaczenie jest poprawne, to proszę nie wymyślaj, tylko daj znać, że nie jesteś pewien.
                """);
        promptTemplate.add("word", word);
        AiResponse generate = this.aiClient.generate(promptTemplate.create());
        return generate.getGeneration().getText();
    }



    @GetMapping(value = "/image", produces = "image/jpeg")
    public ResponseEntity<InputStreamResource> getImage(@RequestParam(name = "topic") String topic) throws URISyntaxException {
        PromptTemplate promptTemplate = new PromptTemplate("""
                I'm bored with hello world apps. Can you create me a prompt about {topic}. Enhance the topic I gave you. Make it fancy.
                Make resolution 256x256 but in Json it needs to be string. I want only 1 creation. Give me as JSON format: prompt, n, size.
                Do not make any comments. Just JSON file.
                """);
        promptTemplate.add("topic", topic);
        String imagePrompt = this.aiClient.generate(promptTemplate.create()).getGeneration().getText();

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + openaiApiKey);
        headers.add("Content-Type", "application/json");
        HttpEntity<String> httpEntity = new HttpEntity<>(imagePrompt,headers);

        String imageUrl = restTemplate.exchange("https://api.openai.com/v1/images/generations", HttpMethod.POST, httpEntity, GeneratedImage.class)
                .getBody().getData().get(0).getUrl();
        byte[] imageBytes = restTemplate.getForObject(new URI(imageUrl), byte[].class);
        return ResponseEntity.ok().body(new InputStreamResource(new java.io.ByteArrayInputStream(imageBytes)));
    }

}
