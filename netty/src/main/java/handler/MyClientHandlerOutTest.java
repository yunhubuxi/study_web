package handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;

/**
 * Created by 敲代码的卡卡罗特
 * on 2018/8/12 21:49.
 */
public class MyClientHandlerOutTest extends ChannelOutboundHandlerAdapter {

    // public void read(ChannelHandlerContext ctx) {
    //     System.out.println("MyClientHandlerOutTest=channel read");
    //     // System.err.println("MyClientHandlerOutTest read ="+ctx.pipeline().names());
    //     ctx.read();
    // }

    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
        System.out.println("MyClientHandlerOutTest=channel wirte");
        // System.err.println("MyClientHandlerOutTest write ="+ctx.pipeline().names());
        ctx.writeAndFlush(msg);
    }

}
