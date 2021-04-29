package server;

import handler.MyServerChildHandler1;
import handler.MyServerChildHandler2;
import handler.MyServerChildHandler3;
import handler.MyServerHandler1;
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
        //服务类
        ServerBootstrap b = new ServerBootstrap();
        b = b.group(bossGroup, workerGroup);
        b = b.option(ChannelOption.SO_BACKLOG, 128);
        b = b.childOption(ChannelOption.SO_KEEPALIVE, true);
        //设置niosocket工厂
        b.channel(NioServerSocketChannel.class);
        b.handler(new ChannelInitializer<NioServerSocketChannel>() {
//            final EventLoopGroup group = new DefaultEventLoopGroup(16);

            @Override
            protected void initChannel(NioServerSocketChannel ch) throws Exception {
                ch.pipeline().addLast(new MyServerHandler1());
            }
        });
        b.childHandler(new ChannelInitializer<SocketChannel>() {
            final EventLoopGroup group = new DefaultEventLoopGroup(16);

            @Override
            protected void initChannel(SocketChannel ch) throws Exception {
                ch.pipeline().addLast(new MyServerChildHandler1()).addLast(new MyServerChildHandler2()).addLast(new MyServerChildHandler3());//
            }
        });
//        ServerBootstrap a = new ServerBootstrap().group(bossGroup, workerGroup)
//                .option(ChannelOption.SO_BACKLOG, 128)
//                .childOption(ChannelOption.SO_KEEPALIVE, true)
//                .channel(NioServerSocketChannel.class)
//                .handler(new MyServerHandlerHandle()).childHandler(new ChannelInitializer<SocketChannel>() {
//                    final EventLoopGroup group = new DefaultEventLoopGroup(16);
//
//                    @Override
//                    protected void initChannel(SocketChannel ch) throws Exception {
//                        ch.pipeline().addLast(group, new MyServerHandler());//
//                    }
//                });
        try {
            //绑定端口并启动去接收进来的连接
//            ChannelFuture af = a.bind(9999).sync();
            ChannelFuture bf = b.bind(8888).sync();
            // ChannelFuture bf1 = b.bind(8889).sync();
            // ChannelFuture bf2 = b.bind(8890).sync();
            // ChannelFuture bf3 = b.bind(8891).sync();
            // 这里会一直等待，直到socket被关闭
//            af.channel().closeFuture().sync();
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
