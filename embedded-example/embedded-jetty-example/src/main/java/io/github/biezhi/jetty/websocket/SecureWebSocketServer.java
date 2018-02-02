package io.github.biezhi.jetty.websocket;

import io.github.biezhi.jetty.ServerConnectorHttps;
import org.eclipse.jetty.server.*;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.util.ssl.SslContextFactory;
import org.eclipse.jetty.websocket.api.WebSocketAdapter;
import org.eclipse.jetty.websocket.servlet.WebSocketServlet;
import org.eclipse.jetty.websocket.servlet.WebSocketServletFactory;

import java.io.IOException;
import java.net.URL;

/**
 * Create a Secure WebSocket Server and host an Echo WebSocket endpoint on "/echo"
 * <p>
 * Note: testing this can be tricky on modern browsers with "wss://localhost:8443/echo"
 * as they will reject either connecting to localhost, or reject any self-signed certificate.
 * </p>
 */
public class SecureWebSocketServer {
    public static class EchoSocketServlet extends WebSocketServlet {
        @Override
        public void configure(WebSocketServletFactory webSocketServletFactory) {
            webSocketServletFactory.register(EchoSocket.class);
        }
    }

    public static class EchoSocket extends WebSocketAdapter {
        @Override
        public void onWebSocketText(String message) {
            try {
                getRemote().sendString(message);
            } catch (IOException e) {
                e.printStackTrace(System.err);
            }
        }

        @Override
        public void onWebSocketError(Throwable cause) {
            cause.printStackTrace(System.err);
        }
    }

    public static void main(String[] args) throws IOException {
        Server server    = new Server();
        int    httpsPort = 8443;

        // Find Keystore
        ClassLoader cl               = ServerConnectorHttps.class.getClassLoader();
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
        HttpConfiguration httpsConf = new HttpConfiguration();
        httpsConf.setSecurePort(httpsPort);
        httpsConf.setSecureScheme("https");
        httpsConf.addCustomizer(new SecureRequestCustomizer()); // adds ssl info to request object

        // Establish the Secure ServerConnector
        ServerConnector httpsConnector = new ServerConnector(server,
                new SslConnectionFactory(sslContextFactory, "http/1.1"),
                new HttpConnectionFactory(httpsConf));
        httpsConnector.setPort(httpsPort);

        server.addConnector(httpsConnector);

        // Setup the basic application "context" for this application at "/"
        // This is also known as the handler tree (in jetty speak)
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath("/");
        server.setHandler(context);

        // Add a WebSocket as a Servlet to the context
        context.addServlet(EchoSocketServlet.class, "/echo");

        try {
            server.start();
            server.join();
        } catch (Throwable t) {
            t.printStackTrace(System.err);
        }
    }
}
