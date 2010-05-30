package cybermoo.ChatCommands;

import cybermoo.Scene;
import cybermoo.Handlers.SceneHandler;
import cybermoo.ThreadedClient;

public class CommandBake implements Command {

    public void call(String[] arguments, ThreadedClient source) {
        if (arguments != null) {
            Scene location = source.getPlayer().getScene();
            for (int i = 0; i < location.getPlayers().size(); i++) {
                if (location.getPlayers().get(i).getClient() != null) {
                    location.getPlayers().get(i).getClient().sendText(source.getPlayer().getName() + " bakes a " + arguments[0] + " cake!");
                }
            }
        } else {
            source.sendText("What are you trying to say? say <message>");
        }
    }

    public Boolean isCleared(ThreadedClient source) {
        return source.getPlayer() != null;
    }

    public String getHelp() {
        return "say <Message>\nBroadcasts your message to all nearby players";
    }
}
