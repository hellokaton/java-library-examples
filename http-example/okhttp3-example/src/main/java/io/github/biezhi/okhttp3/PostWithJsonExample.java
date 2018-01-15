package io.github.biezhi.okhttp3;

import okhttp3.*;

import java.io.IOException;

/**
 * Post With Json 请求示例
 *
 * @author biezhi
 * @date 2018/1/15
 */
public class PostWithJsonExample {

    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    OkHttpClient client = new OkHttpClient();

    private String post(String url, String json) throws IOException {
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }

    private String bowlingJson(String player1, String player2) {
        return "{'winCondition':'HIGH_SCORE',"
                + "'name':'Bowling',"
                + "'round':4,"
                + "'lastSaved':1367702411696,"
                + "'dateStarted':1367702378785,"
                + "'players':["
                + "{'name':'" + player1 + "','history':[10,8,6,7,8],'color':-13388315,'total':39},"
                + "{'name':'" + player2 + "','history':[6,10,5,10,10],'color':-48060,'total':41}"
                + "]}";
    }

    public static void main(String[] args) throws IOException {
        PostWithJsonExample example  = new PostWithJsonExample();
        String              json     = example.bowlingJson("biezhi", "Jake");
        String              response = example.post("http://www.roundsapp.com/post", json);
        System.out.println(response);
    }

}
