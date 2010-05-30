package cybermoo.ChatCommands;

import cybermoo.Handlers.DataHandler;
import cybermoo.Player;
import cybermoo.ThreadedClient;
import java.io.File;
import java.security.MessageDigest;

public class CommandLogin implements Command {

    private final String userList = "data/users/";

    public CommandLogin() {
    }

    public void call(String[] arguments, ThreadedClient source) {
        if (arguments != null && arguments.length == 2) {
            if (new File(userList + "/" + arguments[0] + ".txt").exists()) {
                String hash = toSHA(arguments[1]);
                Player player = DataHandler.getInstance().loadObject(userList + arguments[0] + ".txt", Player.class);
                if (hash.equals(player.getHash())) {
                    source.sendText("You have successfully logged in!\n");
                    player.setClient(source);
                    source.setPlayer(player);
                    source.getPlayer().move(source.getPlayer().getLocation());
                    source.getPlayer().sendLocationData();
                } else {
                    source.sendText("The password submitted is incorrect");
                }
            } else {
                source.sendText("The requested user does not exist, why not REGISTER it?");
            }
        } else {
            source.sendText("I don't understand, try: login <Username> <Password>");
        }
    }

    public Boolean isCleared(ThreadedClient source) {
        return source.getPlayer() == null;
    }

    public String getHelp() {
        return "login <Username> <Password>";
    }

    private String toSHA(String password) {
        try {
            MessageDigest m = MessageDigest.getInstance("SHA");
            m.update(password.getBytes());
            return convToHex(m.digest());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
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
