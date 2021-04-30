package server;

import handler.MyServerChildHandlerIn;
import handler.MyServerChildHandlerOut;
import handler.MyServerChildHandlerDuplex;
import handler.MyServerHandlerIn;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * Created by 敲代码的卡卡罗特
 * on 2018/8/12 17:31.
 */
public class Server {
    public static void main(String[] arg) {
        //boss线程监听端口，worker线程负责数据读写
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        ServerBootstrap b = new ServerBootstrap();
        b = b.group(bossGroup, workerGroup);
        b = b.option(ChannelOption.SO_BACKLOG, 128);
        b = b.childOption(ChannelOption.SO_KEEPALIVE, true);
        b.channel(NioServerSocketChannel.class);
        b.handler(new ChannelInitializer<NioServerSocketChannel>() {
            @Override
            protected void initChannel(NioServerSocketChannel ch) {
                ch.pipeline().addLast(new MyServerHandlerIn());
            }
        });
        b.childHandler(new ChannelInitializer<SocketChannel>() {

            @Override
            protected void initChannel(SocketChannel ch) {
                ch.pipeline().addLast(new MyServerChildHandlerIn()).addLast(new MyServerChildHandlerOut()).addLast(new MyServerChildHandlerDuplex());//
            }
        });
        try {
            ChannelFuture bf = b.bind(8888).sync();
            bf.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            /***
             * 优雅关闭
             */
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }
    }
}
