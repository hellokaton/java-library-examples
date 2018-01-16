package io.github.biezhi.yaml;

import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;

import java.util.HashMap;
import java.util.Map;

/**
 * map转yaml，根据配置
 */
public class JavaToYamlWithOptions {

    public static void main(String[] args) {
        Map<String, Map<String, String>> map = createMap();

        DumperOptions options = new DumperOptions();
        options.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);
        options.setPrettyFlow(true);

        Yaml yaml = new Yaml(options);
        String output = yaml.dump(map);
        System.out.println(output);
    }

    private static Map<String, Map<String, String>> createMap() {
        Map<String, Map<String, String>> map = new HashMap<>();
        for (int i = 1; i <= 3; i++) {
            Map<String, String> map2 = new HashMap<>();
            map2.put("key1" + i, "value1" + i);
            map2.put("key2" + i, "value2" + i);
            map2.put("key3" + i, "value4" + i);
            map.put("key" + i, map2);
        }
        return map;
    }
}