package cn.itcast.nio.c1;



import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class TestByteBuffer {

    public static void main(String[] args) {
        try (RandomAccessFile file = new RandomAccessFile(
                "C:\\Users\\waili\\Desktop\\新建文本文档 (2).txt", "rw")) {
            FileChannel channel = file.getChannel();
            ByteBuffer buffer = ByteBuffer.allocate(10);
            do {
                // 向 buffer 写入
                int len = channel.read(buffer);
                System.out.println("读到字节数：{}"+ len);
                if (len == -1) {
                    break;
                }
                // 切换 buffer 读模式
                buffer.flip();
                //是否还有未读数据
                while (buffer.hasRemaining()) {
                    //每次读一个字节
                    System.out.println("{}"+ (char) buffer.get());
                }
                // 切换 buffer 写模式
                buffer.clear();
            } while (true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
