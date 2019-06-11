package cn.saintshaga.example.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class ReactorServer {

    private static Selector selector;

    static {
        try {
            selector = Selector.open();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static ServerSocketChannel createSocketChannel(String host, int port) throws IOException {
        ServerSocketChannel socketChannel = ServerSocketChannel.open();
        socketChannel.configureBlocking(false);
        SocketAddress remote = new InetSocketAddress(host, port);
        socketChannel.bind(remote);
        socketChannel.register(selector, SelectionKey.OP_ACCEPT);
        return socketChannel;
    }

    public static void main(String[] args) throws IOException {
        createSocketChannel("localhost", 1000);
        createSocketChannel("localhost", 1001);
        while(selector.select() > 0) {
            Set<SelectionKey> keys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = keys.iterator();
            while(iterator.hasNext()) {
                SelectionKey key = iterator.next();
                iterator.remove();
                if(key.isAcceptable()) {
                    ServerSocketChannel serverSocketChannel = (ServerSocketChannel) key.channel();
                    SocketChannel socketChannel = serverSocketChannel.accept();
                    socketChannel.configureBlocking(false);
                    socketChannel.register(selector, SelectionKey.OP_READ);
                    System.out.println("accept socket");
                } else if(key.isReadable()) {
                    SocketChannel socketChannel = (SocketChannel) key.channel();
                    ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                    int count = socketChannel.read(byteBuffer);
                    if(count <= 0) {
                        socketChannel.close();
                        key.cancel();
                    } else {
                        System.out.println(new String(byteBuffer.array()));
                    }
                }
            }
        }

    }
}
