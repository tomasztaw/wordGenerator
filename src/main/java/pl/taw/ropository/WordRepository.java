package pl.taw.ropository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.taw.model.Word;


public interface WordRepository extends JpaRepository<Word, Long> {

    @Query("SELECT w FROM Word w")
    Page<Word> pageOfWords(Pageable pageable);

    Word findFirstByUsedFalse();

}
