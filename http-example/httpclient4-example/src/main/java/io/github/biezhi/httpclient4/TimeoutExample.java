package io.github.biezhi.httpclient4;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;

/**
 * 请求超时设置
 *
 * @author biezhi
 * @date 2018/1/15
 */
public class TimeoutExample {

    public static void main(String[] args) throws IOException {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectionRequestTimeout(1000).setConnectTimeout(1000).setSocketTimeout(1000).build();

        HttpGet httpGet = new HttpGet("http://httpbin.org/anything");
        httpGet.setConfig(requestConfig);

        try (CloseableHttpResponse response = httpclient.execute(httpGet)) {
            System.out.println(response.getStatusLine());
        }
    }

}
