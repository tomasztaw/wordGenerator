package pl.taw.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Data
@With
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WordDescription {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @ManyToOne
        @JoinColumn(name = "word_id")
        private Word word;

        private String description;

        // pochodzenie
        private String provenance;

        @ElementCollection
        private List<String> synonyms;

        @ElementCollection
        private List<String> antonyms;

        private String context;

        // znaczenie
        private String meaning;

        // przykłady
        @ElementCollection
        private List<String> examples;

        // ciekawostki
        private String curiosities;

}
