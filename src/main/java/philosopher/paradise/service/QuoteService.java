package philosopher.paradise.service;

import philosopher.paradise.entity.Quote;
import philosopher.paradise.entity.Topic;

import java.util.Set;

public interface QuoteService {

    Set<Quote> getQuotes();

    Quote getQuoteById(Long id);

    Set<Quote> getQuotesByTopic(Topic topic);

    Set<Quote> getQuotesByPhilosopherId(Long id);

    Quote getRandomQuote();

    Quote createQuote(Quote quote);

    Quote updateQuote(Long is, String text);

    void deleteQuote(Long id);
}
