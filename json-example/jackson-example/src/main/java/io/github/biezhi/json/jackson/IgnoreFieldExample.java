package io.github.biezhi.json.jackson;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.biezhi.json.jackson.model.Employee;
import io.github.biezhi.json.jackson.model.Employee2;
import io.github.biezhi.utils.PrintUtils;

import java.io.IOException;

/**
 * Jackson 注解忽略字段级别示例
 *
 * @author biezhi
 * @date 2018/1/15
 */
public class IgnoreFieldExample {

    private static final ObjectMapper mapper = new ObjectMapper();

    public static void main(String[] args) throws IOException {
        Employee2 employee = new Employee2();
        employee.setName("biezhi");
        employee.setDept("Admin");
        employee.setAddress("ShangHai");

        String jsonString = mapper.writeValueAsString(employee);
        PrintUtils.print("转换成JSON", jsonString);

        Employee2 e = mapper.readValue(jsonString, Employee2.class);
        PrintUtils.print("JSON转换为对象", e);

        //忽略 address
        String s = "{\"name\":\"biezhi\",\"dept\":\"Admin\", \"address\":\"xyz Street\"}";
        System.out.println("JSON input: " + s);
        Employee2 e2 = mapper.readValue(s, Employee2.class);
        PrintUtils.print("JSON转换为对象", e2);

    }

}
