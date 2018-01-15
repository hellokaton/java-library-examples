package io.github.biezhi.okhttp3;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

/**
 * 禁止重定向
 *
 * @author biezhi
 * @date 2018/1/15
 */
public class NoRedirectExample {

    public static void main(String[] args) throws IOException {

        OkHttpClient client = new OkHttpClient().newBuilder()
                .followRedirects(false)
                .build();

        Request request = new Request.Builder()
                .url("http://t.co/I5YYd9tddw")
                .build();

        try (Response response = client.newCall(request).execute()) {
            System.out.println(response.body().string());
        }
    }

}
