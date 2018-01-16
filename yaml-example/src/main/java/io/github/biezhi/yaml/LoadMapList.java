package io.github.biezhi.yaml;

import org.yaml.snakeyaml.Yaml;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

public class LoadMapList {
    public static void main(String[] args) throws IOException {
        loadFromFile("/fruitsGroup.yml");
    }

    private static void loadFromFile(String path) throws IOException {
        System.out.printf("-- loading from %s --%n", path);
        Yaml yaml = new Yaml();
        try (InputStream in = LoadMapList.class.getResourceAsStream(path)) {
            Iterable<Object> itr = yaml.loadAll(in);
            for (Object o : itr) {
                System.out.println("Loaded object type:" + o.getClass());
                Map<String, List<String>> map = (Map<String, List<String>>) o;
                System.out.println("-- the map --");
                System.out.println(map);
                System.out.println("-- iterating --");
                map.forEach((key, list) -> System.out.println("key: " + key + ", values: " + list));
            }
        }
    }
}