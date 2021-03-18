package consumer;

import api.DemoService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.concurrent.TimeUnit;

public class Consumer {
    public static void main(String[] args) throws Exception {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"consumer.xml"});
        context.start();
        // 获取远程服务代理
        DemoService demoService = (DemoService) context.getBean("aaa");
        for (int i = 0; i < 100; i++) {
            TimeUnit.MILLISECONDS.sleep(100);
            String hello = demoService.sayHello("world");
            // 显示调用结果
            System.out.println(hello);
        }


    }

}
