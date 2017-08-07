import java.math.BigInteger;
import java.util.HashMap;

/**
 * The Encryption class uses the GenerateKeys class to make a
 * private and public key. It then uses these keys to encrypt
 * and decrypt BigIntegers.
 * @author Christopher Millar
 */
public class Encryption {

    GenerateKeys generateKeys = new GenerateKeys();

    /**
     * Encrypts message using, cipher text = message^(encryption exponent) (mod n)
     * @param plainText BigInteger message to be encrypted
     * @return the cipher text of the plaintext after encryption
     */
    protected BigInteger encrypt(BigInteger plainText) {
        HashMap publicKey = generateKeys.getPublicKey();
        BigInteger e = (BigInteger) publicKey.get("e");
        BigInteger n = (BigInteger) publicKey.get("n");

        BigInteger cipherText = plainText.modPow(e, n);

        return cipherText;
    }

    /**
     * Decrypts cipher text using, message = cipher text^(decryption exponent) (mod n)
     * @param cipherText BigInteger cipher text to be decrypted
     * @return the plain text of the cipher text message after decryption
     */
    protected BigInteger decrypt(BigInteger cipherText){
        HashMap privateKey = generateKeys.getPrivateKey();
        BigInteger d = (BigInteger) privateKey.get("d");
        BigInteger n = (BigInteger) privateKey.get("n");

        BigInteger plainText = cipherText.modPow(d,n);

        return plainText;
    }

}
