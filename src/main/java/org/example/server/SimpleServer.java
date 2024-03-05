/*
 * Ant Group
 * Copyright (c) 2004-2024 All Rights Reserved.
 */
package org.example.server;

import java.io.IOException;

/**
 * @author iliesse
 * @version SimpleServer.java, v 0.1 2024年01月30日 10:24 iliesse
 */
public interface SimpleServer {
    void start(int port) throws IOException;

    void stop() throws IOException;
}