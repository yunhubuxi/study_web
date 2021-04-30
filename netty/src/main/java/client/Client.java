package client;


import handler.MyClientHandlerIn;
import handler.MyClientHandlerOut;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * Created by 敲代码的卡卡罗特
 * on 2018/8/12 21:46.
 */
public class Client {

    public static void main(String[] arg) {
        /**
         * 如果你只指定了一个EventLoopGroup，
         * 那他就会即作为一个‘boss’线程，
         * 也会作为一个‘workder’线程，
         * 尽管客户端不需要使用到‘boss’线程。
         */
        Bootstrap b = new Bootstrap();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        b.group(workerGroup);
        b.channel(NioSocketChannel.class);
        b.option(ChannelOption.SO_KEEPALIVE, true);
        b.handler(new ChannelInitializer<SocketChannel>() {
            @Override
            public void initChannel(SocketChannel ch) {
                ch.pipeline().addLast(new MyClientHandlerIn()).addLast(new MyClientHandlerOut());
            }
        });
        try {
            ChannelFuture f = b.connect("127.0.0.1", 8888).sync();
            //向服务端发送信息
            f.channel().writeAndFlush(Unpooled.copiedBuffer("Client".getBytes()));
            f.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            workerGroup.shutdownGracefully();
        }
    }
}
