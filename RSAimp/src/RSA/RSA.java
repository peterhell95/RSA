package RSA;

import java.math.BigInteger;
import java.util.Random;

public class RSA {
    private static final BigInteger ONE = BigInteger.ONE;
    private static final int[] aValues = { 2, 3 };
    private static final int bitSize = 1024;

    private BigInteger p;
    private BigInteger q ;
    private BigInteger n;
    private BigInteger phi;
    private BigInteger  e;
    private BigInteger d;

    public RSA() {
        p = randomPrimeGen();
        q = randomPrimeGen();
        n = p.multiply(q);
        phi = (p.subtract(ONE)).multiply(q.subtract(ONE));
        e =  randomGen();
        while(!EukledAlgorithms.eukled(e,phi).equals(ONE) || e.compareTo(phi) >= 0 )
            e =  randomGen();
        d = EukledAlgorithms.extendedEukled(e,phi);
    }
    private static BigInteger randomPrimeGen() {
        BigInteger r ;
        do{
            r = new BigInteger(bitSize, new Random());
            r = r.setBit(0).setBit(bitSize);
        }while(!MillerRabin.millerRabin(r,aValues.length));

        return r;
    }
    private static BigInteger randomGen() {
        BigInteger r ;
        r = new BigInteger(bitSize, new Random());
        r = r.setBit(0).setBit(bitSize);
        return r;
    }

    public BigInteger getP() {
        return p;
    }

    public BigInteger getQ() {
        return q;
    }

    public BigInteger getN() {
        return n;
    }

    public BigInteger getPhi() {
        return phi;
    }

    public BigInteger getE() {
        return e;
    }

    public BigInteger getD() {
        return d;
    }
}
