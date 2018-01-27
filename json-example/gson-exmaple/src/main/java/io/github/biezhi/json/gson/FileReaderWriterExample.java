package io.github.biezhi.json.gson;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import io.github.biezhi.model.Person;
import io.github.biezhi.utils.BeanData;
import io.github.biezhi.utils.PrintUtils;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * Gson 文件读写示例
 *
 * @author biezhi
 * @date 2018/1/15
 */
public class FileReaderWriterExample {

    private static final Gson gson = new Gson();

    public static void main(String[] args) throws IOException {
        List<Person> persons = BeanData.randPersons(1);
        try (FileWriter writer = new FileWriter("persons.json")) {
            gson.toJson(persons, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<Person> personList = gson.fromJson(new FileReader("persons.json"), new TypeToken<List<Person>>(){}.getType());
        PrintUtils.print("写入文件读取JSON数据", persons.size() == personList.size());
        PrintUtils.print("写入文件读取JSON数据", personList);
    }

}
