package io.github.biezhi.jetty;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.servlet.DefaultServlet;
import org.eclipse.jetty.servlet.FilterHolder;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.util.resource.Resource;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.util.EnumSet;

public class AddFilterMultipleMapping {
    public static class DemoFilter implements Filter {
        @Override
        public void init(FilterConfig filterConfig) {
        }

        @Override
        public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
            if (response instanceof HttpServletResponse) {
                ((HttpServletResponse) response).addHeader("X-Demo", "was-filtered");
            }
            chain.doFilter(request, response);
        }

        @Override
        public void destroy() {
        }
    }

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
        server.setHandler(context);

        EnumSet<DispatcherType> dispatches = EnumSet.allOf(DispatcherType.class);
        FilterHolder            holder     = new FilterHolder(DemoFilter.class);
        holder.setName("demo");
        context.addFilter(holder, "/demo/*", dispatches);
        context.addFilter(holder, "*.demo", dispatches);

        // Lastly, the default servlet for root content (always needed, to satisfy servlet spec)
        // It is important that this is last.
        ServletHolder holderDef = new ServletHolder("default", DefaultServlet.class);
        holderDef.setInitParameter("dirAllowed", "true");
        context.addServlet(holderDef, "/");

        server.start();
        server.join();
    }
}
