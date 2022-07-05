import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.*;


public class Server {
    final String HOSTNAME = "127.0.0.1";
    final int PORT = 8511;
    private ServerSocketChannel ssc = null;
    private Selector selector = null;
    ByteBuffer buffer;
    SelectionKey key;

    public Server() throws IOException {
        register();
    }

    public void register() throws IOException {
        ssc = ServerSocketChannel.open();
        ssc.configureBlocking(false);
        ssc.socket().bind(new InetSocketAddress(HOSTNAME, PORT));
        selector = Selector.open();
        ssc.register(selector, SelectionKey.OP_ACCEPT);
        System.out.println("Server is ready");
        handleConnections();
    }

    public void handleConnections() {
        boolean serverIsRunning = true;
        while (serverIsRunning) {// Infinite server loop
            try {
                selector.select();
                Set keys = selector.selectedKeys();
                Iterator iter = keys.iterator();
                while (iter.hasNext()) {
                    key = (SelectionKey) iter.next();
                    iter.remove();
                    if (key.isAcceptable()) { // an incoming connection (i.e. TCP handshake)
                        System.out.println("Acceptable");
                        SocketChannel cc = ssc.accept();
                        cc.configureBlocking(false);
                        cc.register(selector, SelectionKey.OP_READ);
                        continue;
                    }

                    if (key.isReadable()) {
                        System.out.println("Readable");
                        SocketChannel cc = (SocketChannel) key.channel();
                        handleReading(cc);
                        continue;
                    }
                }
            } catch (Exception exc) {
                exc.printStackTrace();
                continue;
            }
        }
    }

    private void handleReading(SocketChannel sc) throws IOException, InterruptedException {
        if (!sc.isOpen()) {
            return;                    // if channel is closed there is nothing we could do
        }
        buffer = ByteBuffer.allocate(1024);
        sc.read(buffer);
        String data = new String(buffer.array()).trim();
        System.out.println(data + " arrived from Client");
        if (data.contains("echo")) {
            System.out.println("Echoing message back to the Client");
            buffer.clear();
            buffer.flip();
            sc.write(buffer);
        }

        if (data.contains("add")) {
            String buff = data.replaceAll("\\D+", ""); //extract digits only
            List<String> lista = Arrays.asList(buff.split(""));
            int sum = 0;
            for (String s : lista) {
                sum += Integer.valueOf(s);
            }
            buffer.clear();
            String odp = "the sum is " + sum;
            buffer.put((odp).getBytes());
            buffer.flip();
            sc.write(buffer);
            System.out.println("Sent " + sum);
        }

        if (data.contains("quit")) {
            sc.close();
            System.out.println("Connection closed...");
        }
    }

    public static void main(String[] args) throws IOException {
        new Server();
    }
}
