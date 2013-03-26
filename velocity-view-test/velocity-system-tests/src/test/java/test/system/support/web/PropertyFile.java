package test.system.support.web;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author Administrator
 * @version 1.0 13-3-21,下午6:10
 */
public class PropertyFile {
    public static Properties loadProperties(String url) {
        checkFileExists(url);
        return loadFrom(url);
    }

    private static void checkFileExists(String url) {
        if (ClassLoader.getSystemResource(url) == null)
            throw new IllegalArgumentException("property file not found:" + url);
    }

    private static Properties loadFrom(String url) {
        Properties properties = new Properties();
        try {
            properties.load(ClassLoader.getSystemResourceAsStream(url));
            return properties;
        } catch (IOException cause) {
            throw new RuntimeException(cause);
        }
    }
}
