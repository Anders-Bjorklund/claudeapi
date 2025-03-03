package se.hackney.claude.request;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Base64;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class ImageSource {
    private String type;

    @JsonProperty("media_type")
    private String mediaType;
    private String data;

    public ImageSource() {
    }

    public ImageSource(String imageFile) throws IOException {
        this.type = "base64";
        this.mediaType = determineMediaType(imageFile);
        this.data = encodeFileToBase64(imageFile);
    }

    public ImageSource(String imageFile, byte[] fileContent) {
        this.type = "base64";
        this.mediaType = determineMediaType(imageFile);
        this.data = Base64.getEncoder().encodeToString(fileContent);
    }

    private String determineMediaType(String fileName) {
        String extension = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
        return switch (extension) {
            case "jpg", "jpeg" -> "image/jpeg";
            case "png" -> "image/png";
            case "gif" -> "image/gif";
            default -> throw new IllegalArgumentException("Unsupported image format: " + extension);
        };
    }

    private String encodeFileToBase64(String filePath) throws IOException {
        byte[] fileContent = Files.readAllBytes(Path.of(filePath));
        return Base64.getEncoder().encodeToString(fileContent);
    }
}
