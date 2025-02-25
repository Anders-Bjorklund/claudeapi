package se.hackney.claude.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Thinking {
    
    private String type = "enabled";

    @JsonProperty("budget_tokens")
    private int budgetTokens = 15000;
}
