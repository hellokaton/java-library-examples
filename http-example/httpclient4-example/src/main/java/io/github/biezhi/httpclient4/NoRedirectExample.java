package io.github.biezhi.httpclient4;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.IOException;

/**
 * 禁止重定向
 *
 * @author biezhi
 * @date 2018/1/15
 */
public class NoRedirectExample {

    public static void main(String[] args) throws IOException {
        CloseableHttpClient httpclient = HttpClientBuilder.create().disableRedirectHandling().build();

        try (CloseableHttpResponse response = httpclient.execute(new HttpGet("http://httpbin.org/redirect/6"))) {
            System.out.println(response.getStatusLine());
            System.out.println(response.getStatusLine().getStatusCode() == 302);
        }
    }

}
