package Recursos;

import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 *
 * @author mateo
 */
public class Encriptar {
    private static final String CLAVE_SECRETA = "claveSecreta1234";
    private static final SecretKey SECRET_KEY = generarClaveSecreta();

    private static SecretKey generarClaveSecreta() {
        try {
            byte[] claveBytes = CLAVE_SECRETA.getBytes();
            KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
            return new SecretKeySpec(claveBytes, "AES");
        } catch (Exception e) {
            System.out.println(e.toString());
            return null;
        }
    }

    public String encriptar(String c) {
        String enc = "";
        try {
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, SECRET_KEY);
            byte[] textoEncriptado = cipher.doFinal(c.getBytes());
            enc = Base64.getEncoder().encodeToString(textoEncriptado);
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return enc;
    }
}
