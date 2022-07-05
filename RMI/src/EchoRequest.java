import java.io.Serializable;

public class EchoRequest implements Serializable {

    public final String messageToEcho;

    public EchoRequest(String m) {
        messageToEcho=m;
    }

    @Override
    public String toString() {
        return "EchoRequest{" +
                "messageToEcho='" + messageToEcho + '\'' +
                '}';
    }


}
