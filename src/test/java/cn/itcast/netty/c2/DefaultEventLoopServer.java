package cn.itcast.netty.c2;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import lombok.extern.slf4j.Slf4j;

import java.nio.charset.Charset;

@Slf4j
public class DefaultEventLoopServer {
    public static void main(String[] args) throws InterruptedException {
        EventLoopGroup group = new DefaultEventLoopGroup();
        new ServerBootstrap()
                .group(new NioEventLoopGroup(1), new NioEventLoopGroup(2))
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel ch) {
                        ch.pipeline().addLast(new ChannelInboundHandlerAdapter() {
                            @Override
                            public void channelRead(ChannelHandlerContext ctx, Object msg) {
                                ByteBuf byteBuf = msg instanceof ByteBuf ? ((ByteBuf) msg) : null;
                                if (byteBuf != null) {
                                    log.debug(byteBuf.toString(Charset.defaultCharset()));
                                }
                                ctx.fireChannelRead(msg);
                            }
                        }).addLast(group,"hyj",new ChannelInboundHandlerAdapter() {
                            @Override
                            public void channelRead(ChannelHandlerContext ctx, Object msg) {
                                ByteBuf byteBuf = msg instanceof ByteBuf ? ((ByteBuf) msg) : null;
                                if (byteBuf != null) {
                                    log.debug(byteBuf.toString(Charset.defaultCharset()));
                                }
                                System.out.println("sss");
                            }
                        });

                    }
                }).bind(8080).sync();
    }
}
