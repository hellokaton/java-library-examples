package io.github.biezhi.json.fastjson;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import io.github.biezhi.model.Person;
import io.github.biezhi.utils.BeanData;
import io.github.biezhi.utils.PrintUtils;

import java.util.List;

/**
 * Java对象转字符串
 *
 * @author biezhi
 * @date 2018/1/15
 */
public class FastjsonExample1 {

    public static void main(String[] args) {

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

}
