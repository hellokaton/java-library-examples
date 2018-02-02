//
//  ========================================================================
//  Copyright (c) 1995-2015 Mort Bay Consulting Pty. Ltd.
//  ------------------------------------------------------------------------
//  All rights reserved. This program and the accompanying materials
//  are made available under the terms of the Eclipse Public License v1.0
//  and Apache License v2.0 which accompanies this distribution.
//
//      The Eclipse Public License is available at
//      http://www.eclipse.org/legal/epl-v10.html
//
//      The Apache License v2.0 is available at
//      http://www.opensource.org/licenses/apache2.0.php
//
//  You may elect to redistribute this code under either of these licenses.
//  ========================================================================
//

package io.github.biezhi.jetty.websocket.jsr;

import io.github.biezhi.jetty.ServerConnectorHttps;
import org.eclipse.jetty.server.*;
import org.eclipse.jetty.servlet.DefaultServlet;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.util.log.Log;
import org.eclipse.jetty.util.log.Logger;
import org.eclipse.jetty.util.ssl.SslContextFactory;
import org.eclipse.jetty.websocket.jsr356.server.ServerContainer;
import org.eclipse.jetty.websocket.jsr356.server.deploy.WebSocketServerContainerInitializer;

import javax.servlet.ServletException;
import javax.websocket.DeploymentException;
import java.net.URL;

/**
 * Tool to setup a WebSocket server with some static html/javascript for browsers
 */
public class JsrBrowserMain {
    private static final Logger LOG = Log.getLogger(JsrBrowserMain.class);

    public static void main(String[] args) {
        int port    = 8080;
        int sslPort = 8443;

        for (int i = 0; i < args.length; i++) {
            String a = args[i];
            if ("-p".equals(a) || "--port".equals(a)) {
                port = Integer.parseInt(args[++i]);
            }
            if ("-ssl".equals(a)) {
                sslPort = Integer.parseInt(args[++i]);
            }
        }

        try {
            JsrBrowserMain tool = new JsrBrowserMain();
            tool.setupServer(port, sslPort);
            tool.runForever();
        } catch (Throwable t) {
            LOG.warn(t);
        }
    }

    private Server server;

    private void runForever() throws Exception {
        server.start();
        server.dumpStdErr();
        server.join();
    }

    private void setupServer(int port, int sslPort) throws DeploymentException, ServletException {
        server = new Server();
        ServerConnector connector = new ServerConnector(server);
        connector.setPort(port);
        server.addConnector(connector);

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
        httpsConf.setSecurePort(sslPort);
        httpsConf.setSecureScheme("https");
        httpsConf.addCustomizer(new SecureRequestCustomizer()); // adds ssl info to request object

        // Establish the ServerConnector
        ServerConnector httpsConnector = new ServerConnector(server,
                new SslConnectionFactory(sslContextFactory, "http/1.1"),
                new HttpConnectionFactory(httpsConf));
        httpsConnector.setPort(sslPort);

        server.addConnector(httpsConnector);

        // Setup ServletContext
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath("/");
        ServletHolder holder = context.addServlet(DefaultServlet.class, "/");

        // TODO: figure out resource base better

        holder.setInitParameter("resourceBase", "src/main/resources/websocket-statics");
        holder.setInitParameter("dirAllowed", "true");
        server.setHandler(context);

        ServerContainer container = WebSocketServerContainerInitializer.configureContext(context);
        container.addEndpoint(JsrBrowserSocket.class);

        LOG.info("{} setup on (http) port {} and (https) port {}", this.getClass().getName(), port, sslPort);
    }
}
