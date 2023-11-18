CREATE TABLE IF NOT EXISTS word_description (
    id SERIAL PRIMARY KEY,
    word_id BIGINT,
    description TEXT,
    provenance TEXT,
    synonyms TEXT[],
    antonyms TEXT[],
    context TEXT,
    meaning TEXT,
    examples TEXT[],
    curiosities TEXT
);