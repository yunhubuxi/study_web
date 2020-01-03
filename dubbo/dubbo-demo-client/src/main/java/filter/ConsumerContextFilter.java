package filter;

import com.alibaba.dubbo.common.extension.Activate;
import com.alibaba.dubbo.rpc.*;

@Activate(
    group = {"consumer"}
)
public class ConsumerContextFilter implements Filter {
    public ConsumerContextFilter() {
    }

    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        String token = "bttYkVicGVCTDRrTWdGcWd2cXpqUjFyR0oxS1prRVM3UnRHdHE3SXNQaDdreEpTNTVsUlhrenBhVitBUXkyaVVIY250WkF1cUN2MWx2S0hPVFFNSTVvZ0pJSmcyZGg2SXRXeko0aGJVYkRaeEk9XzhkMDEyOTk2LWYxZGEtNGU2YS04MzA1LTg0ZDY1Njg3NWQzY19pZHRlc3QueXl1YXAuY29tXzE1NzcyNDIyMzkxODU.__1577242227188";
        RpcContext.getContext().setAttachment("rpcToken", token);
        return invoker.invoke(invocation);
    }
}
