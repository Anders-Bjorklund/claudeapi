package se.hackney.claude.request;

import java.io.File;
import java.io.IOException;

import lombok.Builder;
import lombok.Data;

@Data
public class ImageContent extends Content {
    private ImageSource source;

    public ImageContent() {}

    @Builder
    public ImageContent(String imageFile) {
        this.type = "image";
        try {
            this.source = new ImageSource(imageFile);
        } catch (IOException e) {
            if( imageFile != null ) {
                throw new UnableToAccessImageContentException(imageFile, e);
            } else {
                throw new UnableToAccessImageContentException(e);
            }
        }
    }

    public ImageContent( String imageFile, byte[] fileContent) {
        this.source = new ImageSource(imageFile, fileContent);
    }

}

class UnableToAccessImageContentException extends RuntimeException {
    public UnableToAccessImageContentException( Throwable throwable) {
        super(throwable);
    }

    public UnableToAccessImageContentException(String message, Throwable throwable) {
        super(message, throwable);
    }
}