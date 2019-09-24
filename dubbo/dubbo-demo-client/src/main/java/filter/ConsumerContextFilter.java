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
        String token = "bttcG5VU0t1ZVg2bmtiUzc5di9FbitoU003UDlxNU4rVzM0SWlaSmkxQnU1TDd2WURhZC9TRUJnWFlmN1g3bWQ2Y3hNRC81RDFHMG5jc0NsYlVwb0RMUEhFckJlQ1ViRFZSTFJyNGFLMWpxbTA9__1569320869861";
        RpcContext.getContext().setAttachment("rpcToken", token);
        return invoker.invoke(invocation);
    }
}
