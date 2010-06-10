package cybermoo.Handlers;

/**
 * Contains utility methods which can be
 * applied to a variety of classes
 * @author Shane
 */

import java.security.MessageDigest;

public class HelperHandler {

    public static String toSHA(String password) {
        try {
            MessageDigest m = MessageDigest.getInstance("SHA");
            m.update(password.getBytes());
            return convToHex(m.digest());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String convToHex(byte[] data) {
        StringBuilder buf = new StringBuilder();
        for (int i = 0; i < data.length; i++) {
            int halfbyte = (data[i] >>> 4) & 0x0F;
            int two_halfs = 0;
            do {
                if ((0 <= halfbyte) && (halfbyte <= 9)) {
                    buf.append((char) ('0' + halfbyte));
                } else {
                    buf.append((char) ('a' + (halfbyte - 10)));
                }
                halfbyte = data[i] & 0x0F;
            } while (two_halfs++ < 1);
        }
        return buf.toString();
    }
}
