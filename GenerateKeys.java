import java.math.BigInteger;
import java.util.Random;

/**
 * @author Christopher Millar
 */
public class GenerateKeys {
    KeyRequirements keyRequirements = new KeyRequirements();
    private BigInteger e, d, n;
    private BigInteger lambdaN = keyRequirements.getLambdaN();

    GenerateKeys(){
        setPublicExponent();
        setPrivateExponent();
    }

    private void setPublicExponent() {
        Random rand = new Random();

        n = keyRequirements.getN();
        e = new BigInteger("0");

        while (!e.isProbablePrime(6) || e.compareTo(lambdaN) >= 0 || !(keyRequirements.gcd(e, lambdaN).compareTo(BigInteger.ONE) == 0) || e.compareTo(BigInteger.ZERO) <= 0) {
            e = BigInteger.valueOf(rand.nextLong());
        }
    }

    private void setPrivateExponent(){
        d = new BigInteger("0");
        d = e.modInverse(lambdaN);
    }

    public BigInteger getE() {
        return e;
    }

    public BigInteger getD() {
        return d;
    }
}
