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
        String token = "bttRm9LSDVjM1A0MHVOL1d4ajRDSmt5RGFOc1VkSVlNMHFLZlIzc3FWUkRDaHN4blpwanBFems2QTEvRlhKQ3huME42cFBaSXgvOVFVL2IzOGpFTjlGa3BJSmcyZGg2SXRXeko0aGJVYkRaeEk9__1568805809243";
        RpcContext.getContext().setAttachment("rpcToken", token);
        return invoker.invoke(invocation);
    }
}
