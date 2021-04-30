package handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;
import io.netty.util.ReferenceCountUtil;

import java.time.LocalDateTime;

/**
 * Created by 敲代码的卡卡罗特
 * on 2018/8/12 21:49.
 */
public class MyClientHandlerInTest extends ChannelInboundHandlerAdapter {

    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception{
        try {
            //读取服务端发来的信息
            ByteBuf m = (ByteBuf) msg; // ByteBuf是netty提供的
            System.out.println(LocalDateTime.now().toString());
            System.out.println("client:"+m.toString(CharsetUtil.UTF_8));
            System.err.println("MyClientHandlerInTest read ="+ctx.pipeline().names());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //当没有写操作的时候要把msg给清空。如果有写操作，就不用清空，因为写操作会自动把msg清空。这是netty的特性。
            ReferenceCountUtil.release(msg);
        }

    }

    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("MyClientHandlerInTest=channelActive");
        System.err.println("MyClientHandlerInTest active="+ctx.pipeline().names());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}
