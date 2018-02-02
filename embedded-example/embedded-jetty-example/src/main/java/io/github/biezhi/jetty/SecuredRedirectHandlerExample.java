package io.github.biezhi.jetty;

import io.github.biezhi.jetty.handlers.HelloHandler;
import org.eclipse.jetty.server.*;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.SecuredRedirectHandler;
import org.eclipse.jetty.util.ssl.SslContextFactory;

import java.net.URL;

public class SecuredRedirectHandlerExample {
    public static void main(String[] args) throws Exception {
        Server server    = new Server();
        int    httpPort  = 8080;
        int    httpsPort = 8443;

        // Setup HTTP Connector
        HttpConfiguration httpConf = new HttpConfiguration();
        httpConf.setSecurePort(httpsPort);
        httpConf.setSecureScheme("https");

        // Establish the HTTP ServerConnector
        ServerConnector httpConnector = new ServerConnector(server,
                new HttpConnectionFactory(httpConf));
        httpConnector.setPort(httpPort);
        server.addConnector(httpConnector);

        // Find Keystore for SSL
        ClassLoader cl               = SecuredRedirectHandlerExample.class.getClassLoader();
        String      keystoreResource = "ssl/keystore";
        URL         f                = cl.getResource(keystoreResource);
        if (f == null) {
            throw new RuntimeException("Unable to find " + keystoreResource);
        }

        // Setup SSL
        SslContextFactory sslContextFactory = new SslContextFactory();
        sslContextFactory.setKeyStorePath(f.toExternalForm());
        sslContextFactory.setKeyStorePassword("OBF:1vny1zlo1x8e1vnw1vn61x8g1zlu1vn4");
        sslContextFactory.setKeyManagerPassword("OBF:1u2u1wml1z7s1z7a1wnl1u2g");

        // Setup HTTPS Configuration
        HttpConfiguration httpsConf = new HttpConfiguration(httpConf);
        httpsConf.addCustomizer(new SecureRequestCustomizer()); // adds ssl info to request object

        // Establish the HTTPS ServerConnector
        ServerConnector httpsConnector = new ServerConnector(server,
                new SslConnectionFactory(sslContextFactory, "http/1.1"),
                new HttpConnectionFactory(httpsConf));
        httpsConnector.setPort(httpsPort);

        server.addConnector(httpsConnector);

        // Add a Handlers for requests
        HandlerList handlers = new HandlerList();
        handlers.addHandler(new SecuredRedirectHandler());
        handlers.addHandler(new HelloHandler("Hello Secure World"));
        server.setHandler(handlers);

        server.start();
        server.join();
    }
}
