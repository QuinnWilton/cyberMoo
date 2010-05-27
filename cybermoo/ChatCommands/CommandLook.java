package cybermoo.ChatCommands;

import cybermoo.ThreadedClient;

public class CommandLook implements Command {

    public void call(String[] arguments, ThreadedClient source) {
        source.getPlayer().sendLocationData();
    }

    public Boolean isCleared(ThreadedClient source) {
        return true;
    }

    public String getHelp() {
        return "look\nDisplays an updated view of your surroundings.";
    }
}
