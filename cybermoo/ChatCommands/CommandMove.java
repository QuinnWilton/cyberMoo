package cybermoo.ChatCommands;

/**
 * Moves the player in the specific direction,
 * if a destination is available
 * @author Shane
 */

import cybermoo.Scene;
import cybermoo.Handlers.SceneHandler;
import cybermoo.ThreadedClient;

public class CommandMove implements Command {

    public void call(String[] arguments, ThreadedClient source) {
        if (arguments != null) {
            Scene curLocation = source.getPlayer().getScene();
            String direction = curLocation.getDirection(arguments[0]);
            if (direction != null && !direction.equals("")) {
                for(int i = 0; i < SceneHandler.getInstance().getScenes().get(direction).getPlayers().size(); i++) {
                    SceneHandler.getInstance().getScenes().get(direction).getPlayers().get(i).getClient().sendText(source.getPlayer().getName() + " arrives at your location.");
                }
                source.getPlayer().move(direction);
                source.getPlayer().sendLocationData();
            } else {
                source.sendText("Please input a valid direction.");
            }
        } else {
            source.sendText("I don't understand, try: move <direction>");
        }
    }

    public Boolean isCleared(ThreadedClient source) {
        return source.getPlayer() != null;
    }

    public String getHelp() {
        return "move <direction>\nMoves your player in the specified direction.";
    }
}
