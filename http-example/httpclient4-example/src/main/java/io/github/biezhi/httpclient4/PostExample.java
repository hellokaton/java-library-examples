package io.github.biezhi.httpclient4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

/**
 * Post 请求示例
 *
 * @author biezhi
 * @date 2018/1/15
 */
public class PostExample {

    public static void main(String[] args) throws IOException {
        CloseableHttpClient httpclient = HttpClients.createDefault();

        HttpPost            httpPost   = new HttpPost("http://httpbin.org/post");
        List<NameValuePair> nvps       = new ArrayList<>();
        nvps.add(new BasicNameValuePair("username", "biezhi"));
        nvps.add(new BasicNameValuePair("password", "secret"));
        httpPost.setEntity(new UrlEncodedFormEntity(nvps));

        try (CloseableHttpResponse response = httpclient.execute(httpPost)) {
            HttpEntity entity = response.getEntity();

            BufferedReader rd = new BufferedReader(new InputStreamReader(entity.getContent()));
            StringBuffer result = new StringBuffer();
            String       line;
            while ((line = rd.readLine()) != null) {
                result.append(line).append("\r\n");
            }
            System.out.println(result);
            // and ensure it is fully consumed
            EntityUtils.consume(entity);
        }
    }

}
