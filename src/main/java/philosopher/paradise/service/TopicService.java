package philosopher.paradise.service;

import philosopher.paradise.entity.Topic;

import java.util.Set;

public interface TopicService {

    Set<Topic> getTopics();

    Topic getTopicById(Long id);

    Topic getTopicByText(String text);
}
