package io.github.biezhi.jetty;

import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.AbstractHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 为了为一个请求产生响应,Jetty 需要你在 server 上设置一个 Handler.一个 handler 可以是:
 * <p>
 * - 检查/修改 HTTP 请求.
 * - 生成和完成 HTTP 响应.
 * - 调用另一个 Handler (见 HandlerWrapper).
 * - 选择一个或许多 Handlers 去调用(见 HandlerCollection).
 * <p>
 * 下面的代码基于 HelloHandler.java 展示了一个简单的 hello world handler:
 */
public class HelloHandler extends AbstractHandler {

    final String greeting;
    final String body;

    public HelloHandler() {
        this("Hello World");
    }

    public HelloHandler(String greeting) {
        this(greeting, null);
    }

    public HelloHandler(String greeting, String body) {
        this.greeting = greeting;
        this.body = body;
    }

    @Override
    public void handle(String target,
                       Request baseRequest,
                       HttpServletRequest request,
                       HttpServletResponse response) throws IOException {

        response.setContentType("text/html; charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);

        PrintWriter out = response.getWriter();

        out.println("<h1>" + greeting + "</h1>");
        if (body != null) {
            out.println(body);
        }

        baseRequest.setHandled(true);
    }

    public static void main(String[] args) throws Exception {
        /**
         * 传递到 handle 方法中的参数是:
         *
         * target–请求的目标,是一个 URI 或一个来自命名的 dispatcher 的名称.
         * baseRequest–Jetty 可变的请求对象,总是未包装.
         * request–不可变的请求对象,可以已经被一个 filter 或 servlet 包装过.
         * response–响应,可以已经被一个 filter 或 servlet 包装过.
         * handler 设置相应状态,content-type,和标记请求已经被处理过了,在它使用 writer 生成响应的 body 之前.
         */
        Server server = new Server(8080);
        server.setHandler(new HelloHandler());

        server.start();
        server.join();
    }
}