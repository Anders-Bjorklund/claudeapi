package se.hackney.anthropic;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import org.junit.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class RequestTest {

    @Test
    public void requestTest() {

        Request request = Request.builder().model(Model.CLAUDE_3_5_SONNET.getAnthropicId()).maxTokens(8192).build();

        try {
            Content[] content = {
                    TextContent.builder().text("<FORM>").build(),
                    ImageContent.builder().imageFile(new File("src\\test\\resources\\African_penguin,_Cape_Town_(_1050598).jpg")).build(),
                    TextContent.builder().text("</FORM>").build()
            };

            Message message = Message.builder().role("user").content(Arrays.asList(content)).build();
            request.setMessages(Arrays.asList(new Message[]{message}));

        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            System.out.println(new ObjectMapper().writeValueAsString(request));
        } catch (JsonProcessingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        // Message message = Message.builder().
    }
}
