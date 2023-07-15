package cn.itcast.netty.c1;

import lombok.extern.slf4j.Slf4j;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import static cn.itcast.netty.c1.ByteBufferUtil.debugAll;


@Slf4j
public class TestByteBufferString {
    public static void main(String[] args) {
        ByteBuffer buffer1 = StandardCharsets.UTF_8.encode("hello");
        ByteBuffer buffer2 = Charset.forName("utf-8").encode("hello");

        debugAll(buffer1);
        debugAll(buffer2);

        CharBuffer buffer3 = StandardCharsets.UTF_8.decode(buffer1);
        System.out.println(buffer3.getClass());
        System.out.println(buffer3.toString());
        int a = 10;
        int b = a ++ + ++ a +  a--;
        System.out.println(b);
    }
}
