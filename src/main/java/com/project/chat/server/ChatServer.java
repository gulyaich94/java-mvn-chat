package com.project.chat.server;

import com.project.chat.network.TCPConnection;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class ChatServer {

    private static final int SERVER_PORT = 8189;
    private List<TCPConnection> connections = new CopyOnWriteArrayList<>();

    private ChatServer() {
        System.out.println("Server running...");
        try (ServerSocket serverSocket = new ServerSocket(SERVER_PORT)) {
            while (true) {
                try {
                    new TCPConnection(new ServerTCPConnectionListener(connections), serverSocket.accept());
                } catch (IOException e) {
                    System.out.println("TCPConnection exception: " + e);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        new ChatServer();
    }

}
