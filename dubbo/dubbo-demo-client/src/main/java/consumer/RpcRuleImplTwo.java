package consumer;

import com.alibaba.fastjson.JSON;
import com.yonyoucloud.uretail.api.IBillQueryService;
import com.yonyoucloud.uretail.bill.dto.BillDataDto;
import com.yonyoucloud.uretail.bill.rule.base.IRpcRule;
import com.yonyoucloud.uretail.bill.rule.base.commonRuleUtils;
import com.yonyoucloud.uretail.bill.rule.common.RuleResult;
import com.yonyoucloud.uretail.dubbo.DubboReferenceUtils;
import com.yonyoucloud.uretail.model.BillContext;
import com.yonyoucloud.uretail.model.BillRuleRegister;
import com.yonyoucloud.uretail.util.Toolkit;
import org.imeta.biz.base.BizContext;
import org.imeta.biz.base.ObjectHierarchyBuilder;
import org.imeta.biz.base.Objectlizer;
import org.imeta.core.lang.BooleanUtils;
import org.imeta.core.model.Entity;
import org.imeta.orm.base.BizObject;
import org.springframework.beans.BeanUtils;
import org.springframework.transaction.support.TransactionSynchronizationAdapter;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


public class RpcRuleImplTwo implements IRpcRule {
    public RpcRuleImplTwo() {
    }

    public RuleResult execute(BillRuleRegister billRule, BillContext billContext, Map<String, Object> paramMap, IBillQueryService billService) throws Exception {
        if (null != billService) {
            if (!Toolkit.isEmpty(billRule.getConfig())) {
                Map<String, Object> configMap = (Map) JSON.parseObject(billRule.getConfig(), Map.class);
                if (BooleanUtils.b(configMap.get("afterCommit"))) {
                    this.afterCommit(billService, billRule, billContext, paramMap);
                    return null;
                }
            }

            RuleResult resultTemp = billService.executeRule(billRule, billContext, paramMap);
            if (null != resultTemp) {
                Map<String, Object> outParams = resultTemp.outParams;
                if (null != outParams && outParams.size() > 0) {
                    if (null != outParams.get("billContext")) {
                        BeanUtils.copyProperties(outParams.get("billContext"), billContext);
                    }

                    if (null != outParams.get("paramMap")) {
                        Map<String, Object> outParamMap = (Map) outParams.get("paramMap");
                        Iterator var8 = outParamMap.entrySet().iterator();

                        while (var8.hasNext()) {
                            Map.Entry<String, Object> entry = (Map.Entry) var8.next();
                            if (!"param".equals(entry.getKey())) {
                                paramMap.put(entry.getKey(), entry.getValue());
                            }
                        }

                        BillDataDto dto = (BillDataDto) outParamMap.get("param");
                        if (null != dto) {
                            BillDataDto dtoOri = (BillDataDto) paramMap.get("param");
                            Object obj = dto.getData();
                            if (null != obj && obj instanceof List) {
                                List<Map<String, Object>> list = (List) obj;
                                list = commonRuleUtils.clearParent(list);
                                List<BizObject> listNew = new ArrayList();
                                Iterator var13 = list.iterator();

                                while (var13.hasNext()) {
                                    Map<String, Object> bill = (Map) var13.next();
                                    BizObject obj1 = Objectlizer.convert(bill, billContext.getFullname());
                                    Entity entity = BizContext.getMetaRepository().entity(billContext.getFullname());
                                    ObjectHierarchyBuilder.build(obj1, entity);
                                    listNew.add(obj1);
                                }

                                if (null != dtoOri) {
                                    dtoOri.setData(listNew);
                                }
                            }

                            BeanUtils.copyProperties(dto, dtoOri, new String[]{"data"});
                        }
                    }
                }
            }

            return null;
        } else {
            throw new Exception("未获得" + billRule.getDomain() + "服务引用");
        }
    }

    private void afterCommit(final IBillQueryService billService, final BillRuleRegister billRule, final BillContext billContext, final Map<String, Object> paramMap) throws KeyManagementException, NoSuchAlgorithmException, Exception {
        if (TransactionSynchronizationManager.isActualTransactionActive()) {
            TransactionSynchronizationManager.registerSynchronization(new TransactionSynchronizationAdapter() {
                public void afterCommit() {
                    try {
                        billService.executeRule(billRule, billContext, paramMap);
                    } catch (KeyManagementException var2) {
                        var2.printStackTrace();
                    } catch (NoSuchAlgorithmException var3) {
                        var3.printStackTrace();
                    } catch (Exception var4) {
                        var4.printStackTrace();
                    }

                }
            });
        } else {
            billService.executeRule(billRule, billContext, paramMap);
        }

    }

    private <T> T deepCopy(T src) throws Exception {
        ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
        ObjectOutputStream out = new ObjectOutputStream(byteOut);
        out.writeObject(src);
        ByteArrayInputStream byteIn = new ByteArrayInputStream(byteOut.toByteArray());
        ObjectInputStream in = new ObjectInputStream(byteIn);
        T dest = (T) in.readObject();
        return dest;
    }

    @Override
    public RuleResult execute(BillRuleRegister billRuleRegister, BillContext billContext, Map<String, Object> map) throws Exception {
        return null;
    }
}

