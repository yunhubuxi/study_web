package consumer;

import com.yonyoucloud.manufacturing.service.material.IMaterialQueryService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SelfInterfaceTest {
    public static void main(String[] args) throws Exception {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"consumer.xml"});
        context.start();

        // 执行远程方法
        IMaterialQueryService materialQueryService = (IMaterialQueryService) context.getBean("materialQueryService");
        List<Long> matirialIdList = new ArrayList<>();
        matirialIdList.add(1451887218151680L);
        matirialIdList.add(1450679890465024L);
        matirialIdList.add(1450200634724608L);
        Map<Long, List<Long>> longListMap = materialQueryService.querySKUId(matirialIdList);
        for (Map.Entry<Long, List<Long>> longListEntry : longListMap.entrySet()) {
            System.out.println(longListEntry.getKey().toString() + ":" + longListEntry.getValue().toString());
        }

    }

}
