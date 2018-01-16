package io.github.biezhi.json.jackson;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.biezhi.json.jackson.model.Employee4;
import io.github.biezhi.utils.PrintUtils;

import java.io.IOException;

/**
 * Jackson 注解忽略未知字段示例
 *
 * @author biezhi
 * @date 2018/1/15
 */
public class IgnoreUnknownExample {

    private static final ObjectMapper mapper = new ObjectMapper();

    public static void main(String[] args) throws IOException {
        String s = "{\"name\":\"biezhi\",\"phone\":\"111-111-1111\"}";
        System.out.println("JSON input: " + s);
        Employee4 e2 = mapper.readValue(s, Employee4.class);
        PrintUtils.print("JSON转换为对象", e2);
    }

}
