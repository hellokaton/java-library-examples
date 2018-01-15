package io.github.biezhi.unirest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.ObjectMapper;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import io.github.biezhi.unirest.model.Author;
import io.github.biezhi.unirest.model.Book;

import java.io.IOException;

/**
 * Unirest 序列化示例
 *
 * @author biezhi
 * @date 2018/1/15
 */
public class SerializationExample {

    public static void main(String[] args) throws UnirestException {
        // Only one time
        Unirest.setObjectMapper(new ObjectMapper() {
            private com.fasterxml.jackson.databind.ObjectMapper jacksonObjectMapper
                    = new com.fasterxml.jackson.databind.ObjectMapper();

            @Override
            public <T> T readValue(String value, Class<T> valueType) {
                try {
                    return jacksonObjectMapper.readValue(value, valueType);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

            @Override
            public String writeValue(Object value) {
                try {
                    return jacksonObjectMapper.writeValueAsString(value);
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        // Response to Object
        HttpResponse<Book> bookResponse = Unirest.get("http://httpbin.org/books/1").asObject(Book.class);
        Book               bookObject   = bookResponse.getBody();

        HttpResponse<Author> authorResponse = Unirest.get("http://httpbin.org/books/{id}/author")
                .routeParam("id", bookObject.getId())
                .asObject(Author.class);

        Author authorObject = authorResponse.getBody();

        // Object to Json
        HttpResponse<JsonNode> postResponse = Unirest.post("http://httpbin.org/authors/post")
                .header("accept", "application/json")
                .header("Content-Type", "application/json")
                .body(authorObject)
                .asJson();

        System.out.println(postResponse.getBody().toString());
    }
}