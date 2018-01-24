package io.github.biezhi.httpclient4;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

/**
 * Get 请求示例
 *
 * @author biezhi
 * @date 2018/1/15
 */
public class GetExample {

    public static void main(String[] args) throws IOException {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        try (CloseableHttpResponse response = httpclient.execute(new HttpGet("http://httpbin.org/get?hello=world"))) {
            HttpEntity entity = response.getEntity();

            System.out.println("Response Code : " + response.getStatusLine().getStatusCode());
            System.out.println(EntityUtils.toString(response.getEntity()));
            // and ensure it is fully consumed
            EntityUtils.consume(entity);
        }

    }

}
