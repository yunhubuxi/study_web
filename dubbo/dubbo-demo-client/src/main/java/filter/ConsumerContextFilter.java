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
        String token = "bttMis5bDJNTEFXZldtek1wUnVKekh6M3hrTno4VjZqcjF0Zy9UREdhZVNKSnNOaHZTdk9GdWIyNGlhdHp4eGk3NTNGb0NOSklSTTBzdlhsZTFiQjBhNzNFckJlQ1ViRFZSTFJyNGFLMWpxbTA9__1568877680424";
        RpcContext.getContext().setAttachment("rpcToken", token);
        return invoker.invoke(invocation);
    }
}
