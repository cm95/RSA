import java.math.BigInteger;
import java.util.Random;

/**
 * @author Christopher Millar
 */
public class KeyRequirements {
    private BigInteger p, q, n, lambdaN;

    KeyRequirements(){
        setPrimeNumbers();
        setTotient();
    }

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

    public BigInteger gcd(BigInteger x, BigInteger y) {
        if (y.equals(BigInteger.ZERO)) {
            return x;
        } else {
            return gcd(y, x.mod(y));
        }
    }

    public BigInteger lcm(BigInteger x, BigInteger y) {
        return x.multiply(y).divide(gcd(x, y));
    }
}