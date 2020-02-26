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
        String token = "bttdkl3eGV4ZXVzVGZ0NksxdktkRzVmVnFhTHE1bE9hVTVDNjNUYnBsakI4YTBiU3M1bXRCRUp2VTRjZXJjRVFIK0ZjL0lSdi8zVmxITVhhSzZvUG9pQURxMnF0NGdjQncya3BrSkVyR1FIMVU9XzAzMmQ2NTI5LTM1YjYtNDFiNi04ODE2LTE1Y2FhNDFhNTU1OF9pZHRlc3QueXl1YXAuY29tXzE1NzgwNDI4MTY4ODE.__1578376311892";
        RpcContext.getContext().setAttachment("rpcToken", token);
        return invoker.invoke(invocation);
    }
}
