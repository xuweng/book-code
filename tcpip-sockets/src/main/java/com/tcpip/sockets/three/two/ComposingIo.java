package com.tcpip.sockets.three.two;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * 组合输入输出流
 */
public class ComposingIo {
    private String server;

    /**
     * 先写入:DataOutputStream--------->BufferedOutputStream---------------->OutputStream
     * 读取:DataInputStream<-----------BufferedInputStream<----------------InputStream先读取
     */
    public void composingIo() throws IOException {
        int port = 0;
        Socket socket = new Socket(server, port);
        DataOutputStream out = new DataOutputStream(
                new BufferedOutputStream(socket.getOutputStream()));
    }
}
