package cybermoo.ChatCommands;

/**
 * Logs the player out, closing the socket, and cleaning up
 * @author Shane
 */

import cybermoo.Server;
import cybermoo.ThreadedClient;
import java.io.IOException;

public class CommandQuit implements Command {

    public void call(String[] arguments, ThreadedClient source) {
        try {
            Server.getInstance().getClients().remove(source);
            source.getSocket().close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Boolean isCleared(ThreadedClient source) {
        return true;
    }

    public String getHelp() {
        return "quit\nExits the game";
    }
}
