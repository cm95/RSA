import java.math.BigInteger;
import java.util.Random;

/**
 * @author Christopher Millar
 */
public class Key_Generation {
    public static void main(String[] args) {
        Random rand = new Random();
        BigInteger p, q, e, d;

        p = new BigInteger("0");
        p = BigInteger.valueOf(rand.nextLong());

        q = new BigInteger("0");
        q = BigInteger.valueOf(rand.nextLong());

        BigInteger check = new BigInteger("1000000000");

        while (!p.isProbablePrime(6)) {
            p = BigInteger.valueOf(rand.nextLong());
            if (p.compareTo(check) <= 0) {
                p = BigInteger.ZERO;
            }
            while (!q.isProbablePrime(6)) {
                q = BigInteger.valueOf(rand.nextLong());
                if (q.compareTo(check) <= 0 || p.equals(q)) {
                    q = BigInteger.ZERO;
                }
            }
        }

        BigInteger n = p.multiply(q);
        BigInteger p1 = p.subtract(BigInteger.ONE);
        BigInteger q1 = q.subtract(BigInteger.ONE);
        BigInteger lambdaN = lcm(p1, q1);

        e = new BigInteger("0");
        while (!e.isProbablePrime(6) || e.compareTo(lambdaN) >= 0 || !(gcd(e, lambdaN).compareTo(BigInteger.ONE) == 0) || e.compareTo(BigInteger.ZERO) <= 0) {
            e = BigInteger.valueOf(rand.nextLong());
        }
        d = new BigInteger("0");
        d = e.modInverse(lambdaN);

        System.out.println("totient: " + lambdaN + " e: " + e + " d: " + d);


    }

    public static BigInteger gcd(BigInteger p1, BigInteger q1) {
        if (q1.equals(BigInteger.ZERO)) {
            return p1;
        } else {
            return gcd(q1, p1.mod(q1));
        }
    }

    public static BigInteger lcm(BigInteger p1, BigInteger q1) {
        return p1.multiply(q1).divide(gcd(p1, q1));
    }
}