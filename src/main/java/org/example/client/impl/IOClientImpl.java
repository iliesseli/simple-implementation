/*
 * Ant Group
 * Copyright (c) 2004-2024 All Rights Reserved.
 */
package org.example.client.impl;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import org.example.client.SimpleClient;

/**
 * @author iliesse
 * @version SimpleClient.java, v 0.1 2024年01月30日 13:56 iliesse
 */
public class IOClientImpl implements SimpleClient {
    private Socket socket;
    private InputStream in;
    private OutputStream out;
    public  void startConnection(int port) throws IOException {
        socket = new Socket("127.0.0.1", port);
        in = socket.getInputStream();
        out  = socket.getOutputStream();
    }

    public String sendMessage(String message) throws IOException {
        out.write(message.getBytes());
        byte[] buffer = new byte[message.getBytes().length];
        in.read(buffer);

        return new String(buffer);
    }

    public void stopConnection() throws IOException {
        socket.close();
        in.close();
        out.close();
    }
}