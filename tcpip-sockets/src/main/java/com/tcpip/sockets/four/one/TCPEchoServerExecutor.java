package com.tcpip.sockets.four.one;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.logging.Logger;

/**
 * 真正线程池;动态线程池
 * 系统负载增加---------扩展线程池大小
 * 系统负载减少---------减轻线程池大小
 * 一个线程死了----------->创建新的线程
 */
public class TCPEchoServerExecutor {

    public static void main(String[] args) throws IOException {

        if (args.length != 1) { // Test for correct # of args
            throw new IllegalArgumentException("Parameter(s): <Port>");
        }

        int echoServPort = Integer.parseInt(args[0]); // Server port

        // Create a server socket to accept client connection requests
        ServerSocket servSock = new ServerSocket(echoServPort);

        Logger logger = Logger.getLogger("practical");

        Executor service = Executors.newCachedThreadPool(); // Dispatch svc

        // Run forever, accepting and spawning a thread for each connection
        while (true) {
            Socket clntSock = servSock.accept(); // Block waiting for connection
            service.execute(new EchoProtocol(clntSock, logger));
        }
        /* NOT REACHED */
    }
}
