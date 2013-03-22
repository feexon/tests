package test.system.support.velocity;

import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

/**
 * @author Administrator
 * @version 1.0 13-3-21,下午6:29
 */
public abstract class WebServer implements TestRule {
    public abstract void start() throws Exception;

    public abstract void stop() throws Exception;

    public Statement apply(final Statement statement, final Description description) {
        return new ServerLifeCycleManagement(statement);
    }

    private class ServerLifeCycleManagement extends Statement {
        private final Statement statement;

        public ServerLifeCycleManagement(Statement statement) {
            this.statement = statement;
        }

        public void evaluate() throws Throwable {
            start();
            try {
                statement.evaluate();
            } finally {
                stop();
            }
        }
    }
}
