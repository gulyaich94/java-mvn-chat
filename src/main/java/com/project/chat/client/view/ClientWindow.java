package com.project.chat.client.view;

import com.project.chat.network.TCPConnection;

public interface ClientWindow {

    void runChat(TCPConnection connection);
    void initWindow();
    void printMsg(String msg);
}
