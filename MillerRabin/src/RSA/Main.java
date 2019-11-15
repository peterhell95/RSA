package RSA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class Main {

    public static boolean miller(BigInteger n)
    {
        if(n.equals(BigInteger.ONE))
            return true;

        BigInteger s = BigInteger.ZERO;
        BigInteger d = n.subtract(BigInteger.ONE);
        BigInteger a;
        for(int k = 2; k < 5 ; k++)
        {
            a = BigInteger.valueOf(k);
            while(d.mod(BigInteger.TWO).equals(BigInteger.ZERO))
            {
                s = s.add(BigInteger.ONE);
                d = d.divide(BigInteger.TWO);
            }
            // System.out.println("s: " + s + " d: " + d);
            BigInteger cons = BigInteger.ONE;
            for(int i = 0 ; i < s.intValue();i++)
            {
                BigInteger z = BigInteger.TWO.pow(i).multiply(d);
                BigInteger eredmeny  = a.pow(z.intValue()).mod(n);
                if(cons.equals(eredmeny))
                {
                    return true;
                    // System.out.println(eredmeny + " != " + cons);
                }
                if (cons.equals(BigInteger.ONE))
                    cons = cons.negate();
            }
        }
        return false;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("n:");
        BigInteger n = new BigInteger(br.readLine());
        if(miller(n))
            System.out.println("PRIM");
        else
            System.out.println("Osszetett szam");

    }
}
