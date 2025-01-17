package se.hackney.claude.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Body {
    private String id;
    private String type;
    private String role;
    private String model;
    private List<Content> content;

    @JsonProperty("stop_reason")
    private String stop_reason;
    @JsonProperty("stop_sequence")
    private String stop_sequence;
    private Usage usage;

    public String getText() {
        return content.get(0).getText();
    }
}
