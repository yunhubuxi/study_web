package consumer;

import api.DemoService;
import com.alibaba.dubbo.rpc.service.EchoService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 我们能从 Dubbo 源码中学到的这个骚操作是在创建动态代理对象的时候
 * 可以神不知鬼不觉的给代理对象加一个接口
 * 而且不需要真正的去实现接口里面的方法
 * 只需要拦截下来就行
 * EchoFilter 处理$echo
 * InvokerInvocationHandler处理$destory
 */
public class EchoServiceTest {
    public static void main(String[] args) throws Exception {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"consumer.xml"});
        context.start();
        // 获取远程服务代理
        DemoService demoService = (DemoService) context.getBean("demoService");
        String hello = demoService.sayHello("world");
        // 显示调用结果
        System.out.println(hello);
        EchoService echoService = (EchoService) demoService;
        System.err.println(echoService.$echo("hello world"));
    }

}
