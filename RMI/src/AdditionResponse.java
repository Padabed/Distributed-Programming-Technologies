import java.io.Serializable;
import java.math.BigInteger;

public class AdditionResponse implements Serializable {
    public final BigInteger sum;

    public AdditionResponse(BigInteger x, BigInteger y) {
        sum = responseAddition(x, y);
    }

    public BigInteger responseAddition(BigInteger x, BigInteger y) {
        if (x != null && y != null) return x.add(y);
        else if (x != null && y == null) return x;
        else if (y != null && x == null) return y;
        else return null;
    }

    @Override
    public String toString() {
        return "AdditionResponse{" +
                "sum=" + sum +
                '}';
    }
}
