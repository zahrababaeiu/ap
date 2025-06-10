package ap.mid_project;

import java.io.*;
import java.util.Properties;

public class Config {
    private String storageType;

    public Config(String filePath) throws IOException {
        Properties props = new Properties();
        try (FileInputStream fis = new FileInputStream(filePath)) {
            props.load(fis);
        }
        storageType = props.getProperty("storage", "tabsplit");
    }

    public String getStorageType() {
        return storageType;
    }
}
