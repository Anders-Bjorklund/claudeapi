package se.hackney.claude.request;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

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

    public TextContent( Path filePath ) {
        try {
            this.text = Files.readString(filePath);
        } catch (IOException e) {
            throw new RuntimeException("NuSuchFileException", e);
        }
    }
}