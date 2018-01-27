package io.github.biezhi.json.gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import io.github.biezhi.model.Person;
import io.github.biezhi.utils.BeanData;
import io.github.biezhi.utils.PrintUtils;

import java.util.List;
import java.util.Map;

/**
 * Gson 示例
 *
 * @author biezhi
 * @date 2018/1/15
 */
public class BasicExample {

    private static final Gson gson = new Gson();

    public static void toJson() {
        /**
         * 单个对象
         */
        Person person = BeanData.randPerson();
        PrintUtils.print("Person 对象序列化为 JSON 字符串", gson.toJson(person));

        /**
         * 美观格式化
         */
        PrintUtils.print("美观格式化", "\r\n" + new GsonBuilder().setPrettyPrinting().create().toJson(person));

        /**
         * List对象
         */
        List<Person> persons = BeanData.randPersons(5);
        PrintUtils.print("Person 列表序列化为 JSON 字符串", gson.toJson(persons));

        /**
         * 日期格式化
         */
        Gson gson = new GsonBuilder().setDateFormat("yyyy年MM月dd日").create();
        PrintUtils.print("日期格式化", gson.toJson(person));

    }

    public static void fromJson() {
        String signleJson = "{\"address\":{\"city\":\"cBaQKxIy\",\"country\":\"edUsFwdkelQbxeTeQOva\",\"zipCode\":\"eOMtThyhVNLWUZNR\"},\"birthDate\":\"Mar 4, 2018 4:46:59 AM\",\"gender\":\"FEMALE\",\"id\":106534569952410475,\"name\":\"DYpsBZxvf\",\"nicknames\":[\"aJxkyvRnL\",\"RYtGKbgicZaHCB\",\"RQDSxVLhpfQGTM\"],\"phoneNumber\":\"ScfqIOOma\"}";
        Person person     = gson.fromJson(signleJson, Person.class);
        PrintUtils.print("反序列化为 Person", person);

        Map<String, Object> map = gson.fromJson(signleJson, new TypeToken<Map<String, Object>>(){}.getType());
        PrintUtils.print("反序列化为 Map", map);

        String       jsonList   = "[{\"address\":{\"city\":\"LsNlAeL\",\"country\":\"WVhnI\",\"zipCode\":\"nAzTacoMOyybxV\"},\"birthDate\":\"Mar 4, 2018 4:46:59 AM\",\"gender\":\"FEMALE\",\"id\":298228485105199876,\"name\":\"lMWOIVYZjIE\",\"nicknames\":[\"eWTdXPlQgjMVXbpR\",\"YzBTjz\",\"lglRKAeamYUmWJt\",\"nJZLqwakeYceaYFBlW\",\"coVdFX\",\"lfhbUil\",\"kMpiHDMUveErKUBEqCrh\",\"rtgxJkjHewS\"],\"phoneNumber\":\"ULZAyLBmsLcd\"}]";
        List<Person> personList = gson.fromJson(jsonList, new TypeToken<List<Person>>(){}.getType());
        PrintUtils.print("反序列化为 List<Person>", personList);
    }

    public static void main(String[] args) {
        toJson();
        fromJson();
    }

}
