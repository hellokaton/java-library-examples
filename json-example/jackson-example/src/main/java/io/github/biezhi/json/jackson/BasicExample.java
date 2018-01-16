package io.github.biezhi.json.jackson;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.biezhi.model.Person;
import io.github.biezhi.utils.BeanData;
import io.github.biezhi.utils.PrintUtils;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

/**
 * Jackson 基础示例
 *
 * @author biezhi
 * @date 2018/1/15
 */
public class BasicExample {

    private static final ObjectMapper mapper = new ObjectMapper();

    public static void toJson() throws JsonProcessingException {
        /**
         * 单个对象
         */
        Person person = BeanData.randPerson();
        PrintUtils.print("Person 对象序列化为 JSON 字符串", mapper.writeValueAsString(person));

        /**
         * 美观格式化
         */
        PrintUtils.print("美观格式化", "\r\n" + mapper.writerWithDefaultPrettyPrinter().writeValueAsString(person));

        /**
         * List对象
         */
        List<Person> persons = BeanData.randPersons(5);
        PrintUtils.print("Person 列表序列化为 JSON 字符串", mapper.writeValueAsString(persons));

        /**
         * 日期格式化
         */
        SimpleDateFormat df = new SimpleDateFormat("yyyy年MM月dd日");
        mapper.setDateFormat(df);
        PrintUtils.print("日期格式化", mapper.writeValueAsString(person));

    }

    public static void fromJson() throws IOException {
        String signleJson = "{\"address\":{\"city\":\"cBaQKxIy\",\"country\":\"edUsFwdkelQbxeTeQOva\",\"zipCode\":\"eOMtThyhVNLWUZNR\"},\"birthDate\":1520110019921,\"gender\":\"FEMALE\",\"id\":106534569952410475,\"name\":\"DYpsBZxvf\",\"nicknames\":[\"aJxkyvRnL\",\"RYtGKbgicZaHCB\",\"RQDSxVLhpfQGTM\"],\"phoneNumber\":\"ScfqIOOma\"}";
        Person person     = mapper.readValue(signleJson, Person.class);
        PrintUtils.print("反序列化为 Person", person);

        Map<String, Object> map = mapper.readValue(signleJson, new TypeReference<Map<String, Object>>(){});
        PrintUtils.print("反序列化为 Map", map);

        String       jsonList   = "[{\"address\":{\"city\":\"LsNlAeL\",\"country\":\"WVhnI\",\"zipCode\":\"nAzTacoMOyybxV\"},\"birthDate\":1520110019921,\"gender\":\"FEMALE\",\"id\":298228485105199876,\"name\":\"lMWOIVYZjIE\",\"nicknames\":[\"eWTdXPlQgjMVXbpR\",\"YzBTjz\",\"lglRKAeamYUmWJt\",\"nJZLqwakeYceaYFBlW\",\"coVdFX\",\"lfhbUil\",\"kMpiHDMUveErKUBEqCrh\",\"rtgxJkjHewS\"],\"phoneNumber\":\"ULZAyLBmsLcd\"}]";
        List<Person> personList = mapper.readValue(jsonList, new TypeReference<List<Person>>(){});
        PrintUtils.print("反序列化为 List<Person>", personList);
    }

    public static void main(String[] args) throws IOException {
        toJson();
        fromJson();
    }

}
