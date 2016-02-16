package com.example.socket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by colin on 16-2-16.
 */
public class ServerListener extends Thread {
    @Override
    public void run() {
        try {
            //1-63353
            ServerSocket serverSocket = new ServerSocket(11111);
            while (true) {
                //block阻塞监听状态
                Socket socket=serverSocket.accept();
                System.out.println("有客户端链接到11111端口");
                //将socket传递给新的线程处理
//                new ChatSocket(socket).start();//每一个线程都是独立的,不能相互沟通
                ChatSocket cs = new ChatSocket(socket);
                cs.start();
                ChatManager.getChatManaer().add(cs);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
