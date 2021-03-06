import java.math.BigInteger;
import java.util.HashMap;
import java.util.Random;

/**
 * The GenerateKeys class uses the key components of the KeyRequirements class
 * to calculate a private and public key.
 * @author Christopher Millar
 */
public class GenerateKeys {
    private KeyRequirements keyRequirements = new KeyRequirements();
    private BigInteger e, d, n;
    private BigInteger lambdaN = keyRequirements.getLambdaN();
    private HashMap<String, BigInteger> privateKey = new HashMap();
    private HashMap<String, BigInteger> publicKey = new HashMap();

    GenerateKeys(){
        setPublicExponent();
        setPrivateExponent();
    }

    /**
     * Creates the public exponent of the public key (n,e)
     */
    private void setPublicExponent() {
        Random rand = new Random();

        n = keyRequirements.getN();
        e = new BigInteger("0");

        while (!e.isProbablePrime(6) || e.compareTo(lambdaN) >= 0 || !(keyRequirements.gcd(e, lambdaN).compareTo(BigInteger.ONE) == 0) || e.compareTo(BigInteger.ZERO) <= 0) {
            e = BigInteger.valueOf(rand.nextLong());
        }
    }

    /**
     * Creates the private exponent of the private key (n,d)
     */
    private void setPrivateExponent(){
        d = new BigInteger("0");
        d = e.modInverse(lambdaN);
    }

    public HashMap<String, BigInteger> getPublicKey() {
        publicKey.put("n", n);
        publicKey.put("e", e);
        return publicKey;
    }

    public HashMap<String, BigInteger> getPrivateKey() {
        privateKey.put("n", n);
        privateKey.put("d", d);
        return privateKey;
    }
}
