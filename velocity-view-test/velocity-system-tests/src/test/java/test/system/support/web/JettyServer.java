package test.system.support.web;

import org.mortbay.jetty.Connector;
import org.mortbay.jetty.Server;
import org.mortbay.jetty.bio.SocketConnector;
import org.mortbay.jetty.webapp.WebAppContext;

public class JettyServer extends WebServer {

    private Server server;
    private final int serverPort;
    private final String contextPath;
    private final String warDirectory;

    public JettyServer(int serverPort, String contextPath, String warDirectory) {
        this.serverPort = serverPort;
        this.contextPath = contextPath;
        this.warDirectory = warDirectory;
        server = new Server();
    }

    public void start() throws Exception {
        deployWar();
        listen();
        server.start();
    }

    private void deployWar() {
        server.setHandler(new WebAppContext() {{
            setContextPath(contextPath);
            setWar(warDirectory);
        }});
    }

    private void listen() {
        server.setConnectors(new Connector[]{new SocketConnector() {{
            setPort(serverPort);
        }}});
    }

    public void stop() throws Exception {
        server.stop();
    }

}