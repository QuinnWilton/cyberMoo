package cybermoo.ChatCommands;

/**
 * Ensures that the input credentials do not exist,
 * before creating an account with a hash of the password
 * @author Shane
 */

import cybermoo.Handlers.DataHandler;
import cybermoo.Handlers.HelperHandler;
import cybermoo.Player;
import cybermoo.Handlers.SceneHandler;
import cybermoo.ThreadedClient;
import java.io.File;

@Name("register")
public class CommandRegister implements Command {

    private final String userList = "data/users/";

    public CommandRegister() {
    }

    public void call(String[] arguments, ThreadedClient source) {
        if (arguments != null && arguments.length == 2) {
            if (new File(userList + "/" + arguments[0]).exists()) {
                source.sendText("The selected username already exists, have you forgotten your password?");
            } else {
                    Player player = new Player();
                    player.setName(arguments[0]);
                    player.setHash(HelperHandler.toSHA(arguments[1]));
                    player.setLocation(SceneHandler.defaultStart);
                    DataHandler.getInstance().saveObject(userList + arguments[0], player, false);
                    source.sendText("Your account has been created!");
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
}
