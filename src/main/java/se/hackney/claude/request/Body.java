package se.hackney.claude.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
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
}
