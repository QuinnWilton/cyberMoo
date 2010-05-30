package cybermoo.ChatCommands;

import cybermoo.Handlers.CommandHandler;
import cybermoo.Server;
import cybermoo.ThreadedClient;
import java.util.Iterator;

public class CommandHelp implements Command {

    public void call(String[] arguments, ThreadedClient source) {
        if (arguments != null) {
            source.sendText(CommandHandler.getInstance().getCommands().get(arguments[0]).getHelp());
        } else {
            source.sendText("The following commands are available:");
            Iterator iterator = CommandHandler.getInstance().getCommands().keySet().iterator();
            while(iterator.hasNext()) {
                source.sendText(iterator.next().toString());
            }
            source.sendText("For additional information on a command, type: help <command>");
        }
    }

    public Boolean isCleared(ThreadedClient source) {
        return true;
    }

    public String getHelp() {
        return "help <command>\nReturns usage instructions for a given command.";
    }
}
