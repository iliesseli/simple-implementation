/*
 * Ant Group
 * Copyright (c) 2004-2024 All Rights Reserved.
 */
package org.example.server.impl;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.example.server.SimpleServer;

/**
 * @author iliesse
 * @version SimpleConcurrentServer.java, v 0.1 2024年01月30日 10:23 iliesse
 */
public class ConcurrentServerImpl implements SimpleServer {
    private ExecutorService executor;
    private boolean         running;
    private ServerSocket serverSocket;

    public static void main(String[] args) throws IOException {
        SimpleServer server = new ConcurrentServerImpl();
        server.start(6666);
    }

    @Override
    public void start(int port) throws IOException {
        if (isRunning()) {
            return;
        }
        running = true;
        executor = Executors.newFixedThreadPool(10);
        serverSocket = new ServerSocket(port);
        System.out.println("server start successfully on port: " + port);
        while (isRunning()) {
            Socket clientSocket = serverSocket.accept();
            System.out.println("accept a connection from client:" + clientSocket.toString());
            executor.submit(new Handler(clientSocket));
        }
        System.out.println("main thread stop");
    }
    private boolean isRunning() {
        return running;
    }

    @Override
    public void stop() throws IOException {
        running = false;
        serverSocket.close();
        executor.shutdown();
    }

    static class Handler implements Runnable {
        byte[] buffer;
        Socket clientSocket;

        public Handler(Socket clientSocket) {
            buffer = new byte[1024];
            this.clientSocket = clientSocket;
        }

        @Override
        public void run()  {
            OutputStream out = null;
            InputStream in = null;
            try {
                out = clientSocket.getOutputStream();
                in = clientSocket.getInputStream();
                int byteRead;
                while (true) {
                    byteRead = in.read(buffer, 0, buffer.length);
                    System.out.println("byteRead = " + byteRead);
                    if (byteRead <= 0) {
                        break;
                    }
                    out.write(buffer, 0, byteRead);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    out.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                try {
                    in.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}