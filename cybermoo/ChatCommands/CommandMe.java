package cybermoo.ChatCommands;

/**
 * Allows the player to create an emote, alerting their
 * action to nearby players
 * @author Shane
 */

import cybermoo.Scene;
import cybermoo.ThreadedClient;

@Name("me")
public class CommandMe implements Command {

    public void call(String[] arguments, ThreadedClient source) {
        if (arguments != null) {
            String temp = "";
            for (int i = 0; i < arguments.length; i++) {
                temp += " " + arguments[i];
            }
            String message = temp.trim();
            Scene location = source.getPlayer().getScene();
            for (int i = 0; i < location.getPlayers().size(); i++) {
                if (location.getPlayers().get(i).getClient() != null) {
                    location.getPlayers().get(i).getClient().sendText(source.getPlayer().getName() + " " + message);
                }
            }
        } else {
            source.sendText("What are you trying to do? me <action>");
        }
    }

    public Boolean isCleared(ThreadedClient source) {
        return source.getPlayer() != null;
    }

    public String getHelp() {
        return "me <Message>\nBroadcasts an action to all nearby players";
    }
}
