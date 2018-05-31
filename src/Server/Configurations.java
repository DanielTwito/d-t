package Server;

import java.io.IOException;
import java.util.Properties;

public class Configurations {
    private static Configurations instance = new Configurations();
    private Properties configFile;

    public static Configurations getInstance() {
        return instance;
    }

    private Configurations() {
        configFile = new Properties();

        try {
            configFile.load(this.getClass().getClassLoader().getResourceAsStream("config.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static String getProperty(String key){
        return instance.getValue(key);
    }

    private String getValue(String key) {
        return configFile.getProperty(key);
    }
}
