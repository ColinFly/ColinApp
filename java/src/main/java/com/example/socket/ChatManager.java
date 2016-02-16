package com.example.socket;

import java.util.Vector;

/**
 * Created by colin on 16-2-16.
 */
public class ChatManager {
    private ChatManager() {

    }

    private static final ChatManager mInstance=new ChatManager();

    public static ChatManager getChatManaer() {
        return mInstance;
    }

    Vector<ChatSocket> vector=new Vector<>();
    //为当前的集合添加ChatSocket对象
    public void add(ChatSocket chatSocket) {
        vector.add(chatSocket);
    }

    //向其他客户端传递消息
    public void publish(ChatSocket chatSocket,String out) {
        for (int i = 0; i < vector.size(); i++) {
            ChatSocket cs = vector.get(i);
            if (!cs.equals(chatSocket)) {
                cs.out(out);
            }
        }
    }
}
