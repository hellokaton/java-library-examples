package io.github.biezhi.okhttp3;

import okhttp3.*;

import java.io.File;
import java.io.IOException;

/**
 * Post With Multipart 请求示例
 *
 * @author biezhi
 * @date 2018/1/15
 */
public class MultipartExample {

    public static void main(String[] args) throws IOException {
        OkHttpClient client = new OkHttpClient();

        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("username", "test")
                .addFormDataPart("password", "test")
                .addFormDataPart("file", "file.txt",
                        RequestBody.create(MediaType.parse("application/octet-stream"),
                                new File("src/test/resources/hello.txt")))
                .build();

        Request request = new Request.Builder()
                .url("multipart_url")
                .post(requestBody)
                .build();

        try (Response response = client.newCall(request).execute()) {
            System.out.println(response.body().string());
        }
    }

}
