package se.hackney.client;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import se.hackney.claude.request.CleverBody;
import se.hackney.claude.request.FastBody;
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

                FastBody fastRequestBody = FastBody.builder()
                                .model(Model.CLAUDE_3_7_SONNET.getAnthropicId())
                                .maxTokens(64000)
                                .messages(messages)
                                .build();

                se.hackney.claude.response.Body response = Client.call(apiKey, fastRequestBody);

                System.out.println(response.getContent().toString());
                assertTrue(response.getText().indexOf("pingvin") != -1);

                CleverBody cleverRequestBody = CleverBody.builder()
                                .model(Model.CLAUDE_3_7_SONNET.getAnthropicId())
                                .maxTokens(64000)
                                .messages(messages)
                                .build();


                response = Client.call(apiKey, cleverRequestBody);

                System.out.println(response.getContent().toString());
                assertTrue(response.getText().indexOf("pingvin") != -1);


        }
}
