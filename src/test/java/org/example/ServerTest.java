/*
 * Ant Group
 * Copyright (c) 2004-2024 All Rights Reserved.
 */
package org.example;

import java.io.IOException;
import org.example.client.impl.IOClientImpl;
import org.example.client.impl.NIOClientImpl;
import org.junit.Test;

/**
 * @author iliesse
 * @version ServerTest.java, v 0.1 2024年01月30日 14:10 iliesse
 */
public class ServerTest {
    @Test
    public void simpleServerTest() throws IOException {
        IOClientImpl client = new IOClientImpl();
        client.startConnection(6666);
        assert "hello".equals(client.sendMessage("hello"));
        client.stopConnection();
    }

    /**
     * 测试NIO服务器
     *
     * @throws IOException
     */
    @Test
    public void NIOServerTest() throws IOException {
        // 创建NIO客户端实例
        NIOClientImpl client = new NIOClientImpl();
        // 连接到指定端口
        client.startConnection(6666);
        // 发送消息并断言返回值是否为"hello"
        System.out.println(client.sendMessage("hello"));
        assert "hello".equals(client.sendMessage("hello"));
        // 断开连接
        client.stopConnection();
    }
}