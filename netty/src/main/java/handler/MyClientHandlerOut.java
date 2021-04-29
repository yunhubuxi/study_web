package handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;

/**
 * Created by 敲代码的卡卡罗特
 * on 2018/8/12 21:49.
 */
public class MyClientHandlerOut extends ChannelOutboundHandlerAdapter {


    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) {
        System.out.println("channel wirte");
    }

    public void read(ChannelHandlerContext ctx) {
        System.out.println("channel read");
    }


}
