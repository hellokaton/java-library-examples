package io.github.biezhi.json.jackson;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.biezhi.json.jackson.model.Employee;
import io.github.biezhi.utils.PrintUtils;

import java.io.IOException;

/**
 * Jackson 注解设置JSON字段示例
 *
 * @author biezhi
 * @date 2018/1/15
 */
public class AnnotationFieldExample {

    private static final ObjectMapper mapper = new ObjectMapper();

    public static void main(String[] args) throws IOException {
        Employee employee = new Employee();
        employee.setName("biezhi");
        employee.setDept("Admin");

        String jsonString = mapper.writeValueAsString(employee);
        PrintUtils.print("转换成JSON", jsonString);

        Employee e = mapper.readValue(jsonString, Employee.class);
        PrintUtils.print("JSON转换为对象", e);
    }

}
