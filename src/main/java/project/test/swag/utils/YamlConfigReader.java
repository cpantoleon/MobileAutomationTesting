package project.test.swag.utils;

import java.io.InputStream;
import java.util.Map;
import org.yaml.snakeyaml.Yaml;

public class YamlConfigReader {

    public static Map<String, Object> readConfig(String fileName) {
        Map<String, Object> configData = null;
        try (InputStream inputStream = YamlConfigReader.class.getClassLoader().getResourceAsStream(fileName)) {
            if (inputStream == null) {
                throw new IllegalArgumentException("File not found: " + fileName);
            }
            Yaml yaml = new Yaml();
            configData = yaml.load(inputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return configData;
    }
}