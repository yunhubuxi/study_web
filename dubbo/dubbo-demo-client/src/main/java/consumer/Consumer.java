package consumer;

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
        // DemoService demoService = (DemoService)context.getBean("demoService");
        // 执行远程方法
        // String hello = demoService.sayHello("world");
        // 显示调用结果
        // System.out.println(hello);
        // 获取远程服务代理
        String jsonStr = "{\"action\":\"refer\",\"bMain\":true,\"billnum\":\"pc_product\",\"billtype\":\"Archive\",\"bpmInited\":false,\"cardKey\":\"pc_product\",\"context\":{},\"entityCode\":\"pc_product\",\"excludeRelated\":false,\"fullname\":\"pc.product.Product\",\"i18ndoc\":false,\"name\":\"物料档案\",\"rowAuthControled\":false,\"subid\":\"PC\",\"supportBpm\":false,\"transaction\":false}";
        BillContext billContext = JSONObject.parseObject(jsonStr, BillContext.class);

        String refEntityStr = "{\"bAuth\":false,\"bDataClass_RetEndData\":false,\"bDataClass_Rule\":false,\"bMultiSel\":false,\"bPage\":false,\"cBillnum\":\"mr_time_fence_ref\",\"cCheckFlds\":\"code,name\",\"cDataClass_FullName\":\"mr.timefence.TimeFence\",\"cDataClass_RefClsName\":\"\",\"cDataClass_sortField\":\"code\",\"cDataGrid_FullName\":\"mr.timefence.TimeFence\",\"cDataGrid_classFk\":\"id\",\"cEntityKeyFld\":\"code\",\"cEntityNameFld\":\"name\",\"cRefFilterSql\":\"0\",\"cRetFld\":\"code\",\"cSub_ID\":\"MR\",\"cTpltype\":\"Table\",\"code\":\"mr_time_fence_ref\",\"datasourceType\":\"meta\",\"description\":\"时界参照\",\"eventbean\":\"extendOrgsEvent\",\"extendField\":\"{\\\"placeholder\\\":\\\"编码/名称\\\"}\",\"id\":20138,\"isOrgRel\":false,\"name\":\"时界参照\",\"refType\":\"mr_time_fence_ref\"}";
        RefEntity refEntity = JSONObject.parseObject(refEntityStr, RefEntity.class);

        String refpara = "{\"data\":[{\"code\":\"00000043\",\"unitUseType\":1,\"_convert_detail\":\"ok\",\"realProductAttribute\":1,\"_status\":\"Unchanged\",\"realProductAttributeType\":1,\"orgId\":\"1502600168116480\",\"_realtype\":\"true\",\"_keyName\":\"id\",\"productspecitems\":[],\"_entityName\":\"pc.product.Product\",\"detail\":{\"_parent\":{\"$ref\":\"..\"},\"_realtype\":\"true\",\"_keyName\":\"id\",\"_entityName\":\"pc.product.ProductExtend\",\"businessAttribute\":\"\",\"_status\":\"Unchanged\",\"stopstatus\":false}}],\"dataType\":\"grid\",\"isDistinct\":false,\"tenantId\":1389339883229440,\"page\":{\"pageIndex\":1,\"pageSize\":10,\"totalCount\":0},\"isSum\":false}";
        BillDataDto refMap = JSONObject.parseObject(refpara, BillDataDto.class);
        Object test = JSONObject.parse("{\"data\":[{\"code\":\"00000043\",\"unitUseType\":1,\"_convert_detail\":\"ok\",\"realProductAttribute\":1,\"_status\":\"Unchanged\",\"realProductAttributeType\":1,\"orgId\":\"1502600168116480\",\"_realtype\":\"true\",\"_keyName\":\"id\",\"productspecitems\":[],\"_entityName\":\"pc.product.Product\",\"detail\":{\"_parent\":{\"$ref\":\"..\"},\"_realtype\":\"true\",\"_keyName\":\"id\",\"_entityName\":\"pc.product.ProductExtend\",\"businessAttribute\":\"\",\"_status\":\"Unchanged\",\"stopstatus\":false}}],\"dataType\":\"grid\",\"isDistinct\":false,\"tenantId\":1389339883229440,\"page\":{\"pageIndex\":1,\"pageSize\":10,\"totalCount\":0},\"isSum\":false}");

        IBillQueryService billQueryService = (IBillQueryService)context.getBean("billQueryService");

        Object object = billQueryService.getRefData(billContext, refEntity, beanToMap(refMap));
        System.out.println(JSONObject.toJSONString(object));
        // 执行远程方法
        // billQueryService.register(new HashMap<>());
        // IMaterialQueryService materialQueryService = (IMaterialQueryService) context.getBean("materialQueryService");
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

        // testForGongYingLian(materialQueryService);
    }

    private static void testForGongYingLian(IMaterialQueryService materialQueryService) throws Exception{
        Map<String, String> stringStringMap = materialQueryService.queryMaterialById(1453393419079936L);
        System.out.println(stringStringMap.toString());

        List<Map<String, String>> mapList = materialQueryService.queryMaterialByProductId(1453714209771776L);
        System.out.println(mapList.toString());

    }

    public static <T> Map<String, Object> beanToMap(T bean) {
        Map<String, Object> map = Maps.newHashMap();
        if (bean != null) {
            BeanMap beanMap = BeanMap.create(bean);
            for (Object key : beanMap.keySet()) {
                map.put(key + "", beanMap.get(key));
            }
        }
        return map;
    }
}
