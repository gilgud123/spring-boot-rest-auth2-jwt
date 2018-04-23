package philosopher.paradise.repository;

import org.springframework.data.repository.CrudRepository;
import philosopher.paradise.entity.Quote;
import philosopher.paradise.entity.Topic;

import java.util.Optional;
import java.util.Set;

public interface QuoteRepository extends CrudRepository<Quote, Long> {

    Set<Quote> findByTopics(Topic topic);

    Set<Quote> findByPhilosopherId(Long id);

    Optional<Quote> findById(Long id);

    void deleteById(Long id);
}
