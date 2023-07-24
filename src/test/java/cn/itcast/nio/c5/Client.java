package cn.itcast.nio.c5;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;

/**
 * 处理消息边界
 */
public class Client {
    public static void main(String[] args) {
        try {
            SocketChannel sc = SocketChannel.open();
            sc.connect(new InetSocketAddress("localhost", 8080));
            SocketAddress address = sc.getLocalAddress();
            //sc.write(Charset.defaultCharset().encode("hello\nworld\n"));
            //sc.write(Charset.defaultCharset().encode("0123\n456789abcdef"));
            sc.write(Charset.defaultCharset().encode("0123456789abcdef3333\n"));
            System.in.read();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
