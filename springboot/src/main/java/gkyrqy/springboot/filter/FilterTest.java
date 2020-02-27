package gkyrqy.springboot.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.PrintWriter;

@WebFilter(urlPatterns = "/*", filterName = "filterTest")
public class FilterTest implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        System.out.println("================进入过滤器======================");
        if (httpRequest.getRequestURI().contains("/filter/")) {
            PrintWriter writer = null;
            try {
                writer =  servletResponse.getWriter();
                writer.write("fsdlkfjsdl;kfjsd;lkfj;");
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                if (writer != null) {
                    writer.flush();
                    writer.close();
                }
            }

        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {

    }
}
