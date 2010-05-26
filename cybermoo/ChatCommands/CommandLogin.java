package cybermoo.ChatCommands;

import com.google.gson.Gson;
import cybermoo.Player;
import cybermoo.Player;
import cybermoo.ThreadedClient;
import java.io.File;
import java.io.FileReader;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class CommandLogin implements Command {

    private final String userList = "data/users/";
    private FileReader fileInput;
    private Gson gson;

    public CommandLogin() {
        gson = new Gson();
    }

    public void call(String[] arguments, ThreadedClient source) {
        if (new File(userList + "/" + arguments[0] + ".txt").exists()) {
            try {
                fileInput = new FileReader(userList + arguments[0] + ".txt");
                String hash = toSHA(arguments[1]);
                Player player = gson.fromJson(fileInput, Player.class);
                fileInput.close();
                if (hash.equals(player.getHash())) {
                    source.sendText("You have successfully logged in!");
                    source.setPlayer(player);
                } else {
                    source.sendText("The password submitted is incorrect");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            source.sendText("The requested user does not exist, why not REGISTER it?");
        }
    }

    public Boolean isCleared(ThreadedClient source) {
        return source.getPlayer() == null;
    }

    private String toSHA(String password) throws SecurityException, NoSuchAlgorithmException {
        MessageDigest m = MessageDigest.getInstance("SHA");
        m.update(password.getBytes());
        return convToHex(m.digest());
    }

    private String convToHex(byte[] data) {
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
