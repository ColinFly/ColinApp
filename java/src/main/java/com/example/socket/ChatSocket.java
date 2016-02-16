package com.example.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * Created by colin on 16-2-16.
 */
public class ChatSocket extends Thread {

    private Socket socket;

    public ChatSocket(Socket socket) {
        this.socket = socket;
    }

    //向客户端发消息
    //读取客户端消息的功能
    @Override
    public void run() {
//        int count = 0;
//        while (true) {
//            count++;
//            out("loop+" + count);
//            try {
//                sleep(500);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
        try {
            BufferedReader br=new BufferedReader(new InputStreamReader(socket.getInputStream(),"UTF-8"));
            String line = null;

            while ((line = br.readLine() )!= null) {
                ChatManager.getChatManaer().publish(this, line);
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void out(String out) {
        try {
            socket.getOutputStream().write(out.getBytes("UTF-8"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

