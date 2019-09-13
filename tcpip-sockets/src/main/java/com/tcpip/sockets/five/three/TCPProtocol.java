package com.tcpip.sockets.five.three;

import java.io.IOException;
import java.nio.channels.SelectionKey;

/**
 * 接受、读、写
 */
public interface TCPProtocol {
    void handleAccept(SelectionKey key) throws IOException;

    void handleRead(SelectionKey key) throws IOException;

    void handleWrite(SelectionKey key) throws IOException;
}
