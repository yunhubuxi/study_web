package consumer;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.google.common.collect.Lists;
import com.yonyoucloud.manufacturing.ed.material.Material;
import com.yonyoucloud.manufacturing.service.material.IMaterialQueryService;
import com.yonyoucloud.uretail.api.IBillQueryService;
import com.yonyoucloud.uretail.dao.meta.MetaDaoHelper;
import com.yonyoucloud.uretail.util.json.GsonHelper;
import org.imeta.orm.base.BizObject;
import org.imeta.orm.schema.QueryCondition;
import org.imeta.orm.schema.QueryField;
import org.imeta.orm.schema.QuerySchema;
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
        // List<Long> matirialIdList = Lists.newArrayList();
        // matirialIdList.add(1451887218151680L);
        // matirialIdList.add(1450679890465024L);
        // matirialIdList.add(1450200634724608L);
        // Map<Long, List<Long>> longListMap = materialQueryService.querySKUId(matirialIdList);
        // for (Map.Entry<Long, List<Long>> longListEntry : longListMap.entrySet()) {
        //     System.out.println(longListEntry.getKey().toString() + ":" + longListEntry.getValue().toString());
        // }

        // List<Long> skuIdList = Lists.newArrayList();
        // skuIdList.add(1450658751860992L);
        // skuIdList.add(1450679720186114L);
        // skuIdList.add(1450200634724608L);
        // Map<Long, Long> longListMap = materialQueryService.queryMaterialIdBySKUId(skuIdList);
        // for (Map.Entry<Long, Long> longListEntry : longListMap.entrySet()) {
        //     System.out.println(longListEntry.getKey().toString() + ":" + longListEntry.getValue().toString());
        // }
        // QuerySchema querySchema = QuerySchema.create().addSelect("productId").addSelect(new QueryField("productId"))
        //     .appendQueryCondition(QueryCondition.name("id").eq(1460587085484288L));
        // QuerySchema materialAssociationCompositionSchema = QuerySchema.create().name("materialAssociation")
        //     .fullname("ed.material.MaterialAssociation").addSelect("productSKUId");
        // querySchema.addCompositionSchema(materialAssociationCompositionSchema);
        // String schema = JSONObject.toJSONString(querySchema);
        // JSONObject jsonObject = JSONObject.parseObject(schema);
        // String gson = GsonHelper.ToJSon(querySchema);
        // System.out.println(gson);
        // System.out.println(schema);
        // System.out.println(gson);
        // List<BizObject> queryResultList = billQueryService.query("ed.material.Material", querySchema.toString());
        // System.out.println(queryResultList);

        test(materialQueryService);
    }

    private static void test(IMaterialQueryService materialQueryService) throws Exception{
        Map<String, String> stringStringMap = materialQueryService.queryMaterialById(1453393419079936L);
        System.out.println(stringStringMap.toString());

        List<Map<String, String>> mapList = materialQueryService.queryMaterialByProductId(1453714209771776L);
        System.out.println(mapList.toString());

    }
}
