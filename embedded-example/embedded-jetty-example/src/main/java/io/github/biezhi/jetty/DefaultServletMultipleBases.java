package io.github.biezhi.jetty;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.servlet.DefaultServlet;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.util.resource.Resource;

import java.io.File;
import java.net.URI;
import java.net.URL;
import java.nio.file.Path;

public class DefaultServletMultipleBases {
    public static void main(String[] args) throws Exception {
        Server          server    = new Server();
        ServerConnector connector = new ServerConnector(server);
        connector.setPort(8080);
        server.addConnector(connector);

        // Figure out what path to serve content from
        ClassLoader cl = DefaultServletMultipleBases.class.getClassLoader();
        // We look for a file, as ClassLoader.getResource() is not
        // designed to look for directories (we resolve the directory later)
        URL f = cl.getResource("static-root/hello.html");
        if (f == null) {
            throw new RuntimeException("Unable to find resource directory");
        }

        // Resolve file to directory
        URI webRootUri = f.toURI().resolve("./").normalize();
        System.err.println("Main Base Resource is " + webRootUri);

        // Setup the basic application "context" for this application at "/"
        // This is also known as the handler tree (in jetty speak)
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath("/");
        context.setBaseResource(Resource.newResource(webRootUri));
        context.setWelcomeFiles(new String[]{"index.html", "index.htm", "alt-index.html"});
        server.setHandler(context);

        // Find altPath
        Path altPath = new File("webapps/alt-root").toPath().toRealPath();
        System.err.println("Alt Base Resource is " + altPath);

        // add special pathspec of "/alt/" content mapped to the altPath
        ServletHolder holderAlt = new ServletHolder("static-alt", DefaultServlet.class);
        holderAlt.setInitParameter("resourceBase", altPath.toUri().toASCIIString());
        holderAlt.setInitParameter("dirAllowed", "true");
        holderAlt.setInitParameter("pathInfoOnly", "true");
        context.addServlet(holderAlt, "/alt/*");

        // Lastly, the default servlet for root content (always needed, to satisfy servlet spec)
        // It is important that this is last.
        ServletHolder holderDef = new ServletHolder("default", DefaultServlet.class);
        holderDef.setInitParameter("dirAllowed", "true");
        context.addServlet(holderDef, "/");

        server.start();
        server.join();
    }
}
