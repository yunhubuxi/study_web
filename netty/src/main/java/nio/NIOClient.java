package nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.Scanner;

public class NIOClient {
    public static void main(String[] args){
        // 创建远程连接地址
        InetSocketAddress remote = new InetSocketAddress("localhost",9999);
        SocketChannel channel = null;
        ByteBuffer buffer = ByteBuffer.allocate(1024);

        try {
            channel = SocketChannel.open();
            channel.connect(remote);
            Scanner reader = new Scanner(System.in);
            while (true){
                System.out.println("put message for send to Server >");
                String line = reader.nextLine();
                if(line.equals("exit")){
                    break;
                }
                buffer.put(line.getBytes("UTF-8"));
                buffer.flip();
                channel.write(buffer);
                buffer.clear();

                int readLength = channel.read(buffer);
                if(readLength == -1){
                    break;
                }
                // 重置缓存游标
                buffer.flip();
                byte[] datas = new byte[buffer.remaining()];

                // 读取数据到数组
                buffer.get(datas);
                System.out.println("from server : " + new String(datas, "UTF-8"));
                // 清空缓存
                buffer.clear();
            }

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(channel != null){
                try {
                    channel.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
    }
}
