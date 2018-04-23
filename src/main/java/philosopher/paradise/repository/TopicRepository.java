package philosopher.paradise.repository;

import org.springframework.data.repository.CrudRepository;
import philosopher.paradise.entity.Topic;

import java.util.Optional;

public interface TopicRepository extends CrudRepository<Topic, Long> {

    Optional<Topic> findById(Long id);

    Optional<Topic> findByText(String text);
}
