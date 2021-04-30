package handler;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelDuplexHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.CharsetUtil;

public class MyServerChildHandlerDuplex extends ChannelDuplexHandler {

    public void channelRegistered(ChannelHandlerContext ctx) {
        System.out.println("MyServerChildHandlerDuplex=channelRegistered");
    }

    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        //读取客户端发来的信息
        ByteBuf m = (ByteBuf) msg; // ByteBuf是netty提供的
        System.out.println("MyServerChildHandlerDuplex：收到的消息 === " + m.toString(CharsetUtil.UTF_8));
        // TimeUnit.SECONDS.sleep(50);
        //2两种打印信息的方法。都可以实现
       /* byte[] b=new byte[m.readableBytes()];
        m.readBytes(b);
        System.out.println("client:"+new String(b,"utf-8"));*/
        //向客户端写信息
        String name = "你好客户端：这是服务端返回的信息!";
        ctx.writeAndFlush(Unpooled.copiedBuffer(name.getBytes()));

    }

    public void channelActive(ChannelHandlerContext ctx) {
        ctx.fireChannelActive();
    }


}
