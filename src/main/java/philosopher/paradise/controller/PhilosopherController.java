package philosopher.paradise.controller;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import philosopher.paradise.entity.Category;
import philosopher.paradise.entity.Philosopher;
import philosopher.paradise.entity.Quote;
import philosopher.paradise.entity.Topic;
import philosopher.paradise.service.PhilosopherServiceImpl;
import philosopher.paradise.service.QuoteServiceImpl;
import philosopher.paradise.service.TopicServiceImpl;

import javax.annotation.Resources;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api")
public class PhilosopherController {

    private final PhilosopherServiceImpl service;
    private final QuoteServiceImpl quoteService;
    private final TopicServiceImpl topicService;

    @Autowired
    public PhilosopherController(PhilosopherServiceImpl service, QuoteServiceImpl quoteService, TopicServiceImpl topicService) {
        this.service = service;
        this.quoteService = quoteService;
        this.topicService = topicService;
    }

    @GetMapping({"/philosophers"})
    public List<Philosopher> getPhilosophers() {
        return service.getPhilosophers();
    }

    @GetMapping({"/philosophers/{id}"})
    public Philosopher getPhilosopher(@PathVariable(value="id") Long id){
        return service.findById(id);
    }

    @GetMapping({"/quotes"})
    public Set<Quote> getQuotes() {
        return quoteService.getQuotes();
    }

    @GetMapping({"/quotes/{id}"})
    public Quote getQuote(@PathVariable(value="id") Long id) {
        return quoteService.getQuoteById(id);
    }

    @GetMapping({"/quotes/{topic}"})
    public Set<Quote> getQuotesByTopic(@PathVariable(value="id") String topic) {
        return quoteService.getQuotesByTopic(topicService.getTopicByText(topic));
    }

    @GetMapping({"/topics"})
    public Set<Topic> getTopics() {
        return topicService.getTopics();
    }

    @GetMapping({"/topics/{id}"})
    public Topic getTopic(@PathVariable(value="id") Long id) {
        return topicService.getTopicById(id);
    }

    @GetMapping("/categories/{category}")
    public Set<Philosopher> getPhilosophersPerCategory(@PathVariable(value="category") String category){
        return service.getPhilosopherByCategory(category);
    }

    @PutMapping("/philosophers/{id}")
    public Philosopher editPhilosopher(@PathVariable Long id, @RequestBody String description) {
        return service.editPhilosopher(id, description);
    }

    @PostMapping("/philosophers")
    public Philosopher postPhilosopher(@RequestBody Philosopher philosopher){
        return service.create(philosopher);
    }

    @DeleteMapping("/philosophers/{id}")
    public void deletePhilosopher(@PathVariable Long id){
        service.deleteById(id);
    }

    @DeleteMapping("/quotes/{id}")
    public void deleteQuote(@PathVariable Long id){
        quoteService.deleteQuote(id);
    }
}
