package http;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;


/**
 * 对外http服务
 */
public class HTTPService {

    public HTTPService() {
    }

    /**
     * 初始化HTTP服务
     */
    public void initServer(int port) {
        try {
            Server server = new Server(port);
            System.out.println("监听http端口" + port);
            //Servlet上下文
            ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
            context.setContextPath("/");
            server.setHandler(context);
            // 查询
            context.addServlet(new ServletHolder(new HelloServlet()), "/hello");
            // 提交
            context.addServlet(new ServletHolder(new PostServlet()), "/post");

            server.start();
            server.join();
        } catch (Exception e) {
            System.out.println("init http server is error:" + e.getMessage());
        }
    }

    public static class HelloServlet extends HttpServlet {
        @Override
        protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            resp.setCharacterEncoding("UTF-8");
            resp.getWriter().print("Hello,World!");
        }
    }

    public class PostServlet extends HttpServlet {
        @Override
        protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            resp.setCharacterEncoding("UTF-8");
            String name = req.getParameter("name");
            System.out.println("接收到的参数：" + name);
            resp.getWriter().print("接收到的参数：" + name);
        }
    }
}
