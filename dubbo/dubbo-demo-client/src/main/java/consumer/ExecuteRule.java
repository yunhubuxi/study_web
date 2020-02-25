package consumer;

import com.yonyoucloud.uretail.api.IBillQueryService;
import com.yonyoucloud.uretail.bill.rule.base.IRpcRule;
import com.yonyoucloud.uretail.bill.rule.common.RuleResult;
import com.yonyoucloud.uretail.model.BillContext;
import com.yonyoucloud.uretail.model.BillRuleRegister;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.HashMap;
import java.util.Map;

/**
 * 也是调用IBillQueryService接口
 */
public class ExecuteRule {
    public static void main(String[] args) throws Exception {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"consumer.xml"});
        context.start();
        BillRuleRegister billRule = new BillRuleRegister();
        billRule.setRuleId("testBillContext");
        billRule.setDomain("gkyrqy");
        Map map = new HashMap();
        map.put("fff", "45654");
        BillContext billContext = new BillContext();
        billContext.setBillnum("123123");
        System.out.println(map.hashCode());
        System.out.println(billContext.hashCode());
        System.out.println(map.toString());
        System.out.println(billContext.getBillnum());
        // RpcRuleImpl和billService.executeRule完成了参数改变
        // RpcRuleImpl在消费者方执行
        // billService.executeRule就是专为服务提供者设计的，在服务提供方执行
        RpcRuleImplTwo rpcRule = new RpcRuleImplTwo();
        IBillQueryService billQueryService = (IBillQueryService) context.getBean("billQueryService");
        rpcRule.execute(billRule, billContext, map, billQueryService);

        System.out.println(map.hashCode());
        System.out.println(billContext.hashCode());
        System.out.println(map.toString());
        System.out.println(billContext.getBillnum());

        // RuleResult ruleResult2 = billQueryService.executeRule(billRule, billContext, map);
        // System.out.println(ruleResult2.toString());
    }
}
