package consumer;

import api.DemoService;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.ParserConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.gson.JsonObject;
import com.yonyoucloud.manufacturing.ed.material.Material;
import com.yonyoucloud.manufacturing.service.material.IMaterialQueryService;
import com.yonyoucloud.uretail.api.IBillQueryService;
import com.yonyoucloud.uretail.bill.dto.BaseDto;
import com.yonyoucloud.uretail.bill.dto.BillDataDto;
import com.yonyoucloud.uretail.dao.Pager;
import com.yonyoucloud.uretail.dao.meta.MetaDaoHelper;
import com.yonyoucloud.uretail.model.BillContext;
import com.yonyoucloud.uretail.ref.model.RefEntity;
import com.yonyoucloud.uretail.util.json.GsonHelper;
import net.sf.json.JsonConfig;
import org.imeta.orm.base.BizObject;
import org.imeta.orm.schema.QueryCondition;
import org.imeta.orm.schema.QueryField;
import org.imeta.orm.schema.QueryPager;
import org.imeta.orm.schema.QuerySchema;
import org.springframework.cglib.beans.BeanMap;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Consumer {
    public static void main(String[] args) throws Exception {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"consumer.xml"});
        context.start();
        // 获取远程服务代理
        DemoService demoService = (DemoService) context.getBean("demoService");
        // 执行远程方法
        Map map = new HashMap();
        map.put("111", "bbb");
        map.put("222", "bbb");
        Map ddd = demoService.sayHello(map);
        String hello = demoService.sayHello("world");
        // 显示调用结果
        System.out.println(hello);
        test(materialQueryService);
    }

    private static void test(IMaterialQueryService materialQueryService) throws Exception{
        Map<String, String> stringStringMap = materialQueryService.queryMaterialById(1453393419079936L);
        System.out.println(stringStringMap.toString());

        List<Map<String, String>> mapList = materialQueryService.queryMaterialByProductId(1453714209771776L);
        System.out.println(mapList.toString());
    }
}
