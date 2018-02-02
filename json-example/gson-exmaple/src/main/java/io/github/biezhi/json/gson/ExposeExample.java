package io.github.biezhi.json.gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.github.biezhi.json.gson.model.UserSimple;

/**
 * 使用 @Expose 忽略字段
 *
 * @author biezhi
 * @date 2018/2/2
 */
public class ExposeExample {

    public static void main(String[] args) {

        GsonBuilder builder = new GsonBuilder();
        builder.excludeFieldsWithoutExposeAnnotation();
        Gson gson = builder.create();

        UserSimple userSimple = new UserSimple("biezhi", "biezhi.me@gmail.com", 18, true);

        System.out.println(gson.toJson(userSimple));
    }
}
