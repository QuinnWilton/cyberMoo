package cybermoo.ChatCommands;

import cybermoo.Server;
import cybermoo.ThreadedClient;


public class CommandSay implements Command {

    public void call(String[] arguments, ThreadedClient source) {
        String temp = "";
        for(int i = 0; i < arguments.length; i++) {
            temp += " " + arguments[i];
        }
        String message = temp.trim();
        Server.getInstance().sendAll(source.getPlayer().getName() + " says \"" + message + "\"");
    }
    public Boolean isCleared(ThreadedClient source) {
        return source.getPlayer() != null;
    }
}
