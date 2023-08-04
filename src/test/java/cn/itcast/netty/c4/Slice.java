package cn.itcast.netty.c4;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;

import static cn.itcast.netty.c4.ByteBufferTest.log;

public class Slice {
    public static void main(String[] args) {
        ByteBuf byteBuf = ByteBufAllocator.DEFAULT.buffer(10);
        byteBuf.writeBytes(new byte[]{'a','b','c','d','e','f','g','h','i','j'});
        ByteBuf slice = byteBuf.slice(0, 5);
        ByteBuf slice1 = byteBuf.slice(5, 5);
        log(slice);
        log(slice1);
    }
}
