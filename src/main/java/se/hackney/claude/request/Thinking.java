package se.hackney.claude.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Thinking {
    
    @Builder.Default
    private String type = "enabled";

    @JsonProperty("budget_tokens")
    @Builder.Default
    private int budgetTokens = 5000;
}
