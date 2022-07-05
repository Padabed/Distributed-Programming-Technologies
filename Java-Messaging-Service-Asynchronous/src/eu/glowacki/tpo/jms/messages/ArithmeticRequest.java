package eu.glowacki.tpo.jms.messages;
import org.apache.activemq.command.ActiveMQMapMessage;

import java.io.Serializable;
import java.math.BigInteger;

public class ArithmeticRequest implements IRequest {

    public final  BigInteger COMPONENT1;
    public final  BigInteger COMPONENT2;
    public final int type;

    public ArithmeticRequest(BigInteger x, BigInteger y, int type)
    {
        COMPONENT1=x;
        COMPONENT2=y;
        this.type=type;
    }
}
