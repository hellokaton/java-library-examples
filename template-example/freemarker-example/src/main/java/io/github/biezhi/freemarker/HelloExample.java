package io.github.biezhi.freemarker;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 * @author biezhi
 * @date 2018/1/16
 */
public class HelloExample {

    public static void main(String[] args) {
        //Freemarker configuration object
        Configuration cfg = new Configuration();
        try {
            String resDir = "template-example/freemarker-example/src/main/resources";

            //Load template from source folder
            Template template = cfg.getTemplate(resDir + "/hello.ftl");

            // Build the data-model
            Map<String, Object> data = new HashMap<>();
            data.put("message", "你好，王爵nice!");

            //List parsing
            List<String> countries = new ArrayList<>();
            countries.add("印第安");
            countries.add("美国");
            countries.add("中国");
            countries.add("法国");

            data.put("countries", countries);

            // Console output
            Writer out = new OutputStreamWriter(System.out);
            template.process(data, out);
            out.flush();

            // File output
            Writer file = new FileWriter (new File(resDir + "/FTL_helloworld.txt"));
            template.process(data, file);
            file.flush();
            file.close();

        } catch (IOException | TemplateException e) {
            e.printStackTrace();
        }
    }
}
