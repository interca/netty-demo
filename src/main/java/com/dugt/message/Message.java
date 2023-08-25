package com.dugt.message;


import com.dugt.rpc.message.RpcRequestMessage;
import com.dugt.rpc.message.RpcResponseMessage;
import lombok.Data;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@Data
public abstract class Message implements Serializable {

    public static Class<?> getMessageClass(int messageType) {
        return messageClasses.get(messageType);
    }

    private int sequenceId;

    private int messageType;

    public abstract int getMessageType();

    /**
     * 登录请求
     */
    public static final int LoginRequestMessage = 0;

    /**
     * 登陆回应
     */
    public static final int LoginResponseMessage = 1;


    /**
     * 聊天
     */
    public static final int ChatRequestMessage = 2;
    public static final int ChatResponseMessage = 3;


    /**
     * 创建聊天室
     */
    public static final int GroupCreateRequestMessage = 4;
    public static final int GroupCreateResponseMessage = 5;

    /**
     * 加入聊天室
     */
    public static final int GroupJoinRequestMessage = 6;
    public static final int GroupJoinResponseMessage = 7;


    /**
     * 退出
     */
    public static final int GroupQuitRequestMessage = 8;
    public static final int GroupQuitResponseMessage = 9;


    /**
     * 聊天室回应
     */
    public static final int GroupChatRequestMessage = 10;
    public static final int GroupChatResponseMessage = 11;
    public static final int GroupMembersRequestMessage = 12;
    public static final int GroupMembersResponseMessage = 13;

    public static final int PingMessage = 14;
    public static final int PongMessage = 15;

    public static final int RPC_MESSAGE_TYPE_REQUEST = 101;
    public static final int  RPC_MESSAGE_TYPE_RESPONSE = 102;

    private static final Map<Integer, Class<?>> messageClasses = new HashMap<>();
    static {
        messageClasses.put(LoginRequestMessage, LoginRequestMessage.class);
        messageClasses.put(LoginResponseMessage, LoginResponseMessage.class);
        messageClasses.put(ChatRequestMessage, ChatRequestMessage.class);
        messageClasses.put(ChatResponseMessage, ChatResponseMessage.class);
        messageClasses.put(GroupCreateRequestMessage, GroupCreateRequestMessage.class);
        messageClasses.put(GroupCreateResponseMessage, GroupCreateResponseMessage.class);
        messageClasses.put(GroupJoinRequestMessage, GroupJoinRequestMessage.class);
        messageClasses.put(GroupJoinResponseMessage, GroupJoinResponseMessage.class);
        messageClasses.put(GroupQuitRequestMessage, GroupQuitRequestMessage.class);
        messageClasses.put(GroupQuitResponseMessage, GroupQuitResponseMessage.class);
        messageClasses.put(GroupChatRequestMessage, GroupChatRequestMessage.class);
        messageClasses.put(GroupChatResponseMessage, GroupChatResponseMessage.class);
        messageClasses.put(GroupMembersRequestMessage, GroupMembersRequestMessage.class);
        messageClasses.put(GroupMembersResponseMessage, GroupMembersResponseMessage.class);

        messageClasses.put(RPC_MESSAGE_TYPE_REQUEST, RpcRequestMessage.class);
        messageClasses.put(RPC_MESSAGE_TYPE_RESPONSE, RpcResponseMessage.class);
    }
}