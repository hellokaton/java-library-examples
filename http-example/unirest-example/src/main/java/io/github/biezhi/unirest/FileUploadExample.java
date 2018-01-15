package io.github.biezhi.unirest;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import java.io.File;

/**
 * Unirest 文件上传示例
 *
 * @author biezhi
 * @date 2018/1/15
 */
public class FileUploadExample {

    public static void main(String[] args) throws UnirestException {
        HttpResponse<JsonNode> jsonResponse = Unirest.post("http://httpbin.org/post")
                .header("accept", "application/json")
                .field("parameter", "value")
                .field("file", new File("/tmp/file"))
                .asJson();

        System.out.println(jsonResponse.getBody().toString());
    }
}