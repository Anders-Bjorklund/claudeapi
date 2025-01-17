package se.hackney.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.Converter;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import se.hackney.claude.request.Body;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Client {
    private static final Logger LOGGER = LoggerFactory.getLogger(Client.class);
    private static final ObjectMapper mapper = new ObjectMapper();

    public static se.hackney.claude.response.Body call(String apiKey, Body requestBody) {

        OkHttpClient client = new OkHttpClient();

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
            responseJson = response.body().string();

            se.hackney.claude.response.Body responseMessage = mapper.readValue(responseJson,
                    se.hackney.claude.response.Body.class);
            return responseMessage;
        } catch (Exception e) {
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
