/*
 * Ant Group
 * Copyright (c) 2004-2024 All Rights Reserved.
 */
package org.example.client;

import java.io.IOException;

/**
 * @author iliesse
 * @version SimpleClient.java, v 0.1 2024年01月30日 17:02 iliesse
 */
public interface SimpleClient {
    void startConnection(int port) throws IOException;

    String sendMessage(String message) throws IOException;

    void stopConnection() throws IOException;
}