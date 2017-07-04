package helpers;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.xml.bind.DatatypeConverter;

public final class Hash {

    public static String generateHash(String string) throws NoSuchAlgorithmException {

        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
        messageDigest.update(string.getBytes());
        String encryptedString = new String(messageDigest.digest());

        return encryptedString;
    }

    public static String base64encode(String string) {
        try {
            return DatatypeConverter.printBase64Binary(string.getBytes());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return "";
        }
    }

    public static String base64decode(String string) {
        try {
            return new String(DatatypeConverter.parseBase64Binary(string));
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return "";
        }
    }

}
