import java.io.Serializable;
import java.math.BigInteger;

public class AdditionRequest implements Serializable {
    public final BigInteger parm1;
    public final BigInteger parm2;

    public AdditionRequest(BigInteger parm1, BigInteger parm2) {
        this.parm1 = parm1;
        this.parm2 = parm2;
    }

    @Override
    public String toString() {
        return "AdditionRequest{" +
                "parm1=" + parm1 +
                ", parm2=" + parm2 +
                '}';
    }
}
