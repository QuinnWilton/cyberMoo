package cybermoo.ChatCommands;

/**
 * Returns a description of the player's current location
 * @author Shane
 */

import cybermoo.ThreadedClient;

public class CommandLook implements Command {

    public void call(String[] arguments, ThreadedClient source) {
        source.getPlayer().sendLocationData();
    }

    public Boolean isCleared(ThreadedClient source) {
        return source.getPlayer() != null;
    }

    public String getHelp() {
        return "look\nDisplays an updated view of your surroundings.";
    }
}
