package eu.glowacki.tpo.jms.messages;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Random;

public class RandomResponse implements IResponse {
/*
    private static final Random RANDOM = new Random();
    private static final int LENGTH = 20;

    public final BigInteger result;

    public RandomResponse(RandomRequest request) {
        result = result(request.range);
    }

    private static BigInteger result(BigInteger range) {
        byte[] value = new byte[LENGTH];
        RANDOM.nextBytes(value);
        BigInteger random = new BigInteger(value);
        return random.mod(range);
    }
    */
public int result;
public RandomResponse(RandomRequest request) {
    Random rnd = new Random();
    result = rnd.nextInt(request.range);
}
}
