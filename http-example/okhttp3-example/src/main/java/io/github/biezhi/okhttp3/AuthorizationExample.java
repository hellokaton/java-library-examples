package io.github.biezhi.okhttp3;

import okhttp3.Credentials;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

/**
 * Authorization 认证示例
 *
 * @author biezhi
 * @date 2018/1/15
 */
public class AuthorizationExample {

    public static void main(String[] args) throws IOException {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("http://httpbin.org/basic-auth/biezhi/passwd")
                .addHeader("Authorization", Credentials.basic("biezhi", "passwd"))
                .build();

        try (Response response = client.newCall(request).execute()) {
            System.out.println(response.body().string());
        }
    }

}
