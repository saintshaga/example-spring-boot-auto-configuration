package cn.saintshaga.example.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.channels.SocketChannel;

public class NioClient {

    private static SocketChannel createSocketChannel(String host, int port) throws IOException {
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.configureBlocking(false);
        SocketAddress remote = new InetSocketAddress(host, port);
        socketChannel.bind(remote);
        return socketChannel;
    }

    public static void sendMessage(String message) throws IOException {

    }
}
