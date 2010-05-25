package cybermoo.ChatCommands;

import com.google.gson.Gson;
import cybermoo.Player;
import cybermoo.ThreadedClient;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class CommandRegister implements Command {

    private final String userList = "data/users/";
    private PrintWriter fileOutput;
    private Gson gson;

    public CommandRegister() {
        gson = new Gson();
    }

    public void call(String[] arguments, ThreadedClient source) {
        System.out.println(arguments[0] + " " + arguments[1]);
        if (new File(userList + "/" + arguments[1] + ".txt").exists()) {
            source.sendText("The selected username already exists, have you forgotten your password?");
        } else {
            try {
                fileOutput = new PrintWriter(new FileWriter(userList + arguments[0] + ".txt"));
                String userName = arguments[1];
                String hash = toSHA(arguments[1]);
                Player player = new Player();
                player.setName(userName);
                player.setHash(hash);
                fileOutput.println(gson.toJson(player));
                fileOutput.close();
                source.sendText("Your account has been created!");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
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
