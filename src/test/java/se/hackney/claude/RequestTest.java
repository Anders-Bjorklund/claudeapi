package se.hackney.claude;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import org.junit.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import se.hackney.claude.request.Body;
import se.hackney.claude.request.Content;
import se.hackney.claude.request.ImageContent;
import se.hackney.claude.request.Message;
import se.hackney.claude.request.Model;
import se.hackney.claude.request.Role;
import se.hackney.claude.request.TextContent;

public class RequestTest {

    @Test
    public void requestTest() {

        Body request = Body.builder().model(Model.CLAUDE_3_7_SONNET.getAnthropicId()).maxTokens(64000).build();

        Content[] content = {
                TextContent.builder().text("<FORM>").build(),
                ImageContent.builder()
                        .imageFile(new File("src\\test\\resources\\African_penguin.jpg")).build(),
                TextContent.builder().text("</FORM>").build()
        };

        Message message = Message.builder().role(Role.USER.toString()).content(Arrays.asList(content)).build();
        request.setMessages(Arrays.asList(new Message[] { message }));

        try {
            System.out.println(new ObjectMapper().writeValueAsString(request));
        } catch (JsonProcessingException e) {
            throw new RuntimeException("NoPenguinException");
        }
        // Message message = Message.builder().
    }
}
