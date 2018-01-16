package io.github.biezhi.json.jackson;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.biezhi.json.jackson.model.Employee2;
import io.github.biezhi.json.jackson.model.Employee3;
import io.github.biezhi.utils.PrintUtils;

import java.io.IOException;

/**
 * Jackson 注解忽略类级别示例
 *
 * @author biezhi
 * @date 2018/1/15
 */
public class IgnoreClassExample {

    private static final ObjectMapper mapper = new ObjectMapper();

    public static void main(String[] args) throws IOException {
        Employee3 employee = new Employee3();
        employee.setName("biezhi");
        employee.setDept("Admin");
        employee.setAddress("ShangHai");

        String jsonString = mapper.writeValueAsString(employee);
        PrintUtils.print("转换成JSON", jsonString);

        Employee3 e = mapper.readValue(jsonString, Employee3.class);
        PrintUtils.print("JSON转换为对象", e);

        //忽略 address
        String s = "{\"name\":\"biezhi\",\"dept\":\"Admin\", \"address\":\"xyz Street\"}";
        System.out.println("JSON input: " + s);
        Employee3 e2 = mapper.readValue(s, Employee3.class);
        PrintUtils.print("JSON转换为对象", e2);

    }

}
