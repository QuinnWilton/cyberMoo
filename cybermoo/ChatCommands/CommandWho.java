package cybermoo.ChatCommands;

/**
 * Returns a listing of all online players
 * @author Shane
 */

import cybermoo.Server;
import cybermoo.ThreadedClient;

@Name("who")
public class CommandWho implements Command {

    public void call(String[] arguments, ThreadedClient source) {
        Server.getInstance().sendToClient(source, Server.getInstance().getClients().size() + " user(s) are connected, the following are logged in:");
        for (ThreadedClient c : Server.getInstance().getClients()) {
            if (c.getPlayer() != null) {
                Server.getInstance().sendToClient(source, c.getPlayer().getName());
            }
        }
    }

    public Boolean isCleared(ThreadedClient source) {
        return true;
    }

    public String getHelp() {
        return "who\nReturns the number of connected players, along with a list of those who are logged in.";
    }
}
