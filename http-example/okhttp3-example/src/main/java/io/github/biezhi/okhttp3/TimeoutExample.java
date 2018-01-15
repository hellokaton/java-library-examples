package io.github.biezhi.okhttp3;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * 设置超时
 *
 * @author biezhi
 * @date 2018/1/15
 */
public class TimeoutExample {

    public static void main(String[] args) throws IOException {

        OkHttpClient client = new OkHttpClient.Builder()
                .readTimeout(1, TimeUnit.SECONDS)
                .build();

        Request request = new Request.Builder()
                .url("http://example.org/delay/2")
                .build();

        try (Response response = client.newCall(request).execute()) {
            System.out.println(response.body().string());
        }
    }

}
