package philosopher.paradise.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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

@Api(tags="Philosopher Quote Controller")
@CrossOrigin
@RestController
@RequestMapping("/philosophyApp")
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

    @ApiOperation(value="Returns the list of all philosopher sorted alphabetically", response = Iterable.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @GetMapping({"/get/philosophers"})
    public List<Philosopher> getPhilosophers() {
        return service.getPhilosophers();
    }

    @ApiOperation(value="Returns the list of all quotes in the database", response = Iterable.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @GetMapping({"/get/quotes"})
    public Set<Quote> getQuotes() {
        return quoteService.getQuotes();
    }

    @ApiOperation(value="Returns the list of all topics in the database", response = Iterable.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @GetMapping({"/get/topics"})
    public Set<Topic> getTopics() {
        return topicService.getTopics();
    }

    @ApiOperation(value="Finds a philosopher by a given id", response = Philosopher.class)
    @GetMapping({"/get/philosophers/{id}"})
    public Philosopher getPhilosopher(@PathVariable(value="id") Long id){
        return service.findById(id);
    }

    @ApiOperation(value="Finds a quote by a given id", response = Quote.class)
    @GetMapping({"/get/quotes/{id}"})
    public Quote getQuote(@PathVariable(value="id") Long id) {
        return quoteService.getQuoteById(id);
    }

    @ApiOperation(value="Returns a list of quotes per given topic", response = Iterable.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @GetMapping({"/get/quotes/{topic}"})
    public Set<Quote> getQuotesByTopic(@PathVariable(value="id") String topic) {
        return quoteService.getQuotesByTopic(topicService.getTopicByText(topic));
    }

    @ApiOperation(value="Returns a list of philosophers per given category", response = Iterable.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @GetMapping("/get/categories/{category}")
    public Set<Philosopher> getPhilosophersPerCategory(@PathVariable(value="category") String category){
        return service.getPhilosopherByCategory(category);
    }

    @ApiOperation(value="Finds a philosopher by id and updates the description")
    @PutMapping("/put/philosophers/{id}")
    public Philosopher editPhilosopher(@PathVariable Long id, @RequestBody String description) {
        return service.editPhilosopher(id, description);
    }

    @ApiOperation(value="Adds a philospher to the database")
    @PostMapping("/post/philosophers")
    public Philosopher postPhilosopher(@RequestBody Philosopher philosopher){
        return service.create(philosopher);
    }

    @ApiOperation(value="Adds a philospher to the database")
    @PostMapping("/post/quotes")
    public Quote postQuote(@RequestBody Quote quote){
        return quoteService.createQuote(quote);
    }

    @ApiOperation(value="Deletes a philosopher based on id")
    @DeleteMapping("/delete/philosophers/{id}")
    public void deletePhilosopher(@PathVariable Long id){
        service.deleteById(id);
    }

    @ApiOperation(value="Deletes a quote based on id")
    @DeleteMapping("/delete/quotes/{id}")
    public void deleteQuote(@PathVariable Long id){
        quoteService.deleteQuote(id);
    }
}
