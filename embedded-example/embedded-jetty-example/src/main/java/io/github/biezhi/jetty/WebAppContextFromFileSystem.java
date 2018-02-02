package io.github.biezhi.jetty;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;

import java.io.File;
import java.nio.file.Path;

public class WebAppContextFromFileSystem {
    public static void main(String[] args) throws Exception {
        Server server = new Server(8080);

        Path warPath = new File("webapps/hello.war").toPath().toRealPath();
        System.err.println("WAR File is " + warPath);

        WebAppContext webapp = new WebAppContext();
        webapp.setContextPath("/");
        webapp.setWar(warPath.toUri().toASCIIString());
        webapp.setParentLoaderPriority(true);

        server.setHandler(webapp);

        server.start();
        server.join();
    }
}
