package io.github.biezhi.unirest;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

/**
 * Unirest 路由参数示例
 *
 * @author biezhi
 * @date 2018/1/15
 */
public class RouteParamExample {

    public static void main(String[] args) throws UnirestException {
        HttpResponse<JsonNode> response = Unirest.get("http://httpbin.org/{method}")
                .routeParam("method", "get")
                .queryString("name", "biezhi")
                .asJson();

        System.out.println(response.getBody().toString());
    }
}