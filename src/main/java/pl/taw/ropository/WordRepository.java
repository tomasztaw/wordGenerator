package pl.taw.ropository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.taw.model.Word;

public interface WordRepository extends JpaRepository<Word, Long> {

}
