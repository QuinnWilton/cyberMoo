package cybermoo.ChatCommands;

import cybermoo.ThreadedClient;

/**
 *
 * @author Shane
 */
public interface Command {
    public void call(String[] arguments, ThreadedClient source);
    public Boolean isCleared(ThreadedClient source);
    public String getHelp();
}
