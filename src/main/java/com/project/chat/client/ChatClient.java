package com.project.chat.client;

import com.project.chat.client.view.ClientWindow;
import com.project.chat.client.view.SwingClientWindow;
import com.project.chat.network.TCPConnection;

import java.io.IOException;

public class ChatClient {

    private static final String IP_ADDR = "10.113.250.60";
    private static final int PORT = 8189;
    private TCPConnection connection;
    private ClientWindow clientWindow;

    public ChatClient() {
        clientWindow = new SwingClientWindow();
        try {
            connection = new TCPConnection(new ClientTCPConnectionListener(clientWindow), IP_ADDR, PORT);
        } catch (IOException e) {
            clientWindow.printMsg("Connection exception " + e);
        }
    }

    public static void main(String[] args) {
        new ChatClient().startClientWindowChat();
    }

    private void startClientWindowChat() {
        clientWindow.runChat(connection);
    }
}
