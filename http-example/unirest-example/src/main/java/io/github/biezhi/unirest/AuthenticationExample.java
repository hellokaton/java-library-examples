package io.github.biezhi.unirest;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

/**
 * Unirest Basic认证示例
 *
 * @author biezhi
 * @date 2018/1/15
 */
public class AuthenticationExample {

    public static void main(String[] args) throws UnirestException {
        HttpResponse<JsonNode> jsonResponse = Unirest.get("http://httpbin.org/basic-auth/biezhi/123456")
                .basicAuth("biezhi", "123456").asJson();

        System.out.println(jsonResponse.getBody().toString());
    }
}