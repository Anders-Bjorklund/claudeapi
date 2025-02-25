package se.hackney.claude.request;

import java.io.File;
import java.io.IOException;

import lombok.Builder;
import lombok.Data;

@Data
public class ImageContent extends Content {
    private ImageSource source;

    public ImageContent() {}

    public ImageContent(String imageFilePath) {
        this(getFile(imageFilePath));
    }

    @Builder
    public ImageContent(File imageFile) {
        this.type = "image";
        try {
            this.source = new ImageSource(imageFile);
        } catch (IOException e) {
            if( imageFile != null ) {
                throw new UnableToAccessImageContentException(imageFile.getAbsolutePath(), e);
            } else {
                throw new UnableToAccessImageContentException(e);
            }
        }
    }

    private static File getFile(String imageFilePath) {
        return new File(imageFilePath);
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