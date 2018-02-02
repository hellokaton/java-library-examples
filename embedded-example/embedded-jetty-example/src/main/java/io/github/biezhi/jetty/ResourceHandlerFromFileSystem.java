package io.github.biezhi.jetty;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.util.resource.PathResource;

import java.io.File;
import java.nio.file.Path;

public class ResourceHandlerFromFileSystem {
    public static void main(String[] args) throws Exception {
        Server server = new Server(8080);

        Path webRootPath = new File("webapps/static-root/").toPath().toRealPath();

        System.err.println("WebRoot is " + webRootPath);

        ResourceHandler handler = new ResourceHandler();
        handler.setBaseResource(new PathResource(webRootPath));
        handler.setDirectoriesListed(true);

        server.setHandler(handler);

        server.start();
        server.join();
    }
}
