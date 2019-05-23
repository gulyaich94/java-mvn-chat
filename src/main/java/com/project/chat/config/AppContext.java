package com.project.chat.config;

import com.project.chat.client.ChatClient;
import com.project.chat.client.ClientTCPConnectionListener;
import com.project.chat.client.view.ClientWindow;
import com.project.chat.client.view.SwingClientWindow;
import com.project.chat.network.TCPConnectionListener;
import com.project.chat.server.ChatServer;
import com.project.chat.server.ServerTCPConnectionListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.CopyOnWriteArrayList;

@Configuration
@ComponentScan("com.project.chat")
public class AppContext {

    @Bean
    public ChatClient chatClient() {
        return new ChatClient();
    }

    @Bean
    public ChatServer chatServer() {
        return new ChatServer();
    }

    @Bean
    public ClientWindow clientWindow() {
        return new SwingClientWindow();
    }

    @Bean
    public TCPConnectionListener clientTCPConnectionListener() {
        return new ClientTCPConnectionListener(clientWindow());
    }

    @Bean
    public TCPConnectionListener serverTCPConnectionListener() {
        return new ServerTCPConnectionListener(new CopyOnWriteArrayList<>());
    }
}
