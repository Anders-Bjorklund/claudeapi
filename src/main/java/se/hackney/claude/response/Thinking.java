package se.hackney.claude.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;


@AllArgsConstructor
@Data
@Builder
public class Thinking {

    public Thinking(String thinking) {
        this.thinking = thinking;
    }
    
    private String type;
    private String thinking;
    private String signature;
}
