package io.github.biezhi.jetty;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.ContextHandler;

/**
 * A ContextHandler 是一个 ScopedHandler,只有当请求有一个 URI 前缀匹配配置的 context path 才产生响应.匹配上下文路径的请求有自己的路径方法相应地更新,并且 contexts scope 是可用的,选项可能包括:
 *
 * - Classloader 被设置为 Thread context classloader 当请求处理在范围中的时候.
 * - 一组属性通过 ServletContext API 可用.
 * - 一组初始参数通过 ServletContext API 可用.
 * - 一个基本的 Resource,作为文档根目录用于静态资源请求,通过 ServletContext API.
 * - 一组虚拟主机名称.
 *
 */
public class OneContext {
    public static void main(String[] args) throws Exception {
        Server server = new Server(8080);

        // Add a single handler on context "/hello"
        ContextHandler context = new ContextHandler();
        context.setContextPath("/hello");
        context.setHandler(new HelloHandler());

        // Can be accessed using http://localhost:8080/hello

        server.setHandler(context);

        // Start the server
        server.start();
        server.join();
    }
}