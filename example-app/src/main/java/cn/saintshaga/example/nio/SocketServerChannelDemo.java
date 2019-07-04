package cn.saintshaga.example.nio;

import lombok.extern.slf4j.Slf4j;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

@Slf4j
public class SocketServerChannelDemo {

    public static void main(String[] args) throws Exception {
        try (ServerSocketChannel serverSocketChannel = ServerSocketChannel.open()) {
            serverSocketChannel.socket().bind(new InetSocketAddress("127.0.0.1", 1234));
            SocketChannel socketChannel = serverSocketChannel.accept();
            log.info("connected");
            ByteBuffer buffer = ByteBuffer.allocate(1000);
            socketChannel.read(buffer);
            log.info("Get from client: {}", new String(buffer.array()));
        }

    }
}
