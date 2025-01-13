package se.hackney.claude;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Base64;

import lombok.Data;

@Data
public class ImageSource {
    private String type;
    private String mediaType;
    private String data;

    public ImageSource(File imageFile) throws IOException {
        this.type = "base64";
        this.mediaType = determineMediaType(imageFile.getName());
        this.data = encodeFileToBase64(imageFile);
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

    private String encodeFileToBase64(File file) throws IOException {
        byte[] fileContent = Files.readAllBytes(file.toPath());
        return Base64.getEncoder().encodeToString(fileContent);
    }
}
