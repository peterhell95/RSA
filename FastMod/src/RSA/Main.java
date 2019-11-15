package RSA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        ArrayList<BigInteger> mods = new ArrayList<BigInteger>();

        System.out.println("A:");
        BigInteger a = new BigInteger(br.readLine());
        System.out.println("B:");
        BigInteger b = new BigInteger(br.readLine());
        System.out.println("C:");
        BigInteger c = new BigInteger(br.readLine());
        BigInteger counter = BigInteger.ONE;
        if(!a.divide(c).equals(BigInteger.ZERO))
        {
            counter = counter.multiply(BigInteger.TWO);
            mods.add(a.subtract(c.multiply(a.divide(c))));
        }
        else
        {
            counter = counter.multiply(BigInteger.TWO);
            mods.add(a);
        }
        int i = 0;
        while(counter.compareTo(b) <= 0)
        {
            mods.add((mods.get(i).multiply(mods.get(i))).subtract(c.multiply((mods.get(i).multiply(mods.get(i))).divide(c))));
            counter = counter.multiply(BigInteger.TWO);
            i++;
        }
        sb = sb.append(b.toString(2)).reverse();
        int counter2 = 1;
        for (int j = 0; j < sb.length(); j++) {
            if(sb.charAt(j)!= '0')
               counter2 = counter2*mods.get(j).intValue();
        }
        System.out.println(BigInteger.valueOf(counter2).mod(c));

    }
}
