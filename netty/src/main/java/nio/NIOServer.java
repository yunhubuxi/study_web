package nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.*;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Scanner;

public class NIOServer implements Runnable {

    // 多路复用器，选择器。用于注册通道
    private Selector selector;
    // 定义两个缓存 分别用于读和写；初始化空间大小为1024字节
    private ByteBuffer readBuffer = ByteBuffer.allocate(1024);
    private ByteBuffer writeBuffer = ByteBuffer.allocate(1024);

    public static void main(String[] args){
        new Thread(new NIOServer(9999)).start();
    }

    public NIOServer (int port){
        init(port);
    }

    private void init(int port) {
        try {
            System.out.println("server starting at port " + port + "...");
            this.selector = Selector.open();

            // 开启服务通道
            ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
            // 非阻塞，传递参数为true为阻塞模式
            serverSocketChannel.configureBlocking(false);
            // 绑定端口
            serverSocketChannel.bind(new InetSocketAddress(port));
            // 注册，并标记当前服务通道状态
            /**
             * register(Selector,int)
             * int  - 状态编码
             * OP_ACCEPT ：连接成功的标记
             * OP_READ ：可以读取数据的标记
             * OP_WRITE ：可以写入数据的标记
             * OP_CONNECT ：建立连接后的标记
             */
            serverSocketChannel.register(this.selector, SelectionKey.OP_ACCEPT);
            System.out.println("server start");
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public void run() {

        while (true){
            try {
                // 阻塞方法，当至少一个通道被选中，此方法返回。
                this.selector.select();
                // 返回以选中的通道标记集合，集合保存的是通道的标记,相当于是通道的ID
                Iterator<SelectionKey> keys = this.selector.selectedKeys().iterator();
                while (keys.hasNext()){
                    SelectionKey key = keys.next();
                    // 将本次要处理的通道冲集合中删除,下次删除根据新的通道列表再次执行必要的业务逻辑
                    keys.remove();
                    // 通道是否有效
                    if(key.isValid()){
                        try {
                            if (key.isAcceptable()){
                                accept(key);
                            }
                        }catch (CancelledKeyException e){
                            key.cancel();
                        }
                        try{
                            if(key.isReadable()){
                                read(key);
                            }
                        }catch (CancelledKeyException cke){
                            key.cancel();
                        }
                        try{
                            if(key.isWritable()){
                                write(key);
                            }
                        }catch (CancelledKeyException cke){
                            key.cancel();
                        }
                    }

                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void write(SelectionKey key){
        this.writeBuffer.clear();
        SocketChannel channel = (SocketChannel)key.channel();
        Scanner reader = new Scanner(System.in);
        try {
            System.out.println("put message for send to client > ");
            String line = reader.nextLine();
            writeBuffer.put(line.getBytes("UTF-8"));
            writeBuffer.flip();
            channel.write(writeBuffer);

            channel.register(this.selector,SelectionKey.OP_READ);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    private void read(SelectionKey key){
        try {
            // 清空读缓存
            this.readBuffer.clear();
            // 获取通道
            SocketChannel channel = (SocketChannel)key.channel();
            // 将通道中的数据读到缓存中。通道中的数据，就是客户端发送给服务器的数据。
            int readLength = channel.read(readBuffer);
            // 检查客户端是否写入数据
            if(readLength == -1){
                // 通道关闭
                key.channel().close();
                // 关闭连接
                key.cancel();
                return;
            }
            // flip,NIO中最复杂的操作就是Buffer的控制
            /** Buffer中有一个游标。游标的信息在操作后不会归零，如果直接访问Buffer的话，数据有可能不一致。
             * flip是重置游标的方法.NIO编程中，flip方法是常用的方法
             *
             */
            this.readBuffer.flip();
            // 字节数据，保存具体数据。Buffer.remaining() ->获取Buffer中有效数据长度的方法。
            byte[] datas = new byte[readBuffer.remaining()];
            // 是将Buffer中的有效数据保存到有效数组中。
            readBuffer.get(datas);
            System.out.println("from" + channel.getRemoteAddress() + " client ： " + new String(datas,"UTF-8"));

            channel.register(this.selector,SelectionKey.OP_WRITE);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    private void accept(SelectionKey key){
        try {
            // 此通道为init方法中注册到Seleor上的ServerSocketChannel
            ServerSocketChannel serverSocketChannel = (ServerSocketChannel) key.channel();
            // 阻塞方法，当客户端发起请求后返回.此通道和客户端一一对应
            SocketChannel channel = serverSocketChannel.accept();
            channel.configureBlocking(false);
            // 设置对用客户端的通道标记状态,此通道为读取数据使用的。
            channel.register(this.selector,SelectionKey.OP_READ);

        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
