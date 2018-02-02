package io.github.biezhi.jetty;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.server.handler.HandlerCollection;
import org.eclipse.jetty.webapp.WebAppContext;

public class ConnectorSpecificWebapps {
    public static void main(String[] args) throws Exception {
        Server server = new Server();

        ServerConnector connectorA = new ServerConnector(server);
        connectorA.setPort(8080);
        connectorA.setName("connA");
        ServerConnector connectorB = new ServerConnector(server);
        connectorB.setPort(9090);
        connectorB.setName("connB");

        server.addConnector(connectorA);
        server.addConnector(connectorB);

        // Basic handler collection
        HandlerCollection contexts = new HandlerCollection();
        server.setHandler(contexts);

        // WebApp A
        WebAppContext appA = new WebAppContext();
        appA.setContextPath("/a");
        appA.setWar("./src/main/wars/webapp-a.war");
        appA.setVirtualHosts(new String[]{"@connA"});
        contexts.addHandler(appA);

        // WebApp B
        WebAppContext appB = new WebAppContext();
        appB.setContextPath("/b");
        appB.setWar("./src/main/wars/webapp-b.war");
        appB.setVirtualHosts(new String[]{"@connB"});
        contexts.addHandler(appB);

        server.start();
        server.join();
    }
}
