package io.github.biezhi.json.gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.github.biezhi.json.gson.model.UserFloat;

/**
 * 解析特殊类型 Float 和 Double
 *
 * @author biezhi
 * @date 2018/2/2
 */
public class FloatExample {
    public static void main(String[] args) {
        UserFloat user = new UserFloat("Norman", Float.POSITIVE_INFINITY);

        Gson gson = new Gson();

        // will throw an exception
//        String usersJson = gson.toJson(user);
//        System.out.println("userJson:" + usersJson);

        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.serializeSpecialFloatingPointValues();
        gson = gsonBuilder.create();
        UserFloat userFloat = new UserFloat("Norman", Float.POSITIVE_INFINITY);
        String usersJson = gson.toJson(userFloat);
        System.out.println("userJson:" + usersJson);

    }
}
