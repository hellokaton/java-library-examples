package io.github.biezhi.jetty;

import io.github.biezhi.jetty.servlets.TimeServlet;
import io.github.biezhi.jetty.websocket.jsr.TimeSocket;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.server.handler.gzip.GzipHandler;
import org.eclipse.jetty.servlet.DefaultServlet;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.util.resource.PathResource;
import org.eclipse.jetty.websocket.jsr356.server.deploy.WebSocketServerContainerInitializer;

import javax.websocket.server.ServerContainer;
import java.io.File;
import java.nio.file.Path;

public class GzipResponsesExample
{
    public static void main(String[] args) throws Exception
    {
        Server server = new Server();
        ServerConnector connector = new ServerConnector(server);
        connector.setPort(8080);
        server.addConnector(connector);

        GzipHandler gzip = new GzipHandler();
        gzip.setIncludedMethods("GET","POST");
        gzip.setMinGzipSize(245);
        gzip.setIncludedMimeTypes("text/plain","text/css","text/html",
                "application/javascript");
        server.setHandler(gzip);

        Path webRootPath = new File("webapps/static-root/").toPath().toRealPath();

        ServletContextHandler context = new ServletContextHandler();
        gzip.setHandler(context);
        context.setContextPath("/");
        context.setBaseResource(new PathResource(webRootPath));
        context.setWelcomeFiles(new String[] { "index.html" });
        
        // Adding WebSockets
        ServerContainer wsContainer = WebSocketServerContainerInitializer.configureContext(context);
        wsContainer.addEndpoint(TimeSocket.class);

        // Adding Servlets
        context.addServlet(TimeServlet.class,"/time/");
        context.addServlet(DefaultServlet.class,"/"); // always last, always on "/"

        server.start();
        server.join();
    }
}
