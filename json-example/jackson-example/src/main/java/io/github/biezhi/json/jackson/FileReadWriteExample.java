package io.github.biezhi.json.jackson;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.biezhi.model.Person;
import io.github.biezhi.utils.BeanData;
import io.github.biezhi.utils.PrintUtils;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Jackson 文件读写示例
 *
 * @author biezhi
 * @date 2018/1/15
 */
public class FileReadWriteExample {

    private static final ObjectMapper mapper = new ObjectMapper();

    public static void main(String[] args) throws IOException {
        List<Person> persons = BeanData.randPersons(1);
        mapper.writeValue(new File("jackson_persons.json"), persons);

        List<Person> personList = mapper.readValue(new File("jackson_persons.json"), new TypeReference<List<Person>>(){});
        PrintUtils.print("写入文件读取JSON数据", persons.size() == personList.size());
        PrintUtils.print("写入文件读取JSON数据", personList);
    }

}
