package io.github.biezhi.okhttp3;

import okhttp3.*;

import java.io.IOException;

/**
 * 基础 Post 请求示例
 *
 * @author biezhi
 * @date 2018/1/15
 */
public class PostExample {

    public static void main(String[] args) throws IOException {

        OkHttpClient client = new OkHttpClient();

        RequestBody formBody = new FormBody.Builder()
                .add("username", "biezhi")
                .add("password", "123456")
                .build();

        Request request = new Request.Builder()
                .url("http://httpbin.org/post")
                .post(formBody)
                .build();

        try (Response response = client.newCall(request).execute()) {
            System.out.println(response.body().string());
        }

    }

}
