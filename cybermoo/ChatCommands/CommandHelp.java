package cybermoo.ChatCommands;

import cybermoo.Server;
import cybermoo.ThreadedClient;

public class CommandHelp implements Command {

    public void call(String[] arguments, ThreadedClient source) {
        source.sendText(Server.getInstance().getCommandHandler().getCommands().get(arguments[0]).getHelp());
    }

    public Boolean isCleared(ThreadedClient source) {
        return true;
    }

    public String getHelp() {
        return "help <command>\nReturns usage instructions for a given command.";
    }
}
