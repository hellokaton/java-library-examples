package io.github.biezhi.okhttp3;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.riversun.okhttp3.OkHttp3CookieHelper;

import java.io.IOException;

/**
 * 设置 Cookie
 *
 * @author biezhi
 * @date 2018/1/15
 */
public class CookieExample {

    public static void main(String[] args) throws IOException {

        String url = "http://httpbin.org/cookies";
        OkHttp3CookieHelper cookieHelper = new OkHttp3CookieHelper();

        //force set cookie from the client
        cookieHelper.setCookie(url, "BID_SESSION", "1233771");

        // init OkHttpClient
        OkHttpClient client = new OkHttpClient.Builder()
                .cookieJar(cookieHelper.cookieJar())
                .build();

        Request request = new Request.Builder()
                .url(url)
                .build();

        try (Response response = client.newCall(request).execute()) {
            System.out.println(response.body().string());
        }

    }

}
