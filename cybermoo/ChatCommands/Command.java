package cybermoo.ChatCommands;

import cybermoo.ThreadedClient;

/**
 * An interface for a generic command, creating a contract between
 * the Command Handler and any commands which are then instantiated
 * @author Shane
 */

public interface Command {
    public void call(String[] arguments, ThreadedClient source);
    public Boolean isCleared(ThreadedClient source);
    public String getHelp();
}
