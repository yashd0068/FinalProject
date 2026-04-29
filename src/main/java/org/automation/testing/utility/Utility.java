package org.automation.testing.utility;

import java.io.FileInputStream;
import java.util.Properties;

public class Utility {

    private static Properties prop;

    static {
        try {
            FileInputStream fis =

                    new FileInputStream("src/test/resources/testData/config.properties");
            prop = new Properties();
            prop.load(fis);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getProperty(String key) {
        return prop.getProperty(key);
    }
}
