package philosopher.paradise.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
public class Quote {

    @Id
    @GeneratedValue
    @ApiModelProperty(notes="The database generated product ID")
    private Long id;

    @Lob
    @ApiModelProperty(notes="The quote content")
    private String text;

    @ManyToMany
    @JoinTable(name = "quote_topic",
            joinColumns = @JoinColumn(name = "QUOTE_ID"),
            inverseJoinColumns = @JoinColumn(name = "TOPIC_ID"))
    @ApiModelProperty(notes="A set of topics that correspond to the quote")
    private Set<Topic> topics;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PHILOSOPHER_ID")
    @ApiModelProperty(notes="The author of the quote")
    private Philosopher philosopher;

    public Quote() {}

    public Quote(String text, Set<Topic> topics, Philosopher philosopher) {
        this.text = text;
        this.topics = topics;
        this.philosopher = philosopher;
    }
}
