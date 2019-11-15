package RSA;

import java.math.BigInteger;
import java.util.ArrayList;

public class EukledAlgorithms extends Main {
    private static final BigInteger ZERO = BigInteger.ZERO;
    private static final BigInteger ONE = BigInteger.ONE;

    public static  BigInteger eukled(BigInteger a , BigInteger b )
    {
        if( b.compareTo(a) > 0 )
        {
            BigInteger c = b;
            b = a;
            a = c;
        }
        while( !b.equals(BigInteger.ZERO) )
        {
            BigInteger b1 = b;
            b = a.subtract((a.divide(b)).multiply(b));
            a = b1;
        }
        return a;
    }

    public static BigInteger extendedEukled(BigInteger a ,BigInteger b )
    {
        ArrayList<BigInteger> k = new ArrayList<BigInteger>();
        ArrayList<BigInteger> rk = new ArrayList<BigInteger>();
        ArrayList<BigInteger> qk = new ArrayList<BigInteger>();
        ArrayList<BigInteger> xk = new ArrayList<BigInteger>();
        ArrayList<BigInteger> yk = new ArrayList<BigInteger>();
        k.add(ZERO);
        rk.add(a);
        qk.add(ZERO);
        xk.add(ONE);
        xk.add(ZERO);
        yk.add(ZERO);
        yk.add(ONE);

        if( b.compareTo(a) > 0 )
        {
            BigInteger c = b;
            b = a;
            a = c;
        }
        int i = 1;
        while( !b.equals(ZERO) )
        {
            qk.add(a.divide(b));
            BigInteger b1 = b;
            b = a.subtract((a.divide(b)).multiply(b));
            a = b1;
            rk.add(a);
            xk.add((xk.get(i).multiply(qk.get(i))).add(xk.get(i-1)));
            yk.add((yk.get(i).multiply(qk.get(i))).add(yk.get(i-1)));
            i++;
            k.add(ONE);
        }

        xk.remove(i);
        yk.remove(i);
        i--;
        //  System.out.println("LNKO: " + a + " = " + rk.get(0) + " * " + (i%2==0 ? "" : "-") + xk.get(i) + " + " + rk.get(1) + " * " + ((i+1)%2==0 ? "" : "-") + yk.get(i));
        if((i+1)%2==0)
            return yk.get(i).add(b);
        else
            return xk.get(i).negate().add(b);
    }
}
