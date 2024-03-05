/*
 * Ant Group
 * Copyright (c) 2004-2024 All Rights Reserved.
 */
package org.example.server.impl;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;
import org.example.server.SimpleServer;

/**
 * @author iliesse
 * @version NIOServerImpl.java, v 0.1 2024年01月30日 16:31 iliesse
 */
public class NIOServerImpl implements SimpleServer {
    ServerSocketChannel serverChannel;
    Selector selector;

    public static void main(String[] args) throws IOException {
        new NIOServerImpl().start(6666);
    }

    @Override
    public void start(int port) throws IOException {
        selector = Selector.open();
        serverChannel = ServerSocketChannel.open();
        serverChannel.bind(new InetSocketAddress("127.0.0.1", port));
        serverChannel.configureBlocking(false);
        serverChannel.register(selector, SelectionKey.OP_ACCEPT);
        System.out.println("server start successfully on port: " + port);
        while (true) {
            selector.select();
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> keyIterator = selectionKeys.iterator();
            while (keyIterator.hasNext()){
                SelectionKey selectionKey = keyIterator.next();
                if (selectionKey.isAcceptable()) {
                    handleAcceptEvent();
                } else if (selectionKey.isReadable()) {
                    handle((SocketChannel) selectionKey.channel());
                }
                keyIterator.remove();
            }
        }
    }

    @Override
    public void stop() throws IOException {
        serverChannel.close();
    }

    private void handleAcceptEvent() throws IOException {
        SocketChannel socketChannel = serverChannel.accept();
        socketChannel.configureBlocking(false);
        socketChannel.register(selector, SelectionKey.OP_READ);
    }

    private void handle(SocketChannel clientChannel) throws IOException {
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        if (clientChannel.read(byteBuffer) != -1) {
            byteBuffer.flip();
            clientChannel.write(byteBuffer);
        }
    }
}