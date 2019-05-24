package com.project.chat.client;

import com.project.chat.client.view.ClientWindow;
import com.project.chat.network.TCPConnection;
import com.project.chat.network.TCPConnectionListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ClientTCPConnectionListener implements TCPConnectionListener {

    private ClientWindow clientWindow;

    @Autowired
    public ClientTCPConnectionListener(ClientWindow clientWindow) {
        this.clientWindow = clientWindow;
    }

    @Override
    public void onConnectionReady(TCPConnection tcpConnection) {
        clientWindow.printMsg("Connection ready...");
    }

    @Override
    public void onReceiveString(TCPConnection tcpConnection, String value) {
        clientWindow.printMsg(value);
    }

    @Override
    public void onDisconnect(TCPConnection tcpConnection) {
        clientWindow.printMsg("Connection close");
    }

    @Override
    public void onException(TCPConnection tcpConnection, Exception e) {
        clientWindow.printMsg("Connection exception " + e);
    }
}
