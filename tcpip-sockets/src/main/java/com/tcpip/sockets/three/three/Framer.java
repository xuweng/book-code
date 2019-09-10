package com.tcpip.sockets.three.three;

import java.io.IOException;
import java.io.OutputStream;

/**
 * 成帧
 * 解决了接收端如何定位消息的首尾问题
 */
public interface Framer {
    void frameMsg(byte[] message, OutputStream out) throws IOException;

    byte[] nextMsg() throws IOException;
}
