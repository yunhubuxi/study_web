package consumer;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import com.yonyoucloud.uretail.api.IBillQueryService;
import com.yonyoucloud.uretail.bill.dto.BillDataDto;
import com.yonyoucloud.uretail.model.BillContext;
import com.yonyoucloud.uretail.ref.model.RefEntity;
import org.springframework.cglib.beans.BeanMap;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Map;

/**
 * 这个类主要用来测试IBillQueryService接口
 * 目前主要两个用途：getRefData和远程执行rule，非常好用
 * beanToMap方法是自己写的
 */
public class BillQueryServiceTest {
    public static void main(String[] args) throws Exception {

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"consumer.xml"});
        context.start();

        String jsonStr = "{\"action\":\"refer\",\"bMain\":true,\"billnum\":\"pc_product\",\"billtype\":\"Archive\",\"bpmInited\":false,\"cardKey\":\"pc_product\",\"context\":{},\"entityCode\":\"pc_product\",\"excludeRelated\":false,\"fullname\":\"pc.product.Product\",\"i18ndoc\":false,\"name\":\"物料档案\",\"rowAuthControled\":false,\"subid\":\"PC\",\"supportBpm\":false,\"transaction\":false}";
        BillContext billContext = JSONObject.parseObject(jsonStr, BillContext.class);

        String refEntityStr = "{\"bAuth\":false,\"bDataClass_RetEndData\":false,\"bDataClass_Rule\":false,\"bMultiSel\":false,\"bPage\":false,\"cBillnum\":\"mr_time_fence_ref\",\"cCheckFlds\":\"code,name\",\"cDataClass_FullName\":\"mr.timefence.TimeFence\",\"cDataClass_RefClsName\":\"\",\"cDataClass_sortField\":\"code\",\"cDataGrid_FullName\":\"mr.timefence.TimeFence\",\"cDataGrid_classFk\":\"id\",\"cEntityKeyFld\":\"code\",\"cEntityNameFld\":\"name\",\"cRefFilterSql\":\"0\",\"cRetFld\":\"code\",\"cSub_ID\":\"MR\",\"cTpltype\":\"Table\",\"code\":\"mr_time_fence_ref\",\"datasourceType\":\"meta\",\"description\":\"时界参照\",\"eventbean\":\"extendOrgsEvent\",\"extendField\":\"{\\\"placeholder\\\":\\\"编/名称\\\"}\",\"id\":20092,\"isOrgRel\":false,\"name\":\"时界参照\",\"refType\":\"mr_time_fence_ref\"}";
        RefEntity refEntity = JSONObject.parseObject(refEntityStr, RefEntity.class);

        String refpara = "{\"data\":[{\"code\":\"0023\",\"unitUseType\":1,\"_convert_detail\":\"ok\",\"realProductAttribute\":1,\"_status\":\"Unchanged\",\"realProductAttributeType\":1,\"orgId\":\"666666\",\"_realtype\":\"true\",\"_keyName\":\"id\",\"productspecitems\":[],\"_entityName\":\"pc.product.Product\",\"detail\":{\"_parent\":{\"$ref\":\"..\"},\"_realtype\":\"true\",\"_keyName\":\"id\",\"_entityName\":\"pc.product.ProductExtend\",\"businessAttribute\":\"\",\"_status\":\"Unchanged\",\"stopstatus\":false}}],\"dataType\":\"grid\",\"isDistinct\":false,\"condition\":{\"byDefault\":false,\"extend\":true,\"filtersId\":0,\"isDefault\":0,\"simpleVOs\":[{\"expand\":false,\"field\":\"orgId\",\"op\":\"in\",\"token\":0,\"value1\":[\"666666\",null]}],\"solutionId\":0},\"tenantId\":1549266965762304,\"page\":{\"pageIndex\":1,\"pageSize\":10,\"totalCount\":0},\"isSum\":false}";
        BillDataDto refMap = JSONObject.parseObject(refpara, BillDataDto.class);

        IBillQueryService billQueryService = context.getBean("billQueryService",IBillQueryService.class);

        Object object = billQueryService.getRefData(billContext, refEntity, beanToMap(refMap));
        System.out.println(JSONObject.toJSONString(object));
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
