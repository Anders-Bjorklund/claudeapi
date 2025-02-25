package se.hackney.client;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

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

public class ClientTest {

    @Test
    public void testCall() {

        String apiKey = System.getenv("ANTHROPIC_API_KEY");

        List<Message> messages = Arrays.asList(new Message[] {
                Message.builder().role(Role.USER.toString()).content(Arrays.asList(new Content[] {
                        new TextContent("Vad är det för djur du ser i bilden nedan?"),
                        new ImageContent("src/test/resources/African_penguin.jpg")
                })).build()
        });

        Body requestBody = Body.builder()
                .model(Model.CLAUDE_3_7_SONNET.getAnthropicId())
                .maxTokens(64000)
                .messages(messages)
                .build();

                try {
                        System.out.println( "TRÄD:\n" + new ObjectMapper().writeValueAsString(requestBody));
                } catch (JsonProcessingException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                }
        se.hackney.claude.response.Body response = Client.call(apiKey, requestBody);
        
        System.out.println(response.getContent().toString());
        assertTrue(response.getText().indexOf("pingvin") != -1);

    }
}
