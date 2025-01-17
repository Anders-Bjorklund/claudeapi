package se.hackney.claude.request;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import lombok.Data;

@Data

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes({
    @JsonSubTypes.Type(value = TextContent.class, name = "text"),
    @JsonSubTypes.Type(value = ImageContent.class, name = "image")
})
public abstract class Content {
    String type;
}

