package RSA;

import java.math.BigInteger;
import java.util.ArrayList;

public class Main  {
    private static final String Message = "Nagyon szép a táj debrecenben :)";
    private static final BigInteger ZERO = BigInteger.ZERO;
    private static RSA declaration()
    {
        RSA rsa;
        do {
            rsa = new RSA();
        }while(rsa.getD().compareTo(ZERO) < 0);
        return rsa;
    }
    private static void getRSA(ArrayList<RSA> rsa)
    {
        System.out.println("P: "  + rsa.get(0).getP());
        System.out.println("Q: "  + rsa.get(0).getQ());
        System.out.println("N: "  + rsa.get(0).getN());
        System.out.println("Phi(n): "  + rsa.get(0).getPhi());
        System.out.println("E: "  + rsa.get(0).getE());
        System.out.println("D: "  + rsa.get(0).getD());
    }
    private static BigInteger encrypt(BigInteger num, BigInteger n, BigInteger e) {
        BigInteger encrypt = ModPow.fastModPow(num,e,n);
        return encrypt;
    }

    private static BigInteger decrypt(BigInteger enc, BigInteger n, BigInteger d) {
        BigInteger decrypt = ModPow.fastModPow(enc,d,n);
        return decrypt;
    }

    private static BigInteger decryptWithCRT(BigInteger P,BigInteger Q , BigInteger D , BigInteger c) {
        BigInteger decrypt = ChineseRemainder.crt(P,Q,D,c);
        return decrypt;
    }
    private static String  BigIntToString(BigInteger m) {
        byte[] bytes = m.toByteArray();
        String decryptedMessage = new String(bytes);
        return decryptedMessage;
    }



    public static void main(String[] args) {

        /** Inicializálás */

        ArrayList<RSA> rsa = new ArrayList<>();
        rsa.add(declaration()); /** Értékadás */

        final BigInteger P = rsa.get(0).getP();
        final BigInteger Q = rsa.get(0).getQ();
        final BigInteger D = rsa.get(0).getD();
        final BigInteger N = rsa.get(0).getN();
        final BigInteger E = rsa.get(0).getE();
       // getRSA(rsa);  /** Paraméterek kiíratása */


        /** Titkositás **/

        BigInteger m = new BigInteger(Message.getBytes());
        BigInteger c = encrypt(m,N,E);
        System.out.println("Üzenet: "  + Message);
        System.out.println("Üzenet bytesorozata: "  + m);
        System.out.println("c: "  + c);

        /** Visszafejtés **/

      //  BigInteger result =decrypt(c,N,D);
      //  System.out.println("Visszafejtett üzenet byte sorozata: "  + result);
        // System.out.println("Visszafejtett üzenet: "  + BigIntToString(result));

        /** Visszafejtés Kínai maradéktétellel **/

        BigInteger result2 = decryptWithCRT(P,Q,D,c);
        System.out.println("Visszafejtett üzenet a kínai maradéktétel segítségével: "  + result2 );
        System.out.println("Visszafejtett üzenet CRT segítségével: "  + BigIntToString(result2));

    }
}