package io.github.biezhi.yaml;

import org.yaml.snakeyaml.Yaml;

import java.util.HashMap;
import java.util.Map;

/**
 * Map 转 yaml配置
 */
public class JavaToYamlExample {

  public static void main(String[] args) {
      Map<String, Map<String, String>> map    = createMap();
      Yaml                             yaml   = new Yaml();
      String                           output = yaml.dump(map);
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