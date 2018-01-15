package io.github.biezhi.unirest;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

/**
 * Unirest 自定义Body请求示例
 *
 * @author biezhi
 * @date 2018/1/15
 */
public class CustomBodyExample {

    public static void main(String[] args) throws UnirestException {
        HttpResponse<JsonNode> jsonResponse = Unirest.post("http://httpbin.org/post")
                .header("accept", "application/json")
                .body("{\"parameter\":\"value\", \"foo\":\"bar\"}")
                .asJson();

        System.out.println(jsonResponse.getBody().toString());
    }
}