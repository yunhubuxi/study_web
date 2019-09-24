package consumer;

import com.yonyoucloud.manufacturing.service.api.IquerySKUId;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Map;

public class Consumer {
    public static void main(String[] args) throws Exception {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"consumer.xml"});
        context.start();
        // DemoService demoService = (DemoService)context.getBean("demoService"); // 获取远程服务代理
        // String hello = demoService.sayHello("world"); // 执行远程方法
        // System.out.println( hello ); // 显示调用结果
        // IBillQueryService billQueryService = (IBillQueryService)context.getBean("billQueryService"); // 获取远程服务代理
        // billQueryService.register(new HashMap<>()); // 执行远程方法
        IquerySKUId iquerySKUId = (IquerySKUId) context.getBean("iquerySKUId");
        Map query = iquerySKUId.query("1401022402089216,1401021291401472,12312,1401021300379904");
        System.out.println(query);
    }
}
