package eu.glowacki.tpo.jms.messages;
import java.math.BigInteger;

public class ArithmeticResponse implements IResponse {

    public final  BigInteger RESULT;

    public ArithmeticResponse(ArithmeticRequest request)
    {
        this.RESULT=Calculate(request);
    }

    public BigInteger Calculate(ArithmeticRequest request)
    {
        BigInteger result;
        switch (request.type){
            case(0): {result=request.COMPONENT2.add(request.COMPONENT1); break;}
            case (1):{ result=request.COMPONENT2.subtract(request.COMPONENT1); break;}
            case (2): { result=request.COMPONENT2.multiply(request.COMPONENT1); break;}
            case (3): result= request.COMPONENT2.divide(request.COMPONENT1);
            break;
            default:
                throw new IllegalStateException("Unexpected value: " + request.type);
        }
        return result;
    }
}
