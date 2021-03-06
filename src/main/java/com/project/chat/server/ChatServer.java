package com.project.chat.server;

import com.project.chat.config.AppContext;
import com.project.chat.network.TCPConnection;
import com.project.chat.network.TCPConnectionListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.ServerSocket;

@Component
public class ChatServer {

    private static final int SERVER_PORT = 8189;

    @Autowired
    private TCPConnectionListener serverTCPConnectionListener;

    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        ctx.register(AppContext.class);
        ctx.refresh();

        ChatServer chat = (ChatServer) ctx.getBean("chatServer");
        chat.runServer();
        ctx.close();
    }

    private void runServer() {
        System.out.println("Server running...");
        try (ServerSocket serverSocket = new ServerSocket(SERVER_PORT)) {
            while (true) {
                try {
                    new TCPConnection(serverTCPConnectionListener, serverSocket.accept());
                } catch (IOException e) {
                    System.out.println("TCPConnection exception: " + e);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
