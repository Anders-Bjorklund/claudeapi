package se.hackney.claude.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TextContent extends Content {
    private String text;

    public TextContent() {}
    
    public TextContent(String text) {
        this.type = "text";
        this.text = text;
    }
}