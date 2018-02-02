package io.github.biezhi.jetty;

import org.eclipse.jetty.server.Server;

/**
 * 下面的代码从 SimplestServer.java 实例化和运行最简单可用的 Jetty 服务器:
 * 这个运行一个 HTTP 服务器在 8080 端口上.它不是一个有用的服务器,因为它没有 handlers,因此为每个请求返回一个 404 错误.
 * <p>
 * 1. 创建一个 Server 实例.
 * 2. 添加/配置 Connectors.
 * 3. 添加/配置 Handlers 和/或 Contexts 和/或 Servlets.
 * 4. 启动 Server.
 * 5. 在服务器上等待或者使用你的线程做一些其它的事情.
 */
public class SimplestServer {

    public static void main(String[] args) throws Exception {
        Server server = new Server(8080);
        server.start();
        server.dumpStdErr();
        server.join();
    }

}