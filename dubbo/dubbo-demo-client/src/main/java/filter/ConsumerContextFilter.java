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
        String token = "bttV3l6NHV6REN1L3dsWENnN1RFM0g4bGkzZkpMOW5iQTVlanJLb0tOamNkU09JaGsrRFZaUkdHZFhmL3p5WUNIWXJvQUJoTHB5OEIrUHlDbG5CU05NbEpJSmcyZGg2SXRXeko0aGJVYkRaeEk9__1571819587847";
        RpcContext.getContext().setAttachment("rpcToken", token);
        return invoker.invoke(invocation);
    }
}
