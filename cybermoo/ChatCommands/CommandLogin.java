package cybermoo.ChatCommands;

/**
 * Encrypts the input password using RSA encryption
 * before comparing the input credentials to a database
 * @author Shane
 */

import cybermoo.Handlers.DataHandler;
import cybermoo.Handlers.HelperHandler;
import cybermoo.Player;
import cybermoo.ThreadedClient;
import java.io.File;

public class CommandLogin implements Command {

    private final String userList = "data/users/";

    public CommandLogin() {
    }

    public void call(String[] arguments, ThreadedClient source) {
        if (arguments != null && arguments.length == 2) {
            if (new File(userList + "/" + arguments[0]).exists()) {
                String hash = HelperHandler.toSHA(arguments[1]);
                Player player = DataHandler.getInstance().loadObject(userList + arguments[0], Player.class);
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
}
