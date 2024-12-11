package project.test.swag.utils;

import java.util.Map;

public class YMLConfig {

    private static String FILE_PATH;

    public static Map<String, Object> configData;
    public static Map<String, Object> configuration;
    public static Map<String, Object> users;
    public static Map<String, Object> valid;
    public static Map<String, Object> invalid;

    public static void initialize(String filePath) {
        FILE_PATH = filePath;
        if (FILE_PATH == null) {
            throw new IllegalStateException("FILE_PATH is not set.");
        }

        configData = YamlConfigReader.readConfig(FILE_PATH);
        if (configData != null) {
            configuration = (Map<String, Object>) configData.get("configuration");
            users = (Map<String, Object>) configData.get("Users");
            if (users != null) {
                valid = (Map<String, Object>) users.get("Valid");
                invalid = (Map<String, Object>) users.get("Invalid");
            }
        }
    }
}
