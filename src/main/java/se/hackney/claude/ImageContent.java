package se.hackney.claude;

import java.io.File;
import java.io.IOException;

import lombok.Builder;
import lombok.Data;

@Data
public class ImageContent extends Content {
    private ImageSource source;

    @Builder
    public ImageContent(File imageFile) throws IOException {
        this.type = "image";
        this.source = new ImageSource(imageFile);
    }
}