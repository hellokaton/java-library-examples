package io.github.biezhi.httpclient4;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

/**
 * 获取响应 Media Type
 *
 * @author biezhi
 * @date 2018/1/15
 */
public class GetMimeTypeExample {

    public static void main(String[] args) throws IOException {
        CloseableHttpClient httpclient = HttpClients.createDefault();

        try (CloseableHttpResponse response = httpclient.execute(new HttpGet("http://httpbin.org/anything"))) {
            HttpEntity entity = response.getEntity();
            String contentMimeType = ContentType.getOrDefault(response.getEntity()).getMimeType();
            System.out.println(contentMimeType);
            System.out.println(contentMimeType.equals(ContentType.APPLICATION_JSON.getMimeType()));
            // and ensure it is fully consumed
            EntityUtils.consume(entity);
        }
    }

}
