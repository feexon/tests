package test.system.support.web;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

public class TestEnvironment {
    public static final String PROPERTY_WEBAPP_DIRECTORY = "test.webapp.directory";
    public static final String PROPERTY_CONTEXT_PATH = "test.context.path";
    public static final String PROPERTY_SERVER_PORT = "test.server.port";
    public static final String PROPERTY_SERVER_HOST = "test.server.host";
    public static final TestEnvironment DEFAULT_EVN = new TestEnvironment("test.properties");
    private Properties properties;


    private TestEnvironment(String url) {
        this.properties = PropertyFile.loadProperties(url);
    }

    public static TestEnvironment in() {
        return DEFAULT_EVN;
    }


    public WebServer makeWebServer() {
        return new JettyServer(serverPort(), contextPath(), getString(PROPERTY_WEBAPP_DIRECTORY));
    }

    public int getInt(String key) {
        return Integer.parseInt(getString(key));
    }

    public String getString(String key) {
        return properties.getProperty(key);
    }

    public URL routeTo(String path) throws MalformedURLException {
        return new URL(webRoot(), path);
    }

    private URL webRoot() throws MalformedURLException {
        return new URL("http", serverHost(), serverPort(), contextPath());
    }

    private String serverHost() {
        return getString(PROPERTY_SERVER_HOST);
    }

    private int serverPort() {
        return getInt(PROPERTY_SERVER_PORT);
    }

    private String contextPath() {
        return getString(PROPERTY_CONTEXT_PATH);
    }
}