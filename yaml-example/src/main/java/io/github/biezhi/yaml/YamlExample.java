package io.github.biezhi.yaml;

import io.github.biezhi.yaml.model.Person;
import io.github.biezhi.yaml.model.Persons;
import org.yaml.snakeyaml.Yaml;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author biezhi
 * @date 2018/1/15
 */
public class YamlExample {

    private static final Yaml yaml = new Yaml();

    /**
     * 加载配置为Java对象
     *
     * @throws IOException
     */
    public static void loadAsJavaObject() throws IOException {
        try (InputStream in = YamlExample.class.getResourceAsStream("/person.yml")) {
            Person person = yaml.loadAs(in, Person.class);
            System.out.println("1. 加载 Person\r\n" + person);
        }
    }

    /**
     * 加载配置为Java对象列表
     *
     * @throws IOException
     */
    public static void loadAsJavaObjectList() throws IOException {
        try (InputStream in = YamlExample.class.getResourceAsStream("/persons.yml")) {
            Persons persons = yaml.loadAs(in, Persons.class);
            System.out.println("2. 加载 PersonList\r\n" + persons);
        }
    }

    /**
     * 加载配置为Map对象
     *
     * @throws IOException
     */
    public static void loadAsMap() throws IOException {
        System.out.println("3. 加载配置为Map");
        try (InputStream in = YamlExample.class.getResourceAsStream("/person.yml")) {
            Iterable<Object> itr = yaml.loadAll(in);
            for (Object o : itr) {
                System.out.println(o);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        loadAsJavaObject();
        loadAsJavaObjectList();
        loadAsMap();
    }

}
