package consumer;

import api.DemoService;
import com.alibaba.dubbo.rpc.Result;
import com.alibaba.dubbo.rpc.RpcContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class Consumer {
    public static void main(String[] args) throws Exception {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"consumer.xml"});
        context.start();
        // 获取远程服务代理
        DemoService demoService = (DemoService) context.getBean("demoService");
        sync(demoService);
        async(demoService);
    }

    static void sync(DemoService demoService) {
        String hello = demoService.sayHello("world");
        // 显示调用结果
        System.out.println(hello);
    }

    static void async(DemoService demoService) throws InterruptedException, ExecutionException, TimeoutException {
        demoService.sayHello("world");
        Object result = RpcContext.getContext().getFuture().get(5, TimeUnit.SECONDS);
        System.out.println(result);
    }

}
