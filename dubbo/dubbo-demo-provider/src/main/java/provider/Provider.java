package provider;

import com.alibaba.dubbo.common.URL;
import com.alibaba.dubbo.config.ServiceConfig;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class Provider {
    public static void main(String[] args) throws Exception {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"provider.xml"});
        context.start();
        ServiceConfig serviceConfig = context.getBean(ServiceConfig.class);
        List<URL> list = serviceConfig.getExportedUrls();
        for (URL url : list) {
            System.out.println(url);
        }
        System.in.read(); // 按任意键退出
    }
}
