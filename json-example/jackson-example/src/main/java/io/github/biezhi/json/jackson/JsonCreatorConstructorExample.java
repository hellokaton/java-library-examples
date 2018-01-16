package io.github.biezhi.json.jackson;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.biezhi.utils.PrintUtils;
import lombok.Data;

import java.io.IOException;

/**
 * JsonCreator 构造函数示例
 *
 * @author biezhi
 * @date 2018/1/16
 */
public class JsonCreatorConstructorExample {

    private static final ObjectMapper mapper = new ObjectMapper();

    private static Employee toEmployee(String jsonData) throws IOException {
        ObjectMapper om = new ObjectMapper();
        return om.readValue(jsonData, Employee.class);
    }

    private static String toJson(Employee employee) throws IOException {
        ObjectMapper om = new ObjectMapper();
        return om.writeValueAsString(employee);
    }

    public static void main(String[] args) throws IOException {
        Employee employee = new Employee("biezhi", "Admin");
        //convert to json
        String jsonString = toJson(employee);
        PrintUtils.print("转换为JSON", jsonString);

        Employee e = toEmployee(jsonString);
        PrintUtils.print("JSON转换为对象", e);
    }

    @Data
    private static class Employee {
        private String name;
        private String dept;

        @JsonCreator
        public Employee(@JsonProperty("name") String name, @JsonProperty("dept") String dept) {
            System.out.println("'构造函数被调用'");
            this.name = name;
            this.dept = dept;
        }

    }

}
