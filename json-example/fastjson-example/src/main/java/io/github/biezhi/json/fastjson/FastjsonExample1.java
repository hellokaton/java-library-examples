package io.github.biezhi.json.fastjson;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import io.github.biezhi.model.Person;
import io.github.biezhi.utils.BeanData;
import io.github.biezhi.utils.PrintUtils;

import java.util.List;
import java.util.Map;

/**
 * Java对象转字符串
 *
 * @author biezhi
 * @date 2018/1/15
 */
public class FastjsonExample1 {

    public static void toJson(){
        /**
         * 单个对象
         */
        Person person = BeanData.randPerson();
        PrintUtils.print("Person 对象序列化为 JSON 字符串", JSON.toJSONString(person));

        /**
         * List对象
         */
        List<Person> persons = BeanData.randPersons(5);
        PrintUtils.print("Person 列表序列化为 JSON 字符串", JSON.toJSONString(persons));

        /**
         * 使用单引号
         */
        PrintUtils.print("使用单引号", JSON.toJSONString(person, SerializerFeature.UseSingleQuotes));

        /**
         * 日期格式化
         */
        PrintUtils.print("日期格式化", JSON.toJSONString(person, SerializerFeature.WriteDateUseDateFormat));

        /**
         * 指定日期格式
         */
        PrintUtils.print("指定日期格式", JSON.toJSONStringWithDateFormat(person, "yyyy年MM月dd日"));
    }

    public static void fromJson(){
        String signleJson = "{\"address\":{\"city\":\"cBaQKxIy\",\"country\":\"edUsFwdkelQbxeTeQOva\",\"zipCode\":\"eOMtThyhVNLWUZNR\"},\"birthDate\":1520110019921,\"gender\":\"FEMALE\",\"id\":-5106534569952410475,\"name\":\"DYpsBZxvf\",\"nicknames\":[\"aJxkyvRnL\",\"RYtGKbgicZaHCB\",\"RQDSxVLhpfQGTM\"],\"phoneNumber\":\"ScfqIOOma\"}";
        Person person = JSON.parseObject(signleJson, Person.class);
        PrintUtils.print("反序列化为 Person", person);

        Map<String, Object> map = JSON.parseObject(signleJson).getInnerMap();
        PrintUtils.print("反序列化为 Map", map);

        String jsonList = "[{\"address\":{\"city\":\"LsNlAeL\",\"country\":\"WVhnI\",\"zipCode\":\"nAzTacoMOyybxV\"},\"birthDate\":1522096185361,\"gender\":\"FEMALE\",\"id\":-2298228485105199876,\"name\":\"lMWOIVYZjIE\",\"nicknames\":[\"eWTdXPlQgjMVXbpR\",\"YzBTjz\",\"lglRKAeamYUmWJt\",\"nJZLqwakeYceaYFBlW\",\"coVdFX\",\"lfhbUil\",\"kMpiHDMUveErKUBEqCrh\",\"rtgxJkjHewS\"],\"phoneNumber\":\"ULZAyLBmsLcd\"}]";
        List<Person> personList = JSON.parseArray(jsonList, Person.class);
        PrintUtils.print("反序列化为 List<Person>", personList);
    }

    public static void main(String[] args) {
        toJson();
        fromJson();
    }

}
