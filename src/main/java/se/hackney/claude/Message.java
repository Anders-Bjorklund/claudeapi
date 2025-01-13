package se.hackney.claude;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Message {
    private String role;
    private List<Content> content;
}
