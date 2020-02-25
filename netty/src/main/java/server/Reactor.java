package server;

import handler.MyServerChildHandler1;
import handler.MyServerChildHandler2;
import handler.MyServerHandler1;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class Reactor {
    public static void main(String[] arg) {
        //boss线程监听端口，worker线程负责数据读写
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        EventLoopGroup workerGroup = new NioEventLoopGroup(1);

        ServerBootstrap b = new ServerBootstrap();
        b = b.group(bossGroup, workerGroup);
        b = b.option(ChannelOption.SO_BACKLOG, 128);
        b = b.childOption(ChannelOption.SO_KEEPALIVE, true);

        b.channel(NioServerSocketChannel.class);
        b.handler(new ChannelInitializer<NioServerSocketChannel>() {
            // final EventLoopGroup group = new DefaultEventLoopGroup(16);
            @Override
            protected void initChannel(NioServerSocketChannel ch)  {
                ch.pipeline().addLast(new MyServerHandler1());
            }
        });
        b.childHandler(new ChannelInitializer<SocketChannel>() {
            final EventLoopGroup group = new DefaultEventLoopGroup(8);
            @Override
            protected void initChannel(SocketChannel ch) {
                ch.pipeline().addLast(new MyServerChildHandler1()).addLast(group, new MyServerChildHandler2());
            }
        });
        try {
            ChannelFuture bf = b.bind(8080).sync();
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
