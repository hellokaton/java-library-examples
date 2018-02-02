package io.github.biezhi.json.gson;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import io.github.biezhi.json.gson.model.Box;
import io.github.biezhi.json.gson.model.UserSimple;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * 泛型示例
 *
 * @author biezhi
 * @date 2018/2/2
 */
public class GenericsExample {

    public static void main(String[] args) {
        Gson gson = new Gson();

        List<Integer> integerList = new ArrayList<>();
        integerList.add(1);
        integerList.add(2);
        integerList.add(3);

        List<String> stringList = new ArrayList<>();
        stringList.add("1");
        stringList.add("2");
        stringList.add("3");

        Type integerType = new TypeToken<List<Integer>>() {}.getType();
        Type stringType  = new TypeToken<List<String>>() {}.getType();

        String integerJson = gson.toJson(integerList, integerType);
        String stringJson  = gson.toJson(stringList, stringType);

        System.out.println("integerJson = " + integerJson);
        System.out.println("stringJson = " + stringJson);

        System.out.println("\n\n======================\n\n");

        Box<String>  stringBox  = new Box<>("String Type");
        Box<Integer> integerBox = new Box<>(42);
        // the class UserDate is from previous guides (https://futurestud.io/blog/gson-builder-formatting-of-dates-custom-date-time-mapping/)
        Box<UserSimple> complexBox = new Box<>(new UserSimple("Norman", "norman@fs.io", 26, true));

        stringType = new TypeToken<Box<String>>() {}.getType();
        integerType = new TypeToken<Box<Integer>>() {}.getType();
        Type complexType = new TypeToken<Box<UserSimple>>() {}.getType();

        integerJson = gson.toJson(integerBox, integerType);
        stringJson = gson.toJson(stringBox, stringType);
        String complexJson = gson.toJson(complexBox, complexType);

        System.out.println("integerJson = " + integerJson);
        System.out.println("stringJson = " + stringJson);
        System.out.println("complexJson = " + complexJson);

    }

}
