package io.github.biezhi.json.gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * 序列化、反序列化基本类型
 *
 * @author biezhi
 * @date 2018/1/16
 */
public class SerializePrimitivesTypes {

    public static void main(String[] args) {
        Gson gson = new GsonBuilder().create();
        gson.toJson((byte) 2);           // => 2
        gson.toJson('A');           // => "A"
        gson.toJson("abc");         // => "abc"
        gson.toJson((short) 2);          // => 2
        gson.toJson(1);             // => 1
        gson.toJson(555000333000L); // => 555000333000
        gson.toJson(3.14d);         // => 3.14
        gson.toJson(13.99f);        // => 13.99
        gson.toJson(true);          // => true
        int[] values = {1, 2, 3};
        gson.toJson(values);             // => [1,2,3]


        byte    byteResult     = gson.fromJson("2", byte.class);
        char    charResult     = gson.fromJson("A", char.class);
        String  stringResult   = gson.fromJson("abc", String.class);
        Short   shortResult    = gson.fromJson("2", Short.class);
        int     intResult      = gson.fromJson("1", int.class);
        Integer integerResult  = gson.fromJson("1", Integer.class);
        Long    longResult     = gson.fromJson("555000333000", Long.class);
        Double  doubleResult   = gson.fromJson("3.14", Double.class);
        Float   floatResult    = gson.fromJson("13.99", Float.class);
        Boolean booleanResult  = gson.fromJson("true", Boolean.class);
        int[]   intArrayResult = gson.fromJson("[1,2,3]", int[].class);

    }
}
