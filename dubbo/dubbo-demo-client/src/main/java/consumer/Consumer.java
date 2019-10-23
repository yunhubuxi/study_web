package consumer;

import com.yonyoucloud.manufacturing.service.material.IMaterialQueryService;
import org.apache.curator.shaded.com.google.common.collect.Lists;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;
import java.util.Map;

public class Consumer {
    public static void main(String[] args) throws Exception {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"consumer.xml"});
        context.start();
        // 获取远程服务代理
        // DemoService demoService = (DemoService)context.getBean("demoService");
        // 执行远程方法
        // String hello = demoService.sayHello("world");
        // 显示调用结果
        // System.out.println(hello);
        // 获取远程服务代理
        // IBillQueryService billQueryService = (IBillQueryService)context.getBean("billQueryService");
        // 执行远程方法
        // billQueryService.register(new HashMap<>());
        IMaterialQueryService materialQueryService = (IMaterialQueryService) context.getBean("materialQueryService");
        List<Long> matirialIdList = Lists.newArrayList();
        // matirialIdList.add(1451887218151680L);
        // matirialIdList.add(1450679890465024L);
        // matirialIdList.add(1450200634724608L);
        Map<Long, List<Long>> longListMap = materialQueryService.querySKUId(matirialIdList);
        for (Map.Entry<Long, List<Long>> longListEntry : longListMap.entrySet()) {
            System.out.println(longListEntry.getKey().toString() + ":" + longListEntry.getValue().toString());
        }

    }
}
