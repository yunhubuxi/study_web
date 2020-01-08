package consumer;

import com.yonyoucloud.uretail.api.IBillQueryService;
import org.imeta.orm.base.BizObject;
import org.imeta.orm.schema.QueryCondition;
import org.imeta.orm.schema.QueryField;
import org.imeta.orm.schema.QuerySchema;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class QuerySchemaTest {
    public static void main(String[] args) throws Exception{
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"consumer.xml"});
        context.start();
        IBillQueryService billQueryService = (IBillQueryService) context.getBean("billQueryService");

        QuerySchema querySchema = QuerySchema.create().addSelect("productId").addSelect(new QueryField("productId"))
            .appendQueryCondition(QueryCondition.name("id").eq(1460587085484288L));
        QuerySchema materialAssociationCompositionSchema = QuerySchema.create().name("materialAssociation")
            .fullname("ed.material.MaterialAssociation").addSelect("productSKUId");
        querySchema.addCompositionSchema(materialAssociationCompositionSchema);
        // QuerySchema远程执行，必须用自己的toString方法
        // String schema = JSONObject.toJSONString(querySchema);
        // JSONObject jsonObject = JSONObject.parseObject(schema);
        // String gson = GsonHelper.ToJSon(querySchema);

        List<BizObject> queryResultList = billQueryService.query("ed.material.Material", querySchema.toString());
        System.out.println(queryResultList);
    }
}
