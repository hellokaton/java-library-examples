package io.github.biezhi.json.gson;

import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Collection;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

/**
 * 序列化、发序列化集合
 *
 * @author biezhi
 * @date 2018/1/16
 */
public class SerializeCollections {

    public static void main(String[] args) {
        Gson       gson     = new GsonBuilder().create();
        Collection<Integer> integers = Arrays.asList(1, 2, 3);

        // serialization  => [1,2,3]
        gson.toJson(integers);

        // deserialization
        Type       collectionType = new TypeToken<Collection<Integer>>() {}.getType();
        Collection<Integer> integers2      = gson.fromJson("[1,2,3]", collectionType);
        System.out.println(integers2);
    }
}
