package io.github.biezhi.jetty;

import io.github.biezhi.jetty.handlers.HelloHandler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.server.handler.ContextHandler;
import org.eclipse.jetty.server.handler.ContextHandlerCollection;

public class ConnectorSpecificContexts
{
    public static void main(String[] args) throws Exception
    {
        Server server = new Server();

        ServerConnector connectorA = new ServerConnector(server);
        connectorA.setPort(8080);
        connectorA.setName("connA");
        ServerConnector connectorB = new ServerConnector(server);
        connectorB.setPort(9090);
        connectorB.setName("connB");

        server.addConnector(connectorA);
        server.addConnector(connectorB);

        // Collection of Contexts
        ContextHandlerCollection contexts = new ContextHandlerCollection();
        server.setHandler(contexts);

        // Hello Handler (connection A)
        ContextHandler ctxHelloA = new ContextHandler();
        ctxHelloA.setContextPath("/");
        ctxHelloA.setHandler(new io.github.biezhi.jetty.handlers.HelloHandler("Hello Connection A"));
        ctxHelloA.setVirtualHosts(new String[] { "@connA" });
        contexts.addHandler(ctxHelloA);

        // Hello Handler (connection B)
        ContextHandler ctxHelloB = new ContextHandler();
        ctxHelloB.setContextPath("/");
        ctxHelloB.setHandler(new HelloHandler("Greetings from Connection B"));
        ctxHelloB.setVirtualHosts(new String[] { "@connB" });
        contexts.addHandler(ctxHelloB);

        server.start();
        server.join();
    }
}
