package philosopher.paradise.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.Random;
import java.util.Set;

@Data
@Entity
public class Topic {

    @Id
    @GeneratedValue
    @ApiModelProperty(notes="The database generated product ID")
    private Long id;

    @ApiModelProperty(notes="A topic of philosophic enquiry")
    private String text;

    @ManyToMany
    @ApiModelProperty(notes="Quotes that correspond to the topic")
    private Set<Quote> quotes;

    private Topic(){
        this.id = new Random().nextLong();
    }

    public Topic(String text, Set<Quote> quotes) {
        this();
        this.text = text;
        this.quotes = quotes;
    }
}
