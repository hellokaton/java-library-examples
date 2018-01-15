package io.github.biezhi.okhttp3;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * 缓存响应
 *
 * @author biezhi
 * @date 2018/1/15
 */
public class CacheResponseExample {

    public static void main(String[] args) throws IOException {

        int cacheSize = 10 * 1024 * 1024;

        File  cacheDirectory = new File("src/test/resources/cache");
        Cache cache          = new Cache(cacheDirectory, cacheSize);

        OkHttpClient client = new OkHttpClient.Builder()
                .cache(cache)
                .build();

        Request request = new Request.Builder()
                .url("http://publicobject.com/helloworld.txt")
                .build();

        try (Response response = client.newCall(request).execute()) {
            System.out.println(response.body().string());
        }

        try (Response response = client.newCall(request).execute()) {
            System.out.println(response.body().string());
        }

    }

}
