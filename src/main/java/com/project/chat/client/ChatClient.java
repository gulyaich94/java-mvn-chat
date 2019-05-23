package com.project.chat.client;

import com.project.chat.client.view.ClientWindow;
import com.project.chat.config.AppContext;
import com.project.chat.network.TCPConnection;
import com.project.chat.network.TCPConnectionListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;

public class ChatClient {

    private static final String IP_ADDR = "10.113.250.60";
    private static final int PORT = 8189;
    private TCPConnection connection;

    @Autowired
    private ClientWindow clientWindow;

    @Autowired
    private TCPConnectionListener clientTCPConnectionListener;

    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        ctx.register(AppContext.class);
        ctx.refresh();

        ChatClient chat = (ChatClient) ctx.getBean("chatClient");
        chat.init();
        chat.startClientWindowChat();

        ctx.close();
    }

    private void init() {
        try {
            connection = new TCPConnection(clientTCPConnectionListener, IP_ADDR, PORT);
        } catch (IOException e) {
            clientWindow.printMsg("Connection exception " + e);
        }
    }

    private void startClientWindowChat() {
        clientWindow.runChat(connection);
    }

}
