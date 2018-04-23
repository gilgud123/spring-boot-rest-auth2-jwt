package philosopher.paradise.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import philosopher.paradise.entity.Quote;
import philosopher.paradise.entity.Topic;
import philosopher.paradise.repository.PhilosopherRepository;
import philosopher.paradise.repository.QuoteRepository;

import java.util.*;

@Service
public class QuoteServiceImpl implements QuoteService {

    private QuoteRepository repo;
    private PhilosopherRepository pRepo;

    public QuoteServiceImpl(QuoteRepository repo, PhilosopherRepository pRepo) {
        this.repo = repo;
        this.pRepo = pRepo;
    }

    public Set<Quote> getQuotes(){
        Set<Quote> quotes = new HashSet<>();
        repo.findAll().iterator().forEachRemaining(quotes::add);
        return quotes;
    }

    @Override
    public Quote getQuoteById(Long id) {

        if(!repo.findById(id).isPresent()) {
            throw new RuntimeException("Quote not found!");
        }
        return repo.findById(id).get();
    }

    public Set<Quote> getQuotesByTopic(Topic topic){
        Set<Quote> quotes = new HashSet<>();
        repo.findByTopics(topic).iterator().forEachRemaining(quotes::add);
        return quotes;
    }

    @Override
    public Set<Quote> getQuotesByPhilosopherId(Long id) {
        Set<Quote> quotes = new HashSet<>();
        repo.findByPhilosopherId(id).iterator().forEachRemaining(quotes::add);
        return quotes;
    }

    @Override
    public Quote createQuote(Quote quote) {
        return repo.save(quote);
    }

    @Override
    public Quote updateQuote(Long id, String text) {
        Quote quoteToUpdate = getQuoteById(id);
        quoteToUpdate.setText(text);
        repo.save(quoteToUpdate);
        return quoteToUpdate;
    }

    @Override
    public Quote getRandomQuote(){
        List<Quote> quotes = new ArrayList<>();
        repo.findAll().iterator().forEachRemaining(quotes::add);
        Random rand = new Random();
        int id = rand.nextInt(quotes.size());
        return quotes.get(id);
    }

    @Override
    public void deleteQuote(Long id) {
        repo.deleteById(id);
    }
}
