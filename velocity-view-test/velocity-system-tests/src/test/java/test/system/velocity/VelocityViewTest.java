package test.system.velocity;

import org.junit.Rule;
import org.junit.Test;
import test.system.support.web.WebServer;
import test.system.support.velocity.Browser;
import test.system.support.web.TestEnvironment;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

/**
 * @author Administrator
 * @version 1.0 13-3-21,下午4:42
 */
public class VelocityViewTest {

    private TestEnvironment env = TestEnvironment.in();
    @Rule
    public WebServer webServer = env.makeWebServer();

    @Test
    public void usingParamValue() throws Exception {
        assertThat(Browser.open(env.routeTo("/echo.vm?version=1.4")).viewSource(), equalTo("Velocity 1.4"));
    }

    @Test
    public void visitVelocityViewWithoutParam() throws Exception {
        assertThat(Browser.open(env.routeTo("/echo.vm")).viewSource(), equalTo("Velocity $params.version"));
    }

    @Test
    public void undefinedVariable() throws Exception {
        assertThat(Browser.open(env.routeTo("/undefined.vm")).viewSource(), equalTo("undefined:$unknown\r\nshort-circuit:"));
    }

}
