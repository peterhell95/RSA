package RSA;

import java.math.BigInteger;

public class ModPow {

    public static BigInteger fastModPow(BigInteger base, BigInteger exponent, final BigInteger modulo) {
        BigInteger result = BigInteger.ONE;
        while (exponent.compareTo(BigInteger.ZERO) > 0) {
            if (exponent.testBit(0))
                result = (result.multiply(base)).mod(modulo);
            exponent = exponent.shiftRight(1);
            base = (base.multiply(base)).mod(modulo);
        }
        return result.mod(modulo);
    }
}
