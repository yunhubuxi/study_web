package consumer;

import com.yonyoucloud.uretail.api.IBillQueryService;
import com.yonyoucloud.uretail.bill.rule.common.RuleResult;
import com.yonyoucloud.uretail.model.BillContext;
import com.yonyoucloud.uretail.model.BillRuleRegister;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.HashMap;

/**
 * 也是调用IBillQueryService接口
 */
public class ExecuteRule {
    public static void main(String[] args) throws Exception{
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"consumer.xml"});
        context.start();
        BillRuleRegister billRule = new BillRuleRegister();
        billRule.setRuleId("planDetailsReportBeforeQueryRule");
        billRule.setDomain("gkyrqy");

        IBillQueryService billQueryService = (IBillQueryService) context.getBean("billQueryService");
        RuleResult ruleResult = billQueryService.executeRule(billRule, new BillContext(), new HashMap<>());
        System.out.println(ruleResult);
    }
}
