package io.github.biezhi.json.gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import io.github.biezhi.json.gson.model.Health;

/**
 * 序列化、发序列化枚举
 *
 * @author biezhi
 * @date 2018/1/16
 */
public class SerializeEnums {

    public static void main(String[] args) {
        Health health = new Health("host", "ip", Health.Status.UP);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String result = gson.toJson(health);
        System.out.println(result);

        String input = "{\n" +
                "  \"hostname\": \"host\",\n" +
                "  \"ip\": \"ip\",\n" +
                "  \"startTime\": 1457088308405,\n" +
                "  \"upTime\": 62,\n" +
                "  \"status\": \"1\"\n" +
                "}";
        Health health2 = gson.fromJson(input, Health.class);
        System.out.println(health2);
    }
}
