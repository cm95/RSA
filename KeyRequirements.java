import java.math.BigInteger;
import java.util.Random;

/**
 * The KeyRequirements class creates the components needed for RSA keys.
 * @author Christopher Millar
 */
public class KeyRequirements {
    private BigInteger p, q, n, lambdaN;

    KeyRequirements(){
        setPrimeNumbers();
        setTotient();
    }

    /**
     * Creates prime numbers p, and q. Calculates n from p and q
     */
    private void setPrimeNumbers() {
        Random rand = new Random();
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
        n = p.multiply(q);
    }

    /**
     * Computes the totient of the product as n = lcm(p-1,q-1)
     */
    private void setTotient() {
        BigInteger p1 = p.subtract(BigInteger.ONE);
        BigInteger q1 = q.subtract(BigInteger.ONE);
        lambdaN = lcm(p1, q1);
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

    public BigInteger getLambdaN() {
        return lambdaN;
    }


    /**
     * Calculates the greatest common divisor of two BigIntegers
     * @param x the first BigInteger to be input
     * @param y the second BigInteger to be input
     * @return BigInteger gcd of the two inputs
     */
    public BigInteger gcd(BigInteger x, BigInteger y) {
        if (y.equals(BigInteger.ZERO)) {
            return x;
        } else {
            return gcd(y, x.mod(y));
        }
    }

    /**
     * Calculates the least common multiple of two BigIntegers
     * @param x the first BigInteger to be input
     * @param y the second BigInteger to be input
     * @return BigInteger lcm of the two inputs
     */
    public BigInteger lcm(BigInteger x, BigInteger y) {
        return x.multiply(y).divide(gcd(x, y));
    }
}