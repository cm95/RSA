import java.math.BigInteger;
import java.util.HashMap;

/**
 * @author Christopher Millar
 */
public class Encryption {

    GenerateKeys generateKeys = new GenerateKeys();

    protected BigInteger encrypt(BigInteger plainText) {
        HashMap publicKey = generateKeys.getPublicKey();
        BigInteger e = (BigInteger) publicKey.get("e");
        BigInteger n = (BigInteger) publicKey.get("n");

        BigInteger cipherText = plainText.modPow(e, n);

        return cipherText;
    }

    protected BigInteger decrypt(BigInteger cipherText){
        HashMap privateKey = generateKeys.getPrivateKey();
        BigInteger d = (BigInteger) privateKey.get("d");
        BigInteger n = (BigInteger) privateKey.get("n");

        BigInteger plainText = cipherText.modPow(d,n);

        return plainText;

    }

}
