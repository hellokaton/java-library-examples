package io.github.biezhi.json.gson.model;

import com.google.gson.annotations.Expose;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserSimple {

    /**
     * equals serialize & deserialize
     */
    @Expose()
    String name;

    /**
     * equals neither serialize nor deserialize
     */
    @Expose(serialize = false, deserialize = false)
    String email;

    /**
     * equals only deserialize
     */
    @Expose(serialize = false)
    int age;

    /**
     * equals only serialize
     *
     * 使用 transient 来描述字段，将不能被序列化和反序列化
     */
    @Expose(deserialize = false)
    /*transient*/ boolean isDeveloper;

}