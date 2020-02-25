package example;

import org.eclipse.jetty.server.*;
import org.eclipse.jetty.server.handler.DefaultHandler;
import org.eclipse.jetty.server.handler.HandlerCollection;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.util.thread.QueuedThreadPool;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class ExampleServer {
    public static Server createServer(int port) {
        // QueuedThreadPool暂时不知道有啥用，反正必须大于acceptors + selectors + 1
        // Server 配置或者不配置 QueuedThreadPool，都是阻塞调用 Servlet
        // 4 核处理器，最大设置8，设置超过8了也没有
        QueuedThreadPool workers = new QueuedThreadPool(20);
        Server server = new Server(workers);
        // Server server = new Server();
        // 如果不设置QueuedThreadPool，Server会初始化一个，MaxThreads默认是200

        // 加上Executor之后，就请求不到Servlet，真不知道是什么鬼，奇奇怪怪的


        // 一定要把QueuedThreadPool、Executor、acceptors、selectors、Servlet都搞懂
        // executors供connector用，先启动selectors,再启动acceptors
        // 默认是不需要指定的，指定的话，就不去验证server的workers了，像是个bug
        Executor executors = Executors.newFixedThreadPool(6);

        // 如果不设置executors，ServerConnector会使用Server中的线程池
        // 如果自己设置了，那就是自己设置的workers，如果没有设置，那就是默认的200个的workers
        ServerConnector connector = new ServerConnector(server, executors, null, null, 2, 4, new HttpConnectionFactory());
        // ServerConnector connector = new ServerConnector(server, 5, 5);
        connector.setPort(port);
        server.setConnectors(new Connector[]{connector});

        ServletContextHandler context = new ServletContextHandler();
        context.setContextPath("/");
        // context.addServlet(HelloServlet.class, "/hello");
        // context.addServlet(AsyncEchoServlet.class, "/echo/*");

        HandlerCollection handlers = new HandlerCollection();
        handlers.setHandlers(new Handler[]{context, new DefaultHandler()});
        server.setHandler(handlers);


        return server;
    }

    public static void main(String[] args) throws Exception {
        // int port = ExampleUtil.getPort(args, "jetty.http.port", 8081);
        int port = 8080;
        Server server = createServer(port);
        server.start();
        server.join();
    }
}
