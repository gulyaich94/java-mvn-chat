package com.project.chat.server;

import com.project.chat.network.TCPConnection;
import com.project.chat.network.TCPConnectionListener;

import java.util.List;

public class ServerTCPConnectionListener implements TCPConnectionListener {

    private List<TCPConnection> connections;

    public ServerTCPConnectionListener(List<TCPConnection> connections) {
        this.connections = connections;
    }

    @Override
    public synchronized void onConnectionReady(TCPConnection tcpConnection) {
        connections.add(tcpConnection);
        sendToAllConnections("Client connected: " + tcpConnection);
    }

    @Override
    public synchronized void onReceiveString(TCPConnection tcpConnection, String value) {
        sendToAllConnections(value);
    }

    @Override
    public synchronized void onDisconnect(TCPConnection tcpConnection) {
        connections.remove(tcpConnection);
        sendToAllConnections("Client disconnected: " + tcpConnection);
    }

    @Override
    public synchronized void onException(TCPConnection tcpConnection, Exception e) {
        System.out.println("TCPConnection exception: " + e);
    }

    private void sendToAllConnections(String value) {
        System.out.println(value);
        for (TCPConnection connection : connections) {
            connection.sendString(value);
        }
    }
}
