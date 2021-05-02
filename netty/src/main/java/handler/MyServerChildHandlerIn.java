package handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.time.LocalDateTime;


/**
 * Created by 敲代码的卡卡罗特
 * on 2018/8/12 21:21.
 */
public class MyServerChildHandlerIn extends ChannelInboundHandlerAdapter {
    static int count = 1;


    public void channelRegistered(ChannelHandlerContext ctx) {
        System.out.println("MyServerChildHandlerIn=channelRegistered");
    }
    /***
     * 这里我们覆盖了chanelRead()事件处理方法。
     * 每当从客户端收到新的数据时，
     * 这个方法会在收到消息时被调用，
     * 这个例子中，收到的消息的类型是ByteBuf
     * @param ctx 通道处理的上下文信息
     * @param msg 接收的消息
     */
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        System.out.println("MyServerChildHandlerIn ：" + count++);
        System.out.println("MyServerChildHandlerIn read = "+ctx.pipeline().names());
        ctx.fireChannelRead(msg);
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
