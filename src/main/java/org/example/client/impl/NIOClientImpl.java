/*
 * Ant Group
 * Copyright (c) 2004-2024 All Rights Reserved.
 */
package org.example.client.impl;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import org.example.client.SimpleClient;

/**
 * @author iliesse
 * @version NIOClient.java, v 0.1 2024年01月30日 16:58 iliesse
 */
public class NIOClientImpl implements SimpleClient {
    SocketChannel client;

    @Override
    public void startConnection(int port) throws IOException {
        client = SocketChannel.open(new InetSocketAddress("127.0.0.1", port));
    }

    @Override
    public String sendMessage(String message) throws IOException {
        client.write(ByteBuffer.wrap(message.getBytes()));
        ByteBuffer buffer = ByteBuffer.allocate(message.getBytes().length);
        client.read(buffer);
        return new String(buffer.array()).trim();
    }

    @Override
    public void stopConnection() throws IOException {

    }
}