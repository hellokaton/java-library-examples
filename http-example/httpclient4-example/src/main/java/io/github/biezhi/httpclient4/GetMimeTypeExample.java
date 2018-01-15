package io.github.biezhi.httpclient4;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

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
