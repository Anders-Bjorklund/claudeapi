package se.hackney.claude.request;

import lombok.Builder;
import lombok.Data;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class FastBody implements Body{

    private String model;

    @Builder.Default
    private int temperature = 1;

    @JsonProperty("max_tokens")
    private int maxTokens;

    private List<Message> messages;
}
