package eu.glowacki.tpo.jms.messages;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Random;

public class RandomRequest implements IRequest {
/*
    private static final int LENGTH = 20;
    private static final Random RANDOM = new Random();
    private static final BigInteger RANGE_LIMIT = new BigInteger("3000");

    public final BigInteger range;

    public RandomRequest()
    {
        range = range();
    }

    private static BigInteger range() {
        byte[] value = new byte[LENGTH];
        RANDOM.nextBytes(value);
        BigInteger random = new BigInteger(value);
        return random.mod(RANGE_LIMIT);
    }*/
  //FAILED OT BUILD BODY FORM BYTES
public int range;
public RandomRequest()
{
    Random rnd = new Random();
    range = rnd.nextInt(3000);
}
}
