import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class Client {
    SocketChannel channel;
    String server = "127.0.0.1";
    int port = 8511;
    boolean continueConversation = true;

    Client() throws IOException, InterruptedException {
        try {
            channel = SocketChannel.open();
            //channel.configureBlocking(false);
            channel.connect(new InetSocketAddress(server, port));
            System.out.print("Connecting ...");
            while (!channel.finishConnect()) {
                System.out.println("wait i am connecting");
            }
        } catch (UnknownHostException exc) {
            System.err.println("Uknown host " + server);
            // ...
        } catch (Exception exc) {
            exc.printStackTrace();
            // ...
        }
        System.out.println("\nConnected");
        while (continueConversation) {
            writeMessage();
            Thread.sleep(2000);
        }
    }

    public void writeMessage() throws IOException {
        String message = "add 2 3 4";
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        buffer.put(message.getBytes());
        buffer.flip();
        System.out.println("1. Sending message: " + message);
        channel.write(buffer);
        buffer.clear();
        channel.read(buffer);
        buffer.flip();
        String response = new String(buffer.array()).trim();
        System.out.println("2. Received response: " + response);
        buffer.clear();
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        new Client();
    }
}
