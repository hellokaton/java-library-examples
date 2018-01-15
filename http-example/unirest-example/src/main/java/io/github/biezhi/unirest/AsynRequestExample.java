package io.github.biezhi.unirest;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.async.Callback;
import com.mashape.unirest.http.exceptions.UnirestException;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * Unirest 异步请求示例
 *
 * @author biezhi
 * @date 2018/1/15
 */
public class AsynRequestExample {

    public static void main(String[] args) throws UnirestException, ExecutionException, InterruptedException {
        Future<HttpResponse<JsonNode>> future = Unirest.post("http://httpbin.org/post")
                .header("accept", "application/json")
                .field("param1", "value1")
                .field("param2", "value2")
                .asJsonAsync(new Callback<JsonNode>() {

                    @Override
                    public void failed(UnirestException e) {
                        System.out.println("The request has failed");
                    }

                    @Override
                    public void completed(HttpResponse<JsonNode> response) {
                        int      code = response.getStatus();
                        JsonNode body = response.getBody();
                        System.out.println("Status Code: " + code);
                        System.out.println(body.toString());
                    }

                    @Override
                    public void cancelled() {
                        System.out.println("The request has been cancelled");
                    }

                });
    }
}