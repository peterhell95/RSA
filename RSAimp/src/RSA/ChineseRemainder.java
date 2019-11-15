package RSA;

import java.math.BigInteger;

public class ChineseRemainder {
    public static final BigInteger ONE = BigInteger.ONE;


    static BigInteger crt(BigInteger P,BigInteger Q , BigInteger D , BigInteger c) {

        BigInteger dP = D.mod(P.subtract(ONE));
        BigInteger dQ = D.mod(Q.subtract(ONE));
        BigInteger qInv = Q.modPow(ONE.negate(),P);
        BigInteger m1 = c.modPow(dP,P);
        BigInteger m2 = c.modPow(dQ,Q);
        BigInteger h = qInv.multiply(m1.subtract(m2)).mod(P);
        BigInteger decryptedMessage = m2.add(h.multiply(Q));
        return decryptedMessage;
    }
}
