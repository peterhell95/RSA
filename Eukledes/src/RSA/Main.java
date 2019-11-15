package RSA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws IOException {
        ArrayList<BigInteger> k = new ArrayList<BigInteger>();
        ArrayList<BigInteger> rk = new ArrayList<BigInteger>();
        ArrayList<BigInteger> qk = new ArrayList<BigInteger>();
        ArrayList<BigInteger> xk = new ArrayList<BigInteger>();
        ArrayList<BigInteger> yk = new ArrayList<BigInteger>();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("a:");
        BigInteger a = new BigInteger(br.readLine());
        System.out.println("b:");
        BigInteger b = new BigInteger(br.readLine());

        rk.add(a);
        qk.add(BigInteger.ZERO);
        xk.add(BigInteger.ONE);
        xk.add(BigInteger.ZERO);
        yk.add(BigInteger.ZERO);
        yk.add(BigInteger.ONE);

        if( b.compareTo(a) > 0 )
        {
            BigInteger c = b;
            b = a;
            a = c;
        }
        int i = 1;
        while( !b.equals(BigInteger.ZERO) )
        {
            qk.add(a.divide(b));
            BigInteger b1 = b;
            b = a.subtract((a.divide(b)).multiply(b));
            a = b1;
            rk.add(a);
            xk.add((xk.get(i).multiply(qk.get(i))).add(xk.get(i-1)));
            yk.add((yk.get(i).multiply(qk.get(i))).add(yk.get(i-1)));
            i++;
        }

        xk.remove(i);
        yk.remove(i);
        i--;
        System.out.println("LNKO: " + a + " = " + rk.get(0) + " * " + (i%2==0 ? "" : "-") + xk.get(i) + " + " + rk.get(1) + " * " + ((i+1)%2==0 ? "" : "-") + yk.get(i));
    }
}
