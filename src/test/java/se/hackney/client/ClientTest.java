package se.hackney.client;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

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
                .model(Model.CLAUDE_3_5_SONNET.getAnthropicId())
                .maxTokens(8192)
                .messages(messages)
                .build();

        se.hackney.claude.response.Body response = Client.call(apiKey, requestBody);

        assertTrue(response.getText().indexOf("pingvin") != -1);

    }
}
