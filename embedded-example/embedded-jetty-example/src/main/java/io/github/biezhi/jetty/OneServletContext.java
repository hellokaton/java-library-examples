package io.github.biezhi.jetty;

import io.github.biezhi.jetty.servlets.DumpServlet;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.DefaultServlet;
import org.eclipse.jetty.servlet.ServletContextHandler;

public class OneServletContext {
    public static void main(String[] args) throws Exception {
        Server server = new Server(8080);

        ServletContextHandler context = new ServletContextHandler(
                ServletContextHandler.SESSIONS);
        context.setContextPath("/");
        context.setResourceBase(System.getProperty("java.io.tmpdir"));
        server.setHandler(context);

        // Add dump servlet
        context.addServlet(DumpServlet.class, "/dump/*");
        // Add default servlet
        context.addServlet(DefaultServlet.class, "/");

        server.start();
        server.join();
    }
}