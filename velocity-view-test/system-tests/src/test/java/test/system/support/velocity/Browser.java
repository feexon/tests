package test.system.support.velocity;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

/**
* @author Administrator
* @version 1.0 13-3-21,下午5:41
*/
public class Browser {

    private URL location;

    public Browser(URL location) throws MalformedURLException {
        this.location = location;
    }

    public static Browser open(URL location) throws MalformedURLException {
        return new Browser(location);
    }

    public String viewSource() throws IOException {
        return IOUtils.toString(location);
    }
}
