package com.dugt.server.handler;

import com.dugt.message.GroupMembersRequestMessage;
import com.dugt.message.GroupMembersResponseMessage;
import com.dugt.server.session.GroupSessionFactory;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Set;



/**
 * 列出群的所有信息
 */
@ChannelHandler.Sharable
public class GroupListMemberHandler extends SimpleChannelInboundHandler<GroupMembersRequestMessage> {
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, GroupMembersRequestMessage groupMembersRequestMessage) throws Exception {
        String groupName = groupMembersRequestMessage.getGroupName();
        Set<String> members = GroupSessionFactory.getGroupSession().getMembers(groupName);
        channelHandlerContext.writeAndFlush(new GroupMembersResponseMessage(members));
    }
}
