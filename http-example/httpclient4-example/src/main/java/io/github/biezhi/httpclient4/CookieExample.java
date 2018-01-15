package io.github.biezhi.httpclient4;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * 设置和获取Cookie
 *
 * @author biezhi
 * @date 2018/1/15
 */
public class CookieExample {

    public static void main(String[] args) throws IOException {
        CloseableHttpClient httpclient = HttpClients.createDefault();

        BasicCookieStore cookieStore = new BasicCookieStore();
        BasicClientCookie cookie = new BasicClientCookie("BIEZHI_SESSIONID", "1234");
        cookie.setDomain(".httpbin.org");
        cookie.setPath("/");
        cookieStore.addCookie(cookie);

        HttpContext localContext = new BasicHttpContext();
        localContext.setAttribute(HttpClientContext.COOKIE_STORE, cookieStore);

        try (CloseableHttpResponse response = httpclient.execute(new HttpGet("http://httpbin.org/cookies"), localContext)) {
            System.out.println(EntityUtils.toString(response.getEntity()));
            System.out.println(cookieStore.getCookies());
        }
    }

}
