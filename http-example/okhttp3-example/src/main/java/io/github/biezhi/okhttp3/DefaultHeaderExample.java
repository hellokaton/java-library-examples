package io.github.biezhi.okhttp3;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

/**
 * 设置默认 Header
 *
 * @author biezhi
 * @date 2018/1/15
 */
public class DefaultHeaderExample {

    static class DefaultContentTypeInterceptor implements Interceptor {
        private String contentType;

        public DefaultContentTypeInterceptor(String contentType) {
            this.contentType = contentType;
        }

        @Override
        public Response intercept(Interceptor.Chain chain)
                throws IOException {

            Request originalRequest = chain.request();
            Request requestWithUserAgent = originalRequest
                    .newBuilder()
                    .header("Content-Type", contentType)
                    .build();
            return chain.proceed(requestWithUserAgent);
        }
    }

    public static void main(String[] args) throws IOException {

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new DefaultContentTypeInterceptor("application/json"))
                .build();

        Request request = new Request.Builder()
                .url("SAMPLE_URL")
                .build();

        try (Response response = client.newCall(request).execute()) {
            System.out.println(response.body().string());
        }
    }

}
