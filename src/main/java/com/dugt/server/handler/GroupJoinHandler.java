package com.dugt.server.handler;

import com.dugt.message.GroupJoinRequestMessage;
import com.dugt.message.GroupJoinResponseMessage;
import com.dugt.server.session.Group;
import com.dugt.server.session.GroupSessionFactory;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

@ChannelHandler.Sharable
public class GroupJoinHandler extends SimpleChannelInboundHandler<GroupJoinRequestMessage> {
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, GroupJoinRequestMessage groupJoinRequestMessage) throws Exception {
        String username = groupJoinRequestMessage.getUsername();
        String groupName = groupJoinRequestMessage.getGroupName();
        GroupSessionFactory.getGroupSession().joinMember(groupName,username);
        channelHandlerContext.writeAndFlush(new GroupJoinResponseMessage(true,"加入成功"));
    }
}
