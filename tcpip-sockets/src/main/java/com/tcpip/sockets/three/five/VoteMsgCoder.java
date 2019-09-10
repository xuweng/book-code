package com.tcpip.sockets.three.five;

import java.io.IOException;

/**
 * 编码和解码
 */
public interface VoteMsgCoder {
    byte[] toWire(VoteMsg msg) throws IOException;

    VoteMsg fromWire(byte[] input) throws IOException;
}
