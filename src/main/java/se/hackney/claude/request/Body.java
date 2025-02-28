package se.hackney.claude.request;

import lombok.Builder;
import lombok.Data;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class Body {

    private String model;

    @Builder.Default
    private int temperature = 1;

    @JsonProperty("max_tokens")
    private int maxTokens;

    @Builder.Default
    private Thinking thinking = new Thinking();

    private List<Message> messages;

    public Body setBudget(int budget) {
        this.thinking = new Thinking(budget);
        return this;
    }
}
