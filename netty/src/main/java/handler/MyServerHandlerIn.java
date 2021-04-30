package handler;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.concurrent.TimeUnit;


/**
 * Created by 敲代码的卡卡罗特
 * on 2018/8/12 21:21.
 */
public class MyServerHandlerIn extends ChannelInboundHandlerAdapter  {

    /***
     * 这里我们覆盖了chanelRead()事件处理方法。
     * 每当从客户端收到新的数据时，
     * 这个方法会在收到消息时被调用，
     * 这个例子中，收到的消息的类型是ByteBuf
     * @param ctx 通道处理的上下文信息
     * @param msg 接收的消息
     */
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception{
        //读取客户端发来的信息
//        System.out.println(count++);
//        System.out.println(ctx.pipeline().names());
//        TimeUnit.SECONDS.sleep(50);
        ctx.fireChannelRead(msg);
        //2两种打印信息的方法。都可以实现
       /* byte[] b=new byte[m.readableBytes()];
        m.readBytes(b);
        System.out.println("client:"+new String(b,"utf-8"));*/
        //向客户端写信息
//        String name="你好客户端：这是服务端返回的信息!";
//        ctx.writeAndFlush(Unpooled.copiedBuffer(name.getBytes()));
    }

    /***
     * 这个方法会在发生异常时触发
     * @param ctx
     * @param cause
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        /***
         * 发生异常后，关闭连接
         */
        cause.printStackTrace();
        ctx.close();
    }
}
