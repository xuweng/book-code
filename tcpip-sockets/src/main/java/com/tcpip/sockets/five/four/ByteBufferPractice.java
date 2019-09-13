package com.tcpip.sockets.five.four;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.CharBuffer;
import java.nio.charset.*;

/**
 * ByteBuffer练习
 */
public class ByteBufferPractice {
    public void practice() {
        ByteBuffer buffer = ByteBuffer.allocate(4);
        buffer.putShort((short) 1);
        buffer.order(ByteOrder.LITTLE_ENDIAN);
        buffer.putShort((short) 1);
// Predict the byte values for buffer and test your prediction

        // Start with buffer in unknown state
        buffer.clear(); // Prepare buffer for input, ignoring existing state
//        channel.read(buffer); // Read new data into buffer, starting at first element

        // ... put data in buffer with put() or read() ...
        buffer.flip(); // Set position to 0, limit to old position
        while (buffer.hasRemaining()) {
            // Write buffer data from the first element up to limit
        }
//            channel.write(buffer);

        // Start with buffer ready for writing
        while (buffer.hasRemaining()) // Write all data to network
        {
//            networkChannel.write(buffer);
        }
        buffer.rewind(); // Reset buffer to write again
        while (buffer.hasRemaining()) // Write all data to logger
        {
//            loggerChannel.write(buffer);
        }

        // Start with buffer ready for reading
        while (channel.read(buffer) != -1) {
            buffer.flip();
            channel.write(buffer);
            buffer.compact();
        }
        while (buffer.hasRemaining()) {
            channel.write(buffer);
        }

        // Start with buffer ready for writing
        ByteBuffer logBuffer = buffer.duplicate();
        while (buffer.hasRemaining()) // Write all data to network
        {
            networkChannel.write(buffer);
        }
        while (logBuffer.hasRemaining()) // Write all data to logger
        {
            loggerChannel.write(buffer);
        }

    }

    public void encode() throws CharacterCodingException {
        Charset charSet = Charset.forName("US-ASCII");
        CharsetEncoder encoder = charSet.newEncoder();
        ByteBuffer buffer = encoder.encode(CharBuffer.wrap("Hi mom"));

        CharsetDecoder decoder = charSet.newDecoder();
        CharBuffer cBuf = decoder.decode(buffer);

        encoder.reset();
        if (encoder.encode(CharBuffer.wrap("Hi "), buffer, false) == CoderResult.OVERFLOW) {
// ... deal with lack of space in buffer ...
        }
        if (encoder.encode(CharBuffer.wrap("Mom"), buffer, true) == CoderResult.OVERFLOW) {
// ... ditto ...
        }
        encoder.flush(buffer);
    }
}
