package se.hackney.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import se.hackney.claude.request.Body;
import se.hackney.claude.request.CleverBody;

import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Client {
    private static final Logger LOGGER = LoggerFactory.getLogger(Client.class);
    private static final ObjectMapper mapper = new ObjectMapper()
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    public static se.hackney.claude.response.Body call(String apiKey, Body requestBody) {

        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(60, TimeUnit.SECONDS) // tid för att etablera anslutning
                .writeTimeout(60, TimeUnit.SECONDS) // tid för att skriva data
                .readTimeout(300, TimeUnit.SECONDS) // tid för att läsa data
                .build();

        MediaType JSON_MIME = MediaType.get("application/json; charset=utf-8");
        String requestJson = null;
        try {
            requestJson = mapper.writeValueAsString(requestBody);
        } catch (JsonProcessingException e) {
            throw new NonSerializableRequestBodyException(requestJson);
        }

        Request request = new Request.Builder()
                .url("https://api.anthropic.com/v1/messages")
                .addHeader("x-api-key", apiKey)
                .addHeader("anthropic-version", "2023-06-01")
                .post(RequestBody.create(requestJson, JSON_MIME))
                .build();

        String responseJson = null;
        try (Response response = client.newCall(request).execute()) {

            if (!response.isSuccessful()) {
                if (response.code() == 529) {
                    System.out.println("Overloaded: " + response.code());
                    LOGGER.info(response.body().string());
                    throw new AnthropicServiceIsOverloadedException();
                } else {
                    System.out.println("Unknown exception HTTP code: " + response.code());
                }
            }

            responseJson = response.body().string();
            LOGGER.debug(responseJson);

            se.hackney.claude.response.Body responseMessage = mapper.readValue(responseJson,
                    se.hackney.claude.response.Body.class);
            return responseMessage;
        } catch (Throwable e) {
            e.printStackTrace();
            throw new NonDeserializableResponseBodyException(responseJson);
        }

    }

}

class NonSerializableRequestBodyException extends RuntimeException {
    public NonSerializableRequestBodyException(String message) {
        super(message);
    }
}

class NonDeserializableResponseBodyException extends RuntimeException {
    public NonDeserializableResponseBodyException(String message) {
        super(message);
    }
}