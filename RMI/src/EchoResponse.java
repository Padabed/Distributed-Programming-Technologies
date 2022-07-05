import java.io.Serializable;

public class EchoResponse implements Serializable {
    public final String echoedMessage;

    public EchoResponse(String Message) {
        this.echoedMessage = performEchoing(Message);
    }

    public String performEchoing(String s){
        return "ECHO "+s;

    }
    @Override
    public String toString() {
        return "EchoResponse{" +
                "echoedMessage='" + echoedMessage + '\'' +
                '}';
    }
}
