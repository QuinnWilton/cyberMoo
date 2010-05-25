package cybermoo.ChatCommands;

import cybermoo.Server;
import cybermoo.ThreadedClient;

public class CommandNick implements Command{

    public void call(String[] arguments, ThreadedClient source) {
        Server.getInstance().sendAll(source.getPlayer().getName() + " has changed his or her nickname to " + arguments[0]);
        source.getPlayer().setName(arguments[0]);
    }
}
