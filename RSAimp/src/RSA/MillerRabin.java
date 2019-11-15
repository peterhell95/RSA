package RSA;

import java.math.BigInteger;

public class MillerRabin extends Main  {
    private static final BigInteger ZERO = BigInteger.ZERO;
    private static final BigInteger ONE = BigInteger.ONE;
    private static final BigInteger TWO = BigInteger.valueOf(2);
    private static final int[] aValues = { 2, 3 };


    public static boolean millerRabin(BigInteger n)
    {
        if(n.equals(BigInteger.ONE))
            return true;

        BigInteger s = BigInteger.ZERO;
        BigInteger d = n.subtract(BigInteger.ONE);
        BigInteger a;
        for(int k = 2; k < 1000 ; k++)
        {
            a = BigInteger.valueOf(k);
            while(d.mod(BigInteger.TWO).equals(BigInteger.ZERO))
            {
                s = s.add(BigInteger.ONE);
                d = d.divide(BigInteger.TWO);
            }
            BigInteger cons = BigInteger.ONE;
            for(int i = 0 ; i < s.intValue();i++)
            {

                BigInteger z = BigInteger.TWO.pow(i).multiply(d);
                BigInteger eredmeny  = a.pow(z.intValue()).mod(n);
                if(cons.equals(eredmeny))
                {
                    return true;
                }
                if (cons.equals(BigInteger.ONE))
                    cons = cons.negate();
            }
        }
        return false;
    }

    public static boolean testPr(BigInteger n, BigInteger a, int s, BigInteger d) {
        for (int i = 0; i < s; i++) {
            BigInteger exp = TWO.pow(i);
            exp = exp.multiply(d);
            BigInteger res = a.modPow(exp, n);
            if (res.equals(n.subtract(ONE)) || res.equals(ONE)) {
                return true;
            }
        }
        return false;
    }

    public static boolean millerRabin(BigInteger n, int numValues) {
        BigInteger d = n.subtract(ONE);
        int s = 0;
        while (d.mod(TWO).equals(ZERO)) {
            s++;
            d = d.divide(TWO);
        }
        for (int i = 0; i < numValues; i++) {
            BigInteger a = BigInteger.valueOf(aValues[i]);
            if (!testPr(n, a, s, d)) {
                return false;
            }
        }
        return true;
    }
}
