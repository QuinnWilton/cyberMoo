package cybermoo.ChatCommands;

import com.google.gson.Gson;
import cybermoo.Player;
import cybermoo.SceneHandler;
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
        if (arguments != null) {
            if (new File(userList + "/" + arguments[0] + ".txt").exists()) {
                source.sendText("The selected username already exists, have you forgotten your password?");
            } else {
                try {
                    fileOutput = new PrintWriter(new FileWriter(userList + arguments[0] + ".txt"));
                    Player player = new Player();
                    player.setName(arguments[0]);
                    player.setHash(toSHA(arguments[1]));
                    player.setLocation(SceneHandler.defaultStart);
                    fileOutput.println(gson.toJson(player));
                    fileOutput.close();
                    source.sendText("Your account has been created!");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } else {
            source.sendText("I don't understand, try: register <Username> <Password>");
        }
    }

    public Boolean isCleared(ThreadedClient source) {
        return source.getPlayer() == null;
    }

    public String getHelp() {
        return "register <Username> <Password>";
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
