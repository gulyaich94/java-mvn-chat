package com.project.chat.client.view;

import com.project.chat.network.TCPConnection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SwingClientWindow extends JFrame implements ActionListener, ClientWindow {

    private TCPConnection connection;
    private static final int WIDTH = 600;
    private static final int HEIGHT = 400;
    private final JTextArea log = new JTextArea();
    private final JTextField fieldLogin = new JTextField("login");
    private final JTextField fieldInput = new JTextField();

    @Override
    public void runChat(TCPConnection connection) {
        this.connection = connection;
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                initWindow();
            }
        });
    }

    private void initWindow() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(WIDTH, HEIGHT);
        setLocationRelativeTo(null);
        setVisible(true);

        log.setEditable(false);
        log.setLineWrap(true);
        add(log, BorderLayout.CENTER);
        fieldInput.addActionListener(this);
        add(fieldInput, BorderLayout.SOUTH);
        add(fieldLogin, BorderLayout.NORTH);
    }

    @Override
    public synchronized void printMsg(String msg) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                log.append(msg + "\n");
                log.setCaretPosition(log.getDocument().getLength());
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String msg = fieldInput.getText();
        if (!"".equals(msg)) {
            fieldInput.setText(null);
            connection.sendString(fieldLogin.getText() + ": " + msg);
        }
    }
}
